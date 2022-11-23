package nameless.classicraft.entity;

import nameless.classicraft.entity.goal.SwineBreakCropGoal;
import nameless.classicraft.init.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.hoglin.HoglinAi;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SwineEntity extends Animal implements NeutralMob {

    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private static final EntityDataAccessor<Boolean> HUNGY = SynchedEntityData.defineId(SwineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TIME_TILL_HUNGRY = SynchedEntityData.defineId(SwineEntity.class, EntityDataSerializers.INT);
    int lastTimeSinceHungry;

    public SwineEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.WHEAT,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.CARROTS,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.BEETROOTS,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.PUMPKIN,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.PUMPKIN_STEM,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.ATTACHED_PUMPKIN_STEM,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.MELON,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.MELON_STEM,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.ATTACHED_MELON_STEM,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.SWEET_BERRY_BUSH,1.0D, 24));
        this.goalSelector.addGoal(1, new SwineBreakCropGoal(this, Blocks.POTATOES,1.0D, 24));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));}

    public void tick() {
        super.tick();
        if (!this.isHungry() && lastTimeSinceHungry < this.getTimeTillHungry()) {
            lastTimeSinceHungry++;
        }
        if (lastTimeSinceHungry >= this.getTimeTillHungry()) {
            this.setHungry(true);
            lastTimeSinceHungry = 0;
        }
    }

    static boolean hurtAndThrowTarget(LivingEntity pSwine, LivingEntity pTarget) {
        float f1 = (float)pSwine.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f;
        f = f1 / 2.0F + (float)pSwine.level.random.nextInt((int)f1);

        boolean flag = pTarget.hurt(DamageSource.mobAttack(pSwine), f);
        if (flag) {
            pSwine.doEnchantDamageEffects(pSwine, pTarget);
            throwTarget(pSwine, pTarget);
        }

        return flag;
    }

    static void throwTarget(LivingEntity pSwine, LivingEntity pTarget) {
        double d0 = pSwine.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        double d1 = pTarget.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        double d2 = d0 - d1;
        if (!(d2 <= 0.0D)) {
            double d3 = pTarget.getX() - pSwine.getX();
            double d4 = pTarget.getZ() - pSwine.getZ();
            float f = (float)(pSwine.level.random.nextInt(21) - 10);
            double d5 = d2 * (double)(pSwine.level.random.nextFloat() * 0.5F + 0.2F);
            Vec3 vec3 = (new Vec3(d3, 0.0D, d4)).normalize().scale(d5).yRot(f);
            double d6 = d2 * (double)pSwine.level.random.nextFloat() * 0.5D;
            pTarget.push(vec3.x, d6, vec3.z);
            pTarget.hurtMarked = true;
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HUNGY, true);
        this.entityData.define(TIME_TILL_HUNGRY, 0);
    }

    public boolean doHurtTarget(Entity pEntity) {
        if (!(pEntity instanceof LivingEntity)) {
            return false;
        } else {
            return hurtAndThrowTarget(this, (LivingEntity)pEntity);
        }
    }

    protected void blockedByShield(LivingEntity pEntity) {
        throwTarget(this, pEntity);
    }

    public boolean isHungry() {
        return this.entityData.get(HUNGY);
    }

    public void setHungry(boolean hungry) {
        this.entityData.set(HUNGY, hungry);
    }

    public int getTimeTillHungry() {
        return this.entityData.get(TIME_TILL_HUNGRY);
    }

    public void setTimeTillHungry(int ticks) {
        this.entityData.set(TIME_TILL_HUNGRY, ticks);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsHungry", this.isHungry());
        pCompound.putInt("TimeTillHungry", this.getTimeTillHungry());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setHungry(pCompound.getBoolean("IsHungry"));
        this.setTimeTillHungry(pCompound.getInt("TimeTillHungry"));
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public boolean isAngryAt(LivingEntity pTarget) {
        if (!this.canAttack(pTarget)) {
            return false;
        } else {
            return pTarget.getType() == EntityType.PLAYER && this.isAngryAtAllPlayers(pTarget.level) || pTarget.getUUID().equals(this.getPersistentAngerTarget());
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
