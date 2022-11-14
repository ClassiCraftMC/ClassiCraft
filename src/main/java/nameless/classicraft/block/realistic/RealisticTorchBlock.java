package nameless.classicraft.block.realistic;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.ToIntFunction;

public class RealisticTorchBlock extends Block {

    public static final int TICK_INTERVAL = ModBlockProperties.TICK_INTERVAL;

    public static final int LIT = ModBlockProperties.LIT;
    public static final int SMOLDERING = ModBlockProperties.SMOLDERING;
    public static final int UNLIT = ModBlockProperties.UNLIT;
    protected static final int INITIAL_BURN_TIME = ClassiCraftConfiguration.torchBurnoutTime.get();
    protected static final boolean CAUSE_FIRE = ClassiCraftConfiguration.torchCauseFire.get();
    protected static final boolean SHOULD_BURN_OUT = INITIAL_BURN_TIME >= 0;
    protected static final IntegerProperty LITSTATE = ModBlockProperties.LITSTATE;
    public static final IntegerProperty BURNTIME = IntegerProperty.create("burntime", 0, SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 1);

    protected static final int AABB_STANDING_OFFSET = 2;
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public RealisticTorchBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).noCollission().instabreak().lightLevel(getLightLevelFromState()).sound(SoundType.WOOD));
        this.registerDefaultState(this.defaultBlockState().setValue(LITSTATE, 0).setValue(BURNTIME, 0));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public void animateTick(BlockState state, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (state.getValue(LITSTATE) == LIT || (state.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            double d0 = (double) pPos.getX() + 0.5D;
            double d1 = (double) pPos.getY() + 0.7D;
            double d2 = (double) pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.hurtAndBreak(1, pPlayer, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(pHand);
                });
            if (pLevel.isRainingAt(pPos.above())) {
                ModBlockProperties.playExtinguishSound(pLevel, pPos);
                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
                ItemEntity newItem = new ItemEntity(
                        pLevel,
                        pPos.getX(), pPos.getY(),
                        pPos.getZ(),
                        Items.STICK.getDefaultInstance());
                pLevel.addFreshEntity(newItem);
            } else {
                changeToLit(pLevel, pPos, pState);
            }
            pLevel.updateNeighborsAt(pPos, this);
        }
        return InteractionResult.SUCCESS;
        }
        else if (pPlayer.getItemInHand(pHand).getItem() == ModItems.MATCHBOX.get()) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.shrink(1);
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                    ModBlockProperties.playExtinguishSound(pLevel, pPos);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                        ModBlockProperties.playExtinguishSound(pLevel, pPos);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState);
                        ModBlockProperties.playLightingSound(pLevel,pPos);
                    }
                    pLevel.updateNeighborsAt(pPos,this);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                    ModBlockProperties.playExtinguishSound(pLevel,pPos);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState);
                }
                pLevel.updateNeighborsAt(pPos,this);
            }
            return InteractionResult.SUCCESS;
        }
        else if( pState.getValue(LITSTATE) == 2 && pPlayer.getItemInHand(pHand).is(ModItems.TORCH.get()))
        {
            pPlayer.setItemInHand(pHand, ItemStackAPI.replaceItemWithCopyNBTTagAndCountButResetBurnTime(pPlayer.getItemInHand(pHand),ModItems.LIT_TORCH.get(), RealisticTorchBlock.INITIAL_BURN_TIME));
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return CAUSE_FIRE &&pState.getValue(LITSTATE) == LIT  && new Random().nextInt(9) == 0;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(pos.getY() == level.getHeight() -2 ) return;
        if(level.getBlockState(pos.above()).getMaterial() == Material.WOOD || level.getBlockState(pos.above()).getMaterial() == Material.WOOL || level.getBlockState(pos.above()).getMaterial() == Material.LEAVES)
        {
            if(level.getBlockState(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ())).getMaterial() == Material.WOOD||level.getBlockState(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ())).getMaterial() == Material.WOOL||level.getBlockState(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ())).getMaterial() == Material.LEAVES || level.getBlockState(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ())).getMaterial() == Material.AIR)
                level.setBlockAndUpdate(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ()), Blocks.FIRE.defaultBlockState());
            else
                level.setBlockAndUpdate(pos.above(),Blocks.AIR.defaultBlockState());
        }
        else if(level.getBlockState(pos.above()).getMaterial() == Material.AIR)
        {
            if(level.getBlockState(new BlockPos(pos.getX(),pos.getY()+2,pos.getZ())).getMaterial() != Material.AIR)
                level.setBlockAndUpdate(pos.above(),Blocks.FIRE.defaultBlockState().setValue(FireBlock.UP,true));
        }

    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.isClientSide() && SHOULD_BURN_OUT && state.getValue(LITSTATE) > UNLIT)
        {
            int newBurnTime = state.getValue(BURNTIME) -1;
            /**
            if(level.isRainingAt(pos.above()))
            {
               level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                ItemEntity newItem = new ItemEntity(
                        level,
                        pos.getX(), pos.getY(),
                        pos.getZ(),
                        Items.STICK.getDefaultInstance());
                level.addFreshEntity(newItem);
            }*/
            if(newBurnTime <= 0)
            {
                ModBlockProperties.playExtinguishSound(level,pos);
                changeToUnlit(level,pos,state);
                level.updateNeighborsAt(pos,this);
            }
            else if(state.getValue(LITSTATE) == LIT &&(newBurnTime <= INITIAL_BURN_TIME / 10 || newBurnTime <=1))
            {
                changeToSmoldering(level,pos,state,newBurnTime);
                level.updateNeighborsAt(pos,this);
            }
            else {
                level.setBlockAndUpdate(pos,state.setValue(BURNTIME,state.getValue(BURNTIME) -1 ));
                level.scheduleTick(pos,this, TICK_INTERVAL);
            }

        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock())
        {
            defaultBlockState().updateIndirectNeighbourShapes(pLevel,pPos,3);
        }
        if(SHOULD_BURN_OUT&&pState.getBlock() instanceof RealisticTorchBlock&&pState.getValue(LITSTATE) > UNLIT)
            pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
        super.onPlace(pState,pLevel,pPos,pOldState,pIsMoving);
    }

    public static IntegerProperty getBurnTime() {
        return BURNTIME;
    }

    public static IntegerProperty getLitState() {
        return LITSTATE;
    }

    public static int getInitialBurnTime() {
        return SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 0;
    }

    public void changeToLit(Level pLevel, BlockPos pPos, BlockState pState)
    {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.TORCH.get().defaultBlockState().setValue(LITSTATE,2).setValue(BURNTIME,INITIAL_BURN_TIME));
        if(SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos,this, TICK_INTERVAL);
        }
    }

    public void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime)
    {
        pLevel.setBlockAndUpdate(pPos,ModBlocks.TORCH.get().defaultBlockState().setValue(LITSTATE,1).setValue(BURNTIME,burnTime));
        if(SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
        }
    }

    public void changeToUnlit(Level pLevel,BlockPos pPos,BlockState pState)
    {
        if (SHOULD_BURN_OUT) {
            if (ClassiCraftConfiguration.noRelightEnabled.get() || ClassiCraftConfiguration.turnToStickEnabled.get()) {
                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
            }
            if (ClassiCraftConfiguration.turnToStickEnabled.get()) {
                ItemEntity itemEntity = new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), Items.STICK.getDefaultInstance());
                pLevel.addFreshEntity(itemEntity);
            }
        }else {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.TORCH.get().defaultBlockState());
        }
        pLevel.scheduleTick(pPos,this, TICK_INTERVAL);
    }

    public static ToIntFunction<BlockState> getLightLevelFromState()
    {
        return (state) ->{
            if(state.getValue(RealisticTorchBlock.LITSTATE) == 2)
            {
                return 14;
            }
            else if(state.getValue(RealisticTorchBlock.LITSTATE) == 1)
            {
                return 8;
            }
            else
            {
                return 0;
            }
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState state =Blocks.TORCH.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(!placeStack.is(ModItems.LIT_TORCH.get())) return state == null ? null:this.defaultBlockState();
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {

            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > INITIAL_BURN_TIME)
                {
                    return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
                }
                else if(burnTime <= 0)
                {
                    return  state == null ? null:this.defaultBlockState();
                }
                else
                {
                    return state == null ? null:this.defaultBlockState().setValue(BURNTIME,burnTime).setValue(LITSTATE,1);
                }
            }
            if(burnTime > INITIAL_BURN_TIME)
            {
                return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
            }
            else if(burnTime <= 0)
            {
                return  state == null ? null:this.defaultBlockState();
            }
            else
            {
                return state == null ? null:this.defaultBlockState().setValue(BURNTIME,burnTime).setValue(LITSTATE,2);
            }

        }
        else
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
            }
            return state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LITSTATE, BURNTIME);
    }
}
