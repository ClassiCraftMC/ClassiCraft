package nameless.classicraft.util;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class LightUtils {

    //int value
    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;
    public static final int TICK_INTERVAL = 1200;
    public static final int CAMPFIRE_INITIAL_BURN_TIME = ClassiCraftConfiguration.campfireBurnoutTime.get();
    public static final int LARGE_FIRE_BOWL_INITIAL_BURN_TIME = ClassiCraftConfiguration.largeFireBowlBurnoutTime.get();
    public static final int FIRE_BOWL_INITIAL_BURN_TIME = ClassiCraftConfiguration.fireBowlBurnoutTime.get();
    public static final int LANTERN_TOTAL_BURN_TIME = ClassiCraftConfiguration.lanternBurnoutTime.get();
    public static final int TORCH_INITIAL_BURN_TIME = ClassiCraftConfiguration.torchBurnoutTime.get();

    //boolean value
    public static final boolean CAMPFIRE_SHOULD_BURN_OUT = CAMPFIRE_INITIAL_BURN_TIME > 0;
    public static final boolean LARGE_FIRE_BOWL_SHOULD_BURN_OUT = LARGE_FIRE_BOWL_INITIAL_BURN_TIME > 0;
    public static final boolean TORCH_SHOULD_BURN_OUT = TORCH_INITIAL_BURN_TIME >= 0;
    public static final boolean LANTERN_SHOUD_BURN_OUT = LANTERN_TOTAL_BURN_TIME >= 0;
    public static final boolean FIRE_BOWL_SHOULD_BURN_OUT = FIRE_BOWL_INITIAL_BURN_TIME > 0;
    public static final boolean CAUSE_FIRE = ClassiCraftConfiguration.torchCauseFire.get();

    //IntegerProperty
    public static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);
    public static final IntegerProperty TORCH_BURNTIME = IntegerProperty.create("burntime", 0, TORCH_SHOULD_BURN_OUT ? TORCH_INITIAL_BURN_TIME : 1);
    public static final IntegerProperty LANTERN_BURNTIME = IntegerProperty.create("burntime",0, LANTERN_SHOUD_BURN_OUT ? LANTERN_TOTAL_BURN_TIME:1);
    public static final IntegerProperty OIL = IntegerProperty.create("oil",0,4);
    public static final IntegerProperty FIRE_BOWL_BURNTIME = IntegerProperty.create("burntime", 0, FIRE_BOWL_SHOULD_BURN_OUT ? FIRE_BOWL_INITIAL_BURN_TIME : 1);
    public static final IntegerProperty LARGE_FIRE_BOWL_BURNTIME = IntegerProperty.create("burntime", 0, LARGE_FIRE_BOWL_SHOULD_BURN_OUT ? LARGE_FIRE_BOWL_INITIAL_BURN_TIME : 1);
    public static final IntegerProperty CAMPFIRE_BURNTIME = IntegerProperty.create("burntime", 0, CAMPFIRE_SHOULD_BURN_OUT ? CAMPFIRE_INITIAL_BURN_TIME : 1);
    public static final IntegerProperty FUEL_VALUE = IntegerProperty.create("fuel_value", 0, 200);

    // BooleanProperty
    public static final BooleanProperty BE_HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty BE_WATERLOGGED = BooleanProperty.create("waterlogged");

    public static IntegerProperty getLitState()
    {
        return LITSTATE;
    }

    public static InteractionResult useLantern(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))){
                replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime), UNLIT, pState.getValue(OIL), block, burnTime);
            }
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand, block, burnTime, initialBurnTime);
        } else if(heldStack.is(Items.HONEYCOMB))
        {
            if(!pPlayer.isCreative() && pState.getValue(OIL) != 3){
                heldStack.shrink(1);
            }
            if(pState.getValue(OIL) >= 4) return InteractionResult.PASS;
            pPlayer.swing(pHand);
            replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime),pState.getValue(LITSTATE),pState.getValue(OIL) + 1, block, burnTime);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult useBlockNeedFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))){
                replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime), UNLIT, pState.getValue(OIL), block, burnTime);
            }
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand, block, burnTime, initialBurnTime);
        } else if(heldStack.is(Items.CHARCOAL) || heldStack.is(Items.COAL))
        {
            if(!pPlayer.isCreative() && pState.getValue(OIL) != 3){
                heldStack.shrink(1);
            }
            if(pState.getValue(OIL) >= 4) return InteractionResult.PASS;
            pPlayer.swing(pHand);
            replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime),pState.getValue(LITSTATE),pState.getValue(OIL) + 1, block, burnTime);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    /**
    public static InteractionResult useBlockNeedFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))){
                replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime), UNLIT, pState.getValue(OIL), block, burnTime);
            }
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand, block, burnTime, initialBurnTime);
        }
        if(heldStack.is(ModTags.Items.FUEL_LEVEL_1)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_2)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_4)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_5)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_6)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_7)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_8)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_9)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_10)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_12)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_14)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_16)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_17)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_19)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_18)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_25)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_26)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_27)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_36)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_72)
                || heldStack.is(ModTags.Items.FUEL_LEVEL_144)) {
            if(!pPlayer.isCreative() && pState.getValue(OIL) != 4){
                heldStack.shrink(1);
            }
            if(pState.getValue(OIL) >= 4) return InteractionResult.PASS;
            pPlayer.swing(pHand);
            replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime),pState.getValue(LITSTATE),pState.getValue(OIL) + 1, block, burnTime);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }*/

    public static IntegerProperty getLanternBurnTime()
    {
        return LANTERN_BURNTIME;
    }

    public static int getTorchInitialBurnTime() {
        return TORCH_SHOULD_BURN_OUT ? TORCH_INITIAL_BURN_TIME : 0;
    }

    public static void tickBlockNeedFuel(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block, boolean shouldBurnOut, IntegerProperty totalBurnTime, int initialBurnTime) {
        if(!level.isClientSide() && shouldBurnOut && state.getValue(LITSTATE) > UNLIT)
        {
            int newBurnTime = state.getValue(totalBurnTime) -1;

            if(state.getValue(totalBurnTime) <= 0) {
                if(state.getValue(OIL)>0)
                {
                    replaceBlockNeedFuel(pos,level,state, LIT,state.getValue(OIL)-1, initialBurnTime, block, totalBurnTime);
                    level.scheduleTick(pos,block,TICK_INTERVAL);
                    return;
                }
                replaceBlockNeedFuel(pos,level,state,UNLIT,0,state.getValue(OIL), block, totalBurnTime);
                playExtinguishSound(level, pos);
                return;
            }
            replaceBlockNeedFuel(pos,level,state,newBurnTime,LIT,state.getValue(OIL), block, totalBurnTime);
            level.scheduleTick(pos,block,TICK_INTERVAL);
        }
    }

    public static InteractionResult useAsFlint(BlockState pState,Level pLevel,BlockPos pPos, Player pPlayer,InteractionHand pHand, Block block, IntegerProperty totalBurnTime, int initialBurnTime) {
        if(pState.getValue(OIL)<=0) {
            pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1,pLevel.random.nextFloat() * 0.1F + 0.3F);
            pPlayer.swing(pHand);
            return InteractionResult.SUCCESS;
        }
        replaceBlockNeedFuel(pPos,pLevel,pState, initialBurnTime, LIT,pState.getValue(OIL), block, totalBurnTime);
        pLevel.updateNeighborsAt(pPos,block);
        pLevel.playSound(pPlayer,pPos,SoundEvents.FLINTANDSTEEL_USE,SoundSource.PLAYERS,1,0.9f);
        if(!pPlayer.isCreative()) {
            pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
        }
        pPlayer.swing(pHand);
        return InteractionResult.SUCCESS;
    }

    public static void replaceBlockNeedFuel(BlockPos pos, Level level, BlockState state, int initialBurnTime, int litState, int oil, Block block, IntegerProperty burnTime) {
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(burnTime, initialBurnTime).setValue(RealisticLanternBlock.HANGING,state.getValue(RealisticLanternBlock.HANGING)).setValue(RealisticLanternBlock.WATERLOGGED,state.getValue(RealisticLanternBlock.WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,block);
    }

    public static void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public static void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public static InteractionResult useTorch(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, Item starter, Item unlit, Item lit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.hurtAndBreak(1, pPlayer, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(pHand);
                });
                pPlayer.swing(pHand);
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToUnlit(pLevel, pPos, pState, block);
                    playExtinguishSound(pLevel, pPos);
                    pPlayer.swing(pHand);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToUnlit(pLevel, pPos, pState, block);
                        playExtinguishSound(pLevel, pPos);
                        pPlayer.swing(pHand);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState, block);
                        playLightingSound(pLevel,pPos);
                        pPlayer.swing(pHand);
                    }
                    pLevel.updateNeighborsAt(pPos,block);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToUnlit(pLevel, pPos, pState, block);
                    playExtinguishSound(pLevel,pPos);
                    pPlayer.swing(pHand);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState, block);
                    pPlayer.swing(pHand);
                }
                pLevel.updateNeighborsAt(pPos,block);
            }
            return InteractionResult.SUCCESS;
        }
        else if (pPlayer.getItemInHand(pHand).getItem() == starter) {
            playLightingSound(pLevel, pPos);
            pPlayer.swing(pHand);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.shrink(1);
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToUnlit(pLevel, pPos, pState, block);
                    playExtinguishSound(pLevel, pPos);
                    pPlayer.swing(pHand);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToUnlit(pLevel, pPos, pState, block);
                        playExtinguishSound(pLevel, pPos);
                        pPlayer.swing(pHand);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState, block);
                        playLightingSound(pLevel,pPos);
                        pPlayer.swing(pHand);
                    }
                    pLevel.updateNeighborsAt(pPos,block);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToUnlit(pLevel, pPos, pState, block);
                    playExtinguishSound(pLevel,pPos);
                    pPlayer.swing(pHand);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState, block);
                    pPlayer.swing(pHand);
                }
                pLevel.updateNeighborsAt(pPos, block);
            }
            return InteractionResult.SUCCESS;
        }
        else if( pState.getValue(LITSTATE) == 2 && pPlayer.getItemInHand(pHand).is(unlit))
        {
            pPlayer.setItemInHand(pHand, ItemStackAPI.replaceItemWithCopyNBTTagAndCountButResetBurnTime(pPlayer.getItemInHand(pHand), lit, TORCH_INITIAL_BURN_TIME));
            pPlayer.swing(pHand);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static void randomTickTorch(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
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

    public static void tickTorch(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block) {
        if (!level.isClientSide() && TORCH_SHOULD_BURN_OUT && state.getValue(LITSTATE) > UNLIT) {
            int newBurnTime = state.getValue(TORCH_BURNTIME) - 1;
            if (level.isRainingAt(pos.above())) {
                playExtinguishSound(level, pos);
                newBurnTime -= random.nextInt(20, 35);
                if (newBurnTime <= 0)
                    changeToUnlit(level, pos, state, block);
                else
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                ItemEntity newItem = new ItemEntity(
                        level,
                        pos.getX(), pos.getY(),
                        pos.getZ(),
                        Items.STICK.getDefaultInstance());
                level.addFreshEntity(newItem);
                level.updateNeighborsAt(pos, block);
                return;
            }
            if (newBurnTime <= 0) {
                playExtinguishSound(level, pos);
                changeToUnlit(level, pos, state, block);
                level.updateNeighborsAt(pos, block);
            } else if (state.getValue(LITSTATE) == LIT && (newBurnTime <= TORCH_INITIAL_BURN_TIME / 10 || newBurnTime <= 1)) {
                changeToSmoldering(level, pos, state, newBurnTime, block);
                level.updateNeighborsAt(pos, block);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(TORCH_BURNTIME, state.getValue(TORCH_BURNTIME) - 1));
                level.scheduleTick(pos, block, TICK_INTERVAL);
            }

        }
    }

    public static void changeToLit(Level pLevel, BlockPos pPos, BlockState pState, Block block)
    {
        pLevel.setBlockAndUpdate(pPos, block.defaultBlockState().setValue(LITSTATE,2).setValue(TORCH_BURNTIME, TORCH_INITIAL_BURN_TIME));
        if(TORCH_SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, block, TICK_INTERVAL);
        }
    }

    public static void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime, Block block)
    {
        pLevel.setBlockAndUpdate(pPos, block.defaultBlockState().setValue(LITSTATE,1).setValue(TORCH_BURNTIME,burnTime));
        if(TORCH_SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, block, TICK_INTERVAL);
        }
    }

    public static void changeToUnlit(Level pLevel,BlockPos pPos,BlockState pState, Block block)
    {
        if (TORCH_SHOULD_BURN_OUT) {
            if (ClassiCraftConfiguration.noRelightEnabled.get() || ClassiCraftConfiguration.turnToStickEnabled.get()) {
                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
            }
            if (ClassiCraftConfiguration.turnToStickEnabled.get()) {
                ItemEntity itemEntity = new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), Items.STICK.getDefaultInstance());
                pLevel.addFreshEntity(itemEntity);
            }
        }else {
            pLevel.setBlockAndUpdate(pPos, block.defaultBlockState());
        }
        pLevel.scheduleTick(pPos,block, TICK_INTERVAL);
    }

    public static BlockState getTorchStateForPlacement(BlockPlaceContext pContext, Block baseBlock, Block block) {
        BlockState state = baseBlock.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(!placeStack.is(ModItems.LIT_TORCH.get())) return state == null ? null:block.defaultBlockState();
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {

            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > TORCH_INITIAL_BURN_TIME)
                {
                    return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME, TORCH_INITIAL_BURN_TIME).setValue(LITSTATE,1);
                }
                else if(burnTime <= 0)
                {
                    return  state == null ? null:block.defaultBlockState();
                }
                else
                {
                    return state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,burnTime).setValue(LITSTATE,1);
                }
            }
            if(burnTime > TORCH_INITIAL_BURN_TIME)
            {
                return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME, TORCH_INITIAL_BURN_TIME).setValue(LITSTATE,2);
            }
            else if(burnTime <= 0)
            {
                return  state == null ? null:block.defaultBlockState();
            }
            else
            {
                return state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,burnTime).setValue(LITSTATE,2);
            }

        }
        else
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME, TORCH_INITIAL_BURN_TIME).setValue(LITSTATE,1);
            }
            return state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME, TORCH_INITIAL_BURN_TIME).setValue(LITSTATE,2);
        }
    }
}
