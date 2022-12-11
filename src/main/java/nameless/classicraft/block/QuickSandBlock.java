package nameless.classicraft.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class QuickSandBlock extends SandBlock {

    private static final VoxelShape FALLING_COLLISION_SHAPE =
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D,
                    1.0D, (double)0.9F, 1.0D);

    public QuickSandBlock(int pDustColor) {
        super(pDustColor, AbstractBlock.Settings.of(Material.AGGREGATE).strength(0.25F).sounds(BlockSoundGroup.SAND).dynamicBounds());
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.8999999761581421, 1.5, 0.8999999761581421));
            if (world.isClient) {
                Random randomsource = world.getRandom();
                boolean flag = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    world.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), (double)(pos.getY() + 1), entity.getZ(), (double)(MathHelper.nextBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), 0.05000000074505806, (double)(MathHelper.nextBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }

        if (!world.isClient) {
            if (entity.isOnFire() && (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity) && entity.canModifyAt(world, pos)) {
                world.breakBlock(pos, false);
            }

            entity.setOnFire(false);
        }

    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!((double)fallDistance < 4.0) && entity instanceof LivingEntity livingentity) {
            LivingEntity.FallSounds fallSounds = livingentity.getFallSounds();
            SoundEvent soundevent = (double)fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
            entity.playSound(soundevent, 1.0F, 1.0F);
        }

    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                if (entity.fallDistance > 2.5F) {
                    return FALLING_COLLISION_SHAPE;
                }

                boolean flag = entity instanceof FallingBlockEntity;
                if (flag || canEntityWalkOnQuickSand(entity) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending()) {
                    return super.getCollisionShape(state, world, pos, context);
                }
            }
        }

        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public static boolean canEntityWalkOnQuickSand(Entity pEntity) {
        if (pEntity.getType().isIn(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
            return true;
        } else {
            return pEntity instanceof LivingEntity && ((LivingEntity) pEntity).getEquippedStack(EquipmentSlot.FEET).canWalkOnPowderedSnow((LivingEntity) pEntity);
        }
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }
}
