/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.mixin;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.san.ISanHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity implements ISanHandler {

    private static final EntityDataAccessor<Float> SAN =
            SynchedEntityData.defineId(Player.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAX_SAN =
            SynchedEntityData.defineId(Player.class, EntityDataSerializers.FLOAT);
    private static final int REGEN_SPEED = 20;
    private static final float REGEN_AMOUNT = 0.05f;

    protected MixinPlayer(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void setSan(float mana) {
        this.entityData.set(SAN, Math.min(getMaxSan(), mana));
    }

    @Override
    public void setMaxSan(float maxMana) {
        this.entityData.set(SAN, Mth.clamp(maxMana, 0.0F, this.getMaxSan()));
    }

    @Override
    public void regenSan(float mana) {
        this.setSan(this.getSan() + mana);
    }

    @Override
    public float getSan() {
        return this.entityData.get(SAN);
    }

    @Override
    public float getMaxSan() {
        return (float) this.getAttributeValue(Attributes.MAX_HEALTH);
    }

    @Override
    public void reduceSan(float mana) {
        this.setSan(this.getSan() - mana);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void onPlayerUpdate(CallbackInfo ci) {
        if (this.getSan() < this.getMaxSan() && this.tickCount % REGEN_SPEED == 0) {
            regenSan(REGEN_AMOUNT);
        }
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void manaInit(CallbackInfo ci) {
        this.entityData.define(MAX_SAN, 0.0F);
        this.entityData.define(SAN, 0.0F);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    private void writeSan(CompoundTag compound, CallbackInfo ci) {
        compound.putFloat(ClassiCraftMod.MOD_ID + ":MaxSan", this.getMaxSan());
        compound.putFloat(ClassiCraftMod.MOD_ID + ":San", this.getSan());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    private void readSan(CompoundTag compound, CallbackInfo ci) {
        this.setMaxSan(compound.getFloat(ClassiCraftMod.MOD_ID + ":MaxSan"));
        this.setSan(compound.getFloat(ClassiCraftMod.MOD_ID + ":San"));
    }

    /**
     * Fixes MC-219083 by only doing natural regeneration on server
     */
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"), require = 0)
    private boolean onlyDoNaturalRegenerationOnServer(GameRules instance, GameRules.Key<GameRules.BooleanValue> key) {
        return instance.getBoolean(key) && !level.isClientSide;
    }
}
