package nameless.classicraft.util;

import nameless.classicraft.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LightUtils {

    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;
    public static final int TICK_INTERVAL = 1200;
    public static final BooleanProperty BE_HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty BE_WATERLOGGED = BooleanProperty.create("waterlogged");
    public static final IntegerProperty OIL = IntegerProperty.create("oil",0,3);
    public static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);

    public static int TOTAL_BURN_TIME = 2880;
    public static IntegerProperty BURNTIME = IntegerProperty.create("burntime", 0, TOTAL_BURN_TIME);

    public static InteractionResult addFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (pState.getValue(OIL) >= 3) return InteractionResult.PASS;
        recoginzeFuel(pState, pLevel, pPos, pPlayer, pHand, block, heldStack, ModTags.Items.FUEL_LEVEL_1, 1, 2, 3);
        recoginzeFuel(pState, pLevel, pPos, pPlayer, pHand, block, heldStack, ModTags.Items.FUEL_LEVEL_2, 2, 4, 6);
        return InteractionResult.PASS;
    }

    private static void recoginzeFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, Block block, ItemStack heldStack, TagKey<Item> fuelTag, int level1, int level2, int level3) {
        if (heldStack.is(fuelTag)) {
            if (!pPlayer.isCreative()) {
                heldStack.shrink(1);
            }
            if (pState.getValue(OIL) == 1) {
                pPlayer.swing(pHand);
                replaceBlockNeedFuel(pPos, pLevel, pState, level1, pState.getValue(LITSTATE), pState.getValue(OIL) + 1, block, BURNTIME);
                pLevel.playSound(null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1, 0.3f * pLevel.random.nextFloat() * 0.1f);
            }
            if (pState.getValue(OIL) == 2) {
                pPlayer.swing(pHand);
                replaceBlockNeedFuel(pPos, pLevel, pState, level2, pState.getValue(LITSTATE), pState.getValue(OIL) + 1, block, BURNTIME);
                pLevel.playSound(null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1, 0.3f * pLevel.random.nextFloat() * 0.1f);
            }
            if (pState.getValue(OIL) == 3) {
                pPlayer.swing(pHand);
                replaceBlockNeedFuel(pPos, pLevel, pState, level3, pState.getValue(LITSTATE), pState.getValue(OIL) + 1, block, BURNTIME);
                pLevel.playSound(null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1, 0.3f * pLevel.random.nextFloat() * 0.1f);
            }
        }
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))) {
                if (pState.getValue(BURNTIME) == level1) {
                    replaceBlockNeedFuel(pPos, pLevel, pState, level1, UNLIT, pState.getValue(OIL), block, BURNTIME);
                } else {
                    useAsFlint(pState, pLevel, pPos, pPlayer, pHand, block, BURNTIME, level1);
                }
                if (pState.getValue(BURNTIME) == level2) {
                    replaceBlockNeedFuel(pPos, pLevel, pState, level2, UNLIT, pState.getValue(OIL), block, BURNTIME);
                } else {
                    useAsFlint(pState, pLevel, pPos, pPlayer, pHand, block, BURNTIME, level2);
                }
                if (pState.getValue(BURNTIME) == level3) {
                    replaceBlockNeedFuel(pPos, pLevel, pState, level3, UNLIT, pState.getValue(OIL), block, BURNTIME);
                } else {
                    useAsFlint(pState, pLevel, pPos, pPlayer, pHand, block, BURNTIME, level3);
                }
            }
        }
    }

    public static InteractionResult useFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block) {
        return addFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block);
    }

    public static InteractionResult useBlockNeedFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block) {
        return useFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block);
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

    public static void tickBlockNeedFuel(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block) {

    }

    public static void tickNeedFuel(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block, IntegerProperty totalBurnTime, int initialBurnTime) {
        if(!level.isClientSide() && state.getValue(LITSTATE) > UNLIT)
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

    public static void replaceBlockNeedFuel(BlockPos pos, Level level, BlockState state, int initialBurnTime, int litState, int oil, Block block, IntegerProperty burnTime) {
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(burnTime, initialBurnTime).setValue(BE_HANGING, state.getValue(BE_HANGING)).setValue(BE_WATERLOGGED,state.getValue(BE_WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,block);
    }

    public static void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public static void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }
}
