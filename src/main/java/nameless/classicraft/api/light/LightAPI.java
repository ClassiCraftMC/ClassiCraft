package nameless.classicraft.api.light;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModItems;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public interface LightAPI {

    int LIT = 2;
    int SMOLDERING = 1;
    int UNLIT = 0;
    int TICK_INTERVAL = 1200;
    int TICK_RATE = 1200;
    int INITIAL_BURN_TIME = ClassiCraftConfiguration.torchBurnoutTime.get();
    boolean CAUSE_FIRE = ClassiCraftConfiguration.torchCauseFire.get();
    boolean TORCH_SHOULD_BURN_OUT = INITIAL_BURN_TIME >= 0;
    IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);
    int LANTERN_TOTAL_BURN_TIME = ClassiCraftConfiguration.lanternBurnoutTime.get();
    IntegerProperty TORCH_BURNTIME = IntegerProperty.create("burntime", 0, TORCH_SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 1);
    boolean LANTERN_SHOUD_BURN_OUT = LANTERN_TOTAL_BURN_TIME >= 0;
    IntegerProperty LANTERN_BURNTIME = IntegerProperty.create("burntime",0, LANTERN_SHOUD_BURN_OUT ? LANTERN_TOTAL_BURN_TIME:1);
    IntegerProperty OIL = IntegerProperty.create("oil",0,3);

    static IntegerProperty getLitState()
    {
        return LITSTATE;
    }

    default InteractionResult useLantern(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))){
                replaceLantern(pPos,pLevel,pState,pState.getValue(LANTERN_BURNTIME), UNLIT, pState.getValue(OIL), block);
            }
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand, block);
        } else if(heldStack.is(Items.HONEYCOMB))
        {
            if(pState.getValue(OIL) >= 3) return InteractionResult.PASS;
            if(!pPlayer.isCreative()){
                heldStack.getItem().getDefaultInstance().shrink(1);
            }
            replaceLantern(pPos,pLevel,pState,pState.getValue(LANTERN_BURNTIME),pState.getValue(LITSTATE),pState.getValue(OIL) + 1, block);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    static IntegerProperty getLanternBurnTime()
    {
        return LANTERN_BURNTIME;
    }

    default void tickLantern(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block) {
        if(!level.isClientSide() && LANTERN_SHOUD_BURN_OUT && state.getValue(LITSTATE) > UNLIT)
        {
            int newBurnTime = state.getValue(LANTERN_BURNTIME) -1;

            if(state.getValue(LANTERN_BURNTIME) <= 0) {
                if(state.getValue(OIL)>0)
                {
                    replaceLantern(pos,level,state,LANTERN_TOTAL_BURN_TIME,LIT,state.getValue(OIL)-1, block);
                    level.scheduleTick(pos,block,TICK_INTERVAL);
                    return;
                }
                replaceLantern(pos,level,state,UNLIT,0,state.getValue(OIL), block);
                playExtinguishSound(level, pos);
                return;
            }
            replaceLantern(pos,level,state,newBurnTime,LIT,state.getValue(OIL), block);
            level.scheduleTick(pos,block,TICK_INTERVAL);
        }
    }

    default InteractionResult useAsFlint(BlockState pState,Level pLevel,BlockPos pPos, Player pPlayer,InteractionHand pHand, Block block) {
        if(pState.getValue(OIL)<=0) {
            pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1,pLevel.random.nextFloat() * 0.1F + 0.3F);
            return InteractionResult.SUCCESS;
        }
        replaceLantern(pPos,pLevel,pState, LANTERN_TOTAL_BURN_TIME,LIT,pState.getValue(OIL), block);
        pLevel.updateNeighborsAt(pPos,block);
        pLevel.playSound(pPlayer,pPos,SoundEvents.FLINTANDSTEEL_USE,SoundSource.PLAYERS,1,0.9f);
        if(!pPlayer.isCreative()) {
            pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
        }
        return InteractionResult.SUCCESS;
    }

    default void replaceLantern(BlockPos pos, Level level, BlockState state, int burnTime, int litState, int oil, Block block) {
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(LANTERN_BURNTIME,burnTime).setValue(RealisticLanternBlock.HANGING,state.getValue(RealisticLanternBlock.HANGING)).setValue(RealisticLanternBlock.WATERLOGGED,state.getValue(RealisticLanternBlock.WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,block);
    }

    static void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    static void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    default InteractionResult useTorch(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, Item starter, Item unlit, Item lit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.hurtAndBreak(1, pPlayer, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(pHand);
                });
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToUnlit(pLevel, pPos, pState, block);
                    ModBlockProperties.playExtinguishSound(pLevel, pPos);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToUnlit(pLevel, pPos, pState, block);
                        ModBlockProperties.playExtinguishSound(pLevel, pPos);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState, block);
                        ModBlockProperties.playLightingSound(pLevel,pPos);
                    }
                    pLevel.updateNeighborsAt(pPos,block);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToUnlit(pLevel, pPos, pState, block);
                    ModBlockProperties.playExtinguishSound(pLevel,pPos);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState, block);
                }
                pLevel.updateNeighborsAt(pPos,block);
            }
            return InteractionResult.SUCCESS;
        }
        else if (pPlayer.getItemInHand(pHand).getItem() == starter) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.shrink(1);
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToUnlit(pLevel, pPos, pState, block);
                    ModBlockProperties.playExtinguishSound(pLevel, pPos);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToUnlit(pLevel, pPos, pState, block);
                        ModBlockProperties.playExtinguishSound(pLevel, pPos);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState, block);
                        ModBlockProperties.playLightingSound(pLevel,pPos);
                    }
                    pLevel.updateNeighborsAt(pPos,block);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToUnlit(pLevel, pPos, pState, block);
                    ModBlockProperties.playExtinguishSound(pLevel,pPos);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState, block);
                }
                pLevel.updateNeighborsAt(pPos, block);
            }
            return InteractionResult.SUCCESS;
        }
        else if( pState.getValue(LITSTATE) == 2 && pPlayer.getItemInHand(pHand).is(unlit))
        {
            pPlayer.setItemInHand(pHand, ItemStackAPI.replaceItemWithCopyNBTTagAndCountButResetBurnTime(pPlayer.getItemInHand(pHand), lit, INITIAL_BURN_TIME));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    default void randomTickTorch(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
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

    default void tickTorch(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block) {
        if (!level.isClientSide() && TORCH_SHOULD_BURN_OUT && state.getValue(LITSTATE) > UNLIT) {
            int newBurnTime = state.getValue(TORCH_BURNTIME) - 1;
            if (level.isRainingAt(pos.above())) {
                ModBlockProperties.playExtinguishSound(level, pos);
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
                ModBlockProperties.playExtinguishSound(level, pos);
                changeToUnlit(level, pos, state, block);
                level.updateNeighborsAt(pos, block);
            } else if (state.getValue(LITSTATE) == LIT && (newBurnTime <= INITIAL_BURN_TIME / 10 || newBurnTime <= 1)) {
                changeToSmoldering(level, pos, state, newBurnTime, block);
                level.updateNeighborsAt(pos, block);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(TORCH_BURNTIME, state.getValue(TORCH_BURNTIME) - 1));
                level.scheduleTick(pos, block, TICK_INTERVAL);
            }

        }
    }

    default void changeToLit(Level pLevel, BlockPos pPos, BlockState pState, Block block)
    {
        pLevel.setBlockAndUpdate(pPos, block.defaultBlockState().setValue(LITSTATE,2).setValue(TORCH_BURNTIME,INITIAL_BURN_TIME));
        if(TORCH_SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, block, TICK_INTERVAL);
        }
    }

    default void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime, Block block)
    {
        pLevel.setBlockAndUpdate(pPos, block.defaultBlockState().setValue(LITSTATE,1).setValue(TORCH_BURNTIME,burnTime));
        if(TORCH_SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, block, TICK_INTERVAL);
        }
    }

    default void changeToUnlit(Level pLevel,BlockPos pPos,BlockState pState, Block block)
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

    default BlockState getTorchStateForPlacement(BlockPlaceContext pContext, Block baseBlock, Block block) {
        BlockState state = baseBlock.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(!placeStack.is(ModItems.LIT_TORCH.get())) return state == null ? null:block.defaultBlockState();
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {

            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > INITIAL_BURN_TIME)
                {
                    return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
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
            if(burnTime > INITIAL_BURN_TIME)
            {
                return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
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
                return  state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
            }
            return state == null ? null:block.defaultBlockState().setValue(TORCH_BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
        }
    }
}
