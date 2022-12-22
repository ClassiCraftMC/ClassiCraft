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
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
}
