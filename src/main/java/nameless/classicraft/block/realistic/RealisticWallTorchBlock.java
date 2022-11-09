package nameless.classicraft.block.realistic;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;


public class RealisticWallTorchBlock extends RealisticTorchBlock {

    public static final int TICK_RATE = ModBlockProperties.TICK_RATE;

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D),
            Direction.SOUTH, box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D),
            Direction.WEST, box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D),
            Direction.EAST, box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

    public RealisticWallTorchBlock()
    {
        super();
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
        if(state.getValue(LITSTATE) == LIT && level.getRandom().nextInt(2) == 1)
        {
            Direction direction = state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.7D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.22D;
            double d4 = 0.27D;
            Direction direction1 = direction.getOpposite();
            level.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
        }
        else if(state.getValue(LITSTATE) == SMOLDERING && level.getRandom().nextInt(2) == 1 )
        {
            Direction direction = state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.7D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.22D;
            double d4 = 0.27D;
            Direction direction1 = direction.getOpposite();
            level.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void changeToLit(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.WALL_TORCH.get().defaultBlockState().
                setValue(LITSTATE,LIT).
                setValue(BURNTIME,getInitialBurnTime()).
                setValue(FACING,pState.getValue(FACING)));
        if(SHOULD_BURN_OUT){
            pLevel.scheduleTick(pPos,this,TICK_RATE);
        }
    }

    @Override
    public void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime) {
        pLevel.setBlockAndUpdate(pPos,ModBlocks.WALL_TORCH.get().defaultBlockState().
                setValue(LITSTATE,SMOLDERING).
                setValue(BURNTIME,burnTime).
                setValue(FACING,pState.getValue(FACING)));
        if(SHOULD_BURN_OUT){
            pLevel.scheduleTick(pPos,this,TICK_RATE);
        }
    }

    @Override
    public void changeToUnlit(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlockAndUpdate(pPos,ModBlocks.WALL_TORCH.get().defaultBlockState().
                setValue(FACING,pState.getValue(FACING)));
        if(SHOULD_BURN_OUT)
            pLevel.scheduleTick(pPos,this,TICK_RATE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return net.minecraft.world.level.block.WallTorchBlock.getShape(pState);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return Blocks.WALL_TORCH.isValidSpawn(state,level,pos,type,entityType);
    }

    //let it can be placed in the wall
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return Blocks.WALL_TORCH.canSurvive(pState,pLevel,pPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState state =Blocks.WALL_TORCH.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(!placeStack.is(ModItems.LIT_TORCH.get())) return state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING));
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {
            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > INITIAL_BURN_TIME)
                {
                    return  state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
                }
                else if(burnTime <= 0)
                {
                    return  state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING));
                }
                else
                {
                    return state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,burnTime).setValue(LITSTATE,1);
                }
            }
            if(burnTime > INITIAL_BURN_TIME)
            {
                return  state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
            }
            else if(burnTime <= 0)
            {
                return  state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING));
            }
            else
            {
                return state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,burnTime).setValue(LITSTATE,2);
            }

        }
        else
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                return  state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
            }
            return state == null ? null:this.defaultBlockState().setValue(FACING,state.getValue(FACING)).setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return Blocks.WALL_TORCH.updateShape(pState,pFacing,pFacingState,pLevel,pCurrentPos,pFacingPos);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return Blocks.WALL_TORCH.rotate(pState,pRotation);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return Blocks.WALL_TORCH.mirror(pState,pMirror);
    }
}
