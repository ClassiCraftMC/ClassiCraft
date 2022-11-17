package nameless.classicraft.api.light;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public interface LightAPI {

    int LIT = 2;
    int SMOLDERING = 1;
    int UNLIT = 0;
    int TICK_INTERVAL = 1200;
    int TICK_RATE = 1200;
    IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);
    int LANTERN_TOTAL_BURN_TIME = ClassiCraftConfiguration.lanternBurnoutTime.get();
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
}
