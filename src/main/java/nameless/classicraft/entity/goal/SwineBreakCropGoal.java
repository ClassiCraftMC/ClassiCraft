package nameless.classicraft.entity.goal;

import nameless.classicraft.entity.SwineEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SwineBreakCropGoal extends MoveToBlockGoal {
    private final Block blockToRemove;
    private final SwineEntity removerMob;
    private int ticksSinceReachedGoal;
    private static final int WAIT_AFTER_BLOCK_FOUND = 20;

    public SwineBreakCropGoal(SwineEntity pRemoverMob, double pSpeedModifier, int pSearchRange) {
        super(pRemoverMob, pSpeedModifier, 24, pSearchRange);
        this.blockToRemove = Blocks.FARMLAND;
        this.removerMob = pRemoverMob;
    }

    public boolean canUse() {
        if (!ForgeEventFactory.getMobGriefingEvent(this.removerMob.level, this.removerMob)) {
            return false;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else if (this.tryFindBlock()) {
            this.nextStartTick = reducedTickDelay(20);
            return true;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            return false;
        }
    }

    private boolean tryFindBlock() {
        return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true : this.findNearestBlock();
    }

    public void stop() {
        super.stop();
        this.removerMob.fallDistance = 1.0F;
    }

    public void start() {
        super.start();
        this.ticksSinceReachedGoal = 0;
    }

    public void playDestroyProgressSound(LevelAccessor pLevel, BlockPos pPos) {
    }

    public void playBreakSound(Level pLevel, BlockPos pPos) {
    }

    public void tick() {
        super.tick();
        Level level = this.removerMob.level;
        BlockPos blockpos = this.removerMob.blockPosition();
        BlockPos blockpos1 = this.getPosWithBlock(blockpos, level);
        RandomSource randomsource = this.removerMob.getRandom();
        if (this.isReachedTarget() && blockpos1 != null) {
            Vec3 vec31;
            double d3;
            if (this.ticksSinceReachedGoal > 0) {
                vec31 = this.removerMob.getDeltaMovement();
                this.removerMob.setDeltaMovement(vec31.x, 0.3, vec31.z);
                if (!level.isClientSide) {
                    d3 = 0.08;
                    ((ServerLevel)level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.FARMLAND.defaultBlockState()), (double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.7, (double)blockpos1.getZ() + 0.5, 3, ((double)randomsource.nextFloat() - 0.5) * 0.08, ((double)randomsource.nextFloat() - 0.5) * 0.08, ((double)randomsource.nextFloat() - 0.5) * 0.08, 0.15000000596046448);
                }
            }

            if (this.ticksSinceReachedGoal % 2 == 0) {
                vec31 = this.removerMob.getDeltaMovement();
                this.removerMob.setDeltaMovement(vec31.x, -0.3, vec31.z);
                if (this.ticksSinceReachedGoal % 6 == 0) {
                    this.playDestroyProgressSound(level, this.blockPos);
                }
            }

            if (this.ticksSinceReachedGoal > 30) {
                level.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
                if (!level.isClientSide) {
                    for(int i = 0; i < 20; ++i) {
                        d3 = randomsource.nextGaussian() * 0.02;
                        double d1 = randomsource.nextGaussian() * 0.02;
                        double d2 = randomsource.nextGaussian() * 0.02;
                        ((ServerLevel)level).sendParticles(ParticleTypes.POOF, (double)blockpos1.getX() + 0.5, (double)blockpos1.getY(), (double)blockpos1.getZ() + 0.5, 1, d3, d1, d2, 0.15000000596046448);
                    }

                    this.playBreakSound(level, blockpos1);
                }
            }

            ++this.ticksSinceReachedGoal;
        }

    }

    @Nullable
    private BlockPos getPosWithBlock(BlockPos pPos, BlockGetter pLevel) {
        if (pLevel.getBlockState(pPos).is(this.blockToRemove)) {
            return pPos;
        } else {
            BlockPos[] ablockpos = new BlockPos[]{pPos.below(), pPos.west(), pPos.east(), pPos.north(), pPos.south(), pPos.below().below()};
            BlockPos[] var4 = ablockpos;
            int var5 = ablockpos.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                BlockPos blockpos = var4[var6];
                if (pLevel.getBlockState(blockpos).is(this.blockToRemove)) {
                    return blockpos;
                }
            }

            return null;
        }
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        ChunkAccess chunkaccess = pLevel.getChunk(SectionPos.blockToSectionCoord(pPos.getX()), SectionPos.blockToSectionCoord(pPos.getZ()), ChunkStatus.FULL, false);
        if (chunkaccess == null || !this.removerMob.isHungry()) {
            return false;
        } else if (!chunkaccess.getBlockState(pPos).canEntityDestroy(pLevel, pPos, this.removerMob)) {
            return false;
        } else {
            return chunkaccess.getBlockState(pPos).is(this.blockToRemove) && chunkaccess.getBlockState(pPos.above()).isAir() && chunkaccess.getBlockState(pPos.above(2)).isAir();
        }
    }

}