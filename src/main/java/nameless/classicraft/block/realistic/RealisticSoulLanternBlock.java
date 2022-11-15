package nameless.classicraft.block.realistic;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class RealisticSoulLanternBlock extends LanternBlock {

    public static final int LIT = 1;
    public static final int UNLIT = 0;
    public static final int TICK_INTERVAL = 1200;
    public static final int TOTAL_BURN_TIME = ClassiCraftConfiguration.lanternBurnoutTime.get();
    protected static final boolean SHOUD_BURN_OUT = TOTAL_BURN_TIME >= 0;
    public static final IntegerProperty BURNTIME = IntegerProperty.create("burntime",0,SHOUD_BURN_OUT ? TOTAL_BURN_TIME:1);
    public static final IntegerProperty LITSTATE = IntegerProperty.create("litstate",0,1);

    public static final IntegerProperty OIL = IntegerProperty.create("oil",0,3);

    public RealisticSoulLanternBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {
            return RealisticSoulLanternBlock.getLitState(state);
        }).noOcclusion());
        registerDefaultState(defaultBlockState().setValue(LITSTATE,0).setValue(BURNTIME,0).setValue(OIL,0).setValue(OIL,0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand);
        } else if(heldStack.is(Items.COAL) || heldStack.is(Items.CHARCOAL))
        {
            if(pState.getValue(OIL) >= 3) return InteractionResult.PASS;
            if(!pPlayer.isCreative()){
                heldStack.getItem().getDefaultInstance().shrink(1);
            }
            replaceLantern(pPos,pLevel,pState,pState.getValue(BURNTIME),pState.getValue(LITSTATE),pState.getValue(OIL) + 1);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        else if(pPlayer.getMainHandItem().getItem() == Items.AIR)
        {
            ItemStack stack;
            if(pState.getValue(LITSTATE) == LIT){
                stack = new ItemStack(ModItems.LIT_SOUL_LANTERN.get());
            }
            else
                stack = new ItemStack(ModItems.SOUL_LANTERN.get());
            stack.getOrCreateTag().putInt("oil",pState.getValue(OIL));
            stack.getOrCreateTag().putInt("burnTime",pState.getValue(BURNTIME));
            stack.getOrCreateTag().putInt("lit_state",pState.getValue(LITSTATE));
            pPlayer.setItemSlot(EquipmentSlot.MAINHAND,stack);
            pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
            pLevel.playSound(null,pPos,SoundEvents.LANTERN_BREAK,SoundSource.BLOCKS,1,0.9f *pLevel.random.nextFloat()*0.1f);
            pLevel.updateNeighborsAt(pPos,this);
        }
        return super.use(pState,pLevel,pPos,pPlayer,pHand,pHit);
    }

    private InteractionResult useAsFlint(BlockState pState,Level pLevel,BlockPos pPos, Player pPlayer,InteractionHand pHand) {
        if(pState.getValue(OIL)<=0) {
            pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1,pLevel.random.nextFloat() * 0.1F + 0.3F);
            return InteractionResult.SUCCESS;
        }
        replaceLantern(pPos,pLevel,pState,TOTAL_BURN_TIME,LIT,pState.getValue(OIL));
        pLevel.updateNeighborsAt(pPos,this);
        pLevel.playSound(pPlayer,pPos,SoundEvents.FLINTANDSTEEL_USE,SoundSource.PLAYERS,1,0.9f);
        if(!pPlayer.isCreative())
            pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.isClientSide() && SHOUD_BURN_OUT && state.getValue(LITSTATE) > UNLIT)
        {
            int newBurnTime = state.getValue(BURNTIME) -1;

            if(state.getValue(BURNTIME) <= 0) {
                if(state.getValue(OIL)>0)
                {
                    replaceLantern(pos,level,state,TOTAL_BURN_TIME,LIT,state.getValue(OIL)-1);
                    level.scheduleTick(pos,this,TICK_INTERVAL);
                    return;
                }
                replaceLantern(pos,level,state,UNLIT,0,state.getValue(OIL));
                playExtinguishSound(pos, level);
                return;
            }
            replaceLantern(pos,level,state,newBurnTime,LIT,state.getValue(OIL));
            level.scheduleTick(pos,this,TICK_INTERVAL);
        }
    }

    private void replaceLantern(BlockPos pos,Level level,BlockState state,int burnTime,int litState,int oil) {
        level.setBlockAndUpdate(pos, ModBlocks.SOUL_LANTERN.get().defaultBlockState().setValue(BURNTIME,burnTime).setValue(RealisticSoulLanternBlock.HANGING,state.getValue(RealisticSoulLanternBlock.HANGING)).setValue(RealisticSoulLanternBlock.WATERLOGGED,state.getValue(RealisticSoulLanternBlock.WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,this);
    }

    private void playExtinguishSound(BlockPos pos,Level level)
    {
        level.playSound(null,pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock()) {
            defaultBlockState().updateIndirectNeighbourShapes(pLevel,pPos,3);
        }
        if(SHOUD_BURN_OUT&& pState.getBlock() instanceof RealisticSoulLanternBlock &&pState.getValue(LITSTATE) > UNLIT)
            pLevel.scheduleTick(pPos,this,TICK_INTERVAL);
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    private static int getLitState(BlockState state)
    {
        if(state.getValue(RealisticSoulLanternBlock.LITSTATE) == 0) {
            return 0;
        } else {
            return 15;
        }
    }

    public static IntegerProperty getBurnTime()
    {
        return BURNTIME;
    }

    public static IntegerProperty getLitState()
    {
        return LITSTATE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BURNTIME,LITSTATE,OIL);
        super.createBlockStateDefinition(pBuilder);
    }
}
