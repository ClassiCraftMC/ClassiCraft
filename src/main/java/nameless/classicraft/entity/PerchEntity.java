package nameless.classicraft.entity;

import com.google.common.collect.Lists;
import nameless.classicraft.entity.goal.PerchHideInSeagrassGoal;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PerchEntity extends AbstractSchoolingFish {

    public PerchEntity(EntityType<? extends PerchEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PerchHideInSeagrassGoal(this));
    }

    public boolean isSeagrassNearby() {
        return !this.getNearbySeagrass().isEmpty();
    }

    public List<BlockPos> getNearbySeagrass() {
        List<BlockPos> seagrasses = Lists.newArrayList();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int yy = this.blockPosition().getY() - 6; yy <= this.getY() + 6; yy++) {
            for (int xx = this.blockPosition().getX() - 12; xx <= this.getX() + 12; xx++) {
                for (int zz = this.blockPosition().getZ() - 12; zz <= this.getZ() + 12; zz++) {
                    mutable.set(xx, yy, zz);
                    BlockState block = this.level.getBlockState(mutable);
                    if (block.is(Blocks.SEAGRASS) || block.is(Blocks.TALL_SEAGRASS)) {
                        seagrasses.add(mutable);
                    }
                }
            }
        }
        return seagrasses;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.PERCH_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FIRE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.PUFFER_FISH_FLOP;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

}