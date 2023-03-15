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

import nameless.classicraft.api.access.ZombieAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public class MixinZombie implements ZombieAccessor {

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.INT);

    private static final int MAX_VARIANTS = 200;

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void addAdditionalSaveDataCC(CompoundTag pCompound, CallbackInfo ci) {
        pCompound.putInt("Variant", getVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void readAdditionalSaveDataCC(CompoundTag pCompound, CallbackInfo ci) {
        setVariant(Mth.clamp(pCompound.getInt("Variant"), 0, MAX_VARIANTS - 1));
        setVariant(pCompound.getInt("Variant"));
    }

    @Inject(method = "defineSynchedData", at = @At("HEAD"))
    private void defineSynchedDataCC(CallbackInfo ci) {
        ((Zombie) (Object) this).getEntityData().define(VARIANT, 0);
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    private void finalizeSpawnCC(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, SpawnGroupData pSpawnData, CompoundTag pDataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        int i = pLevel.getRandom().nextInt(200);
        this.setVariant(i);
    }

    @Override
    public int getVariant() {
        return  ((Zombie) (Object) this).getEntityData().get(VARIANT);
    }

    @Override
    public void setVariant(int variant) {
        ((Zombie) (Object) this).getEntityData().set(VARIANT, variant);
    }

    @Override
    public EntityDataAccessor<Integer> getVariantCC() {
        return VARIANT;
    }

    @Override
    public int getMaxVariants() {
        return MAX_VARIANTS;
    }
}
