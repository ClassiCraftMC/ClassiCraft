package nameless.classicraft.util;

import nameless.classicraft.block.realistic.RealisticLanternBlock;
import nameless.classicraft.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
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

    int LIT = 2;
    int SMOLDERING = 1;
    int UNLIT = 0;
    IntegerProperty OIL = IntegerProperty.create("oil",0,3);
    IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);

    public static final int FUEL_LEVEL_I_TOTAL_BURN_TIME = 1;
    public static final int FUEL_LEVEL_II_TOTAL_BURN_TIME = 2;

    public static final boolean FUEL_LEVEL_I_SHOULD_BURN_OUT = FUEL_LEVEL_I_TOTAL_BURN_TIME >= 0;


    IntegerProperty FUEL_LEVEL_I_BURNTIME = IntegerProperty.create("burntime", 0, FUEL_LEVEL_I_SHOULD_BURN_OUT ? FUEL_LEVEL_I_TOTAL_BURN_TIME : 1);

    public InteractionResult useFlint(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, TagKey<Item> fuelItem, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.getItem() == Items.FLINT_AND_STEEL) {
            if (pLevel.isRainingAt(pPos.above(1))){
                replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime), UNLIT, pState.getValue(OIL), block, burnTime);
            }
            return useAsFlint(pState,pLevel,pPos,pPlayer,pHand, block, burnTime, initialBurnTime);
        } else if(heldStack.is(fuelItem))
        {
            if(!pPlayer.isCreative() && pState.getValue(OIL) != 3){
                heldStack.shrink(1);
            }
            if(pState.getValue(OIL) >= 3) return InteractionResult.PASS;
            pPlayer.swing(pHand);
            replaceBlockNeedFuel(pPos,pLevel,pState,pState.getValue(burnTime),pState.getValue(LITSTATE),pState.getValue(OIL) + 1, block, burnTime);
            pLevel.playSound(null,pPos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,1,0.3f*pLevel.random.nextFloat()*0.1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public InteractionResult useBlockNeedFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, Block block, TagKey<Item> fuelItem, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getItemInHand(pHand);
        if (heldStack.is(ModTags.Items.OIL_FUEL)) {
            useFlint(pState, pLevel, pPos, pPlayer, pHand, pHit, block, fuelItem, FUEL_LEVEL_I_BURNTIME, FUEL_LEVEL_I_TOTAL_BURN_TIME);
        }
        return InteractionResult.PASS;
    }

    public InteractionResult useAsFlint(BlockState pState,Level pLevel,BlockPos pPos, Player pPlayer,InteractionHand pHand, Block block, IntegerProperty totalBurnTime, int initialBurnTime) {
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

    public void replaceBlockNeedFuel(BlockPos pos, Level level, BlockState state, int initialBurnTime, int litState, int oil, Block block, IntegerProperty burnTime) {
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(burnTime, initialBurnTime).setValue(RealisticLanternBlock.HANGING,state.getValue(RealisticLanternBlock.HANGING)).setValue(RealisticLanternBlock.WATERLOGGED,state.getValue(RealisticLanternBlock.WATERLOGGED)).setValue(LITSTATE,litState).setValue(OIL,oil));
        level.updateNeighborsAt(pos,block);
    }
}
