package nameless.classicraft.util;

import nameless.classicraft.block.realistic.RealisticLanternBlock;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LightUtils {

    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;
    public static final int TICK_INTERVAL = 1200;
    public static final IntegerProperty OIL = IntegerProperty.create("oil",0,3);
    public static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);

    public static final int FUEL_LEVEL_I_TOTAL_BURN_TIME = 1;
    public static final int FUEL_LEVEL_II_TOTAL_BURN_TIME = 2;
    public static final int FUEL_LEVEL_IV_TOTAL_BURN_TIME = 4;
    public static final int FUEL_LEVEL_V_TOTAL_BURN_TIME = 5;

    public static final IntegerProperty FUEL_LEVEL_I_BURNTIME = IntegerProperty.create("burntime_1", 0, FUEL_LEVEL_I_TOTAL_BURN_TIME);
    public static final IntegerProperty FUEL_LEVEL_II_BURNTIME = IntegerProperty.create("burntime_2", 0, FUEL_LEVEL_II_TOTAL_BURN_TIME);
    public static final IntegerProperty FUEL_LEVEL_IV_BURNTIME = IntegerProperty.create("burntime_4", 0,FUEL_LEVEL_IV_TOTAL_BURN_TIME);
    public static final IntegerProperty FUEL_LEVEL_V_BURNTIME = IntegerProperty.create("burntime_5", 0, FUEL_LEVEL_V_TOTAL_BURN_TIME);

    public static InteractionResult useFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, TagKey<Item> fuelItem, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.is(fuelItem)) {
            if (!pPlayer.isCreative() && pState.getValue(OIL) != 3) {
                heldStack.shrink(1);
                if (pState.getValue(OIL) >= 3) return InteractionResult.PASS;
                pPlayer.swing(pHand);
                replaceBlockNeedFuel(pPos, pLevel, pState, pState.getValue(burnTime), pState.getValue(LITSTATE), pState.getValue(OIL) + 1, block, burnTime);
                pLevel.playSound(null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1, 0.3f * pLevel.random.nextFloat() * 0.1f);
            }
        }
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))) {
                replaceBlockNeedFuel(pPos, pLevel, pState, pState.getValue(burnTime), UNLIT, pState.getValue(OIL), block, burnTime);
            }
            return useAsFlint(pState, pLevel, pPos, pPlayer, pHand, block, burnTime, initialBurnTime);
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult useBlockNeedFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block) {
        useFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block, ModTags.Items.FUEL_LEVEL_1, FUEL_LEVEL_I_BURNTIME, FUEL_LEVEL_I_TOTAL_BURN_TIME);
        useFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block, ModTags.Items.FUEL_LEVEL_2, FUEL_LEVEL_II_BURNTIME, FUEL_LEVEL_II_TOTAL_BURN_TIME);
        useFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block, ModTags.Items.FUEL_LEVEL_4, FUEL_LEVEL_IV_BURNTIME, FUEL_LEVEL_IV_TOTAL_BURN_TIME);
        useFuel(pState, pLevel, pPos, pPlayer, pHand, pHit, block, ModTags.Items.FUEL_LEVEL_5, FUEL_LEVEL_V_BURNTIME, FUEL_LEVEL_V_TOTAL_BURN_TIME);
        return InteractionResult.PASS;
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
        if (state.getValue(FUEL_LEVEL_I_BURNTIME) != 0) {
            tickNeedFuel(state, level, pos, random, block, FUEL_LEVEL_I_BURNTIME, FUEL_LEVEL_I_TOTAL_BURN_TIME);
        }
        if (state.getValue(FUEL_LEVEL_II_BURNTIME) != 0) {
            tickNeedFuel(state, level, pos, random, block,  FUEL_LEVEL_II_BURNTIME, FUEL_LEVEL_II_TOTAL_BURN_TIME);
        }
        if (state.getValue(FUEL_LEVEL_IV_BURNTIME) != 0) {
            tickNeedFuel(state, level, pos, random, block, FUEL_LEVEL_IV_BURNTIME, FUEL_LEVEL_IV_TOTAL_BURN_TIME);
        }
        if (state.getValue(FUEL_LEVEL_V_BURNTIME) != 0) {
            tickNeedFuel(state, level, pos, random, block, FUEL_LEVEL_V_BURNTIME, FUEL_LEVEL_V_TOTAL_BURN_TIME);
        }
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
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(burnTime, initialBurnTime).setValue(RealisticLanternBlock.HANGING,state.getValue(RealisticLanternBlock.HANGING)).setValue(RealisticLanternBlock.WATERLOGGED,state.getValue(RealisticLanternBlock.WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,block);
    }

    public static void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public static void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }
}
