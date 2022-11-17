package nameless.classicraft.api.light;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public interface CandleLightAPI {

    int TICK_INTERVAL = 1200;
    int CANDLE_INITIAL_BURN_TIME = 30;
    boolean CANDLE_SHOULD_BURN_OUT =CANDLE_INITIAL_BURN_TIME > 0;
    IntegerProperty CANDLE_BURNTIME = IntegerProperty.create("burntime", 0, CANDLE_SHOULD_BURN_OUT ? CANDLE_INITIAL_BURN_TIME : 1);

    default void tickCandle(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, Block block) {
        if (!level.isClientSide() && CANDLE_SHOULD_BURN_OUT && state.getValue(AbstractCandleBlock.LIT)) {
            int newBurnTime = state.getValue(CANDLE_BURNTIME) - 1;
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
            } else if (state.getValue(AbstractCandleBlock.LIT) && (newBurnTime <= CANDLE_INITIAL_BURN_TIME / 10 || newBurnTime <= 1)) {
                changeToSmoldering(level, pos, state, newBurnTime, block);
                level.updateNeighborsAt(pos, block);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(CANDLE_BURNTIME, state.getValue(CANDLE_BURNTIME) - 1));
                level.scheduleTick(pos, block, TICK_INTERVAL);
            }

        }
    }

    default void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime, Block block)
    {
        pLevel.setBlockAndUpdate(pPos, block.defaultBlockState().setValue(AbstractCandleBlock.LIT, true).setValue(CANDLE_BURNTIME,burnTime));
        if(CANDLE_SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, block, TICK_INTERVAL);
        }
    }

    default void changeToUnlit(Level pLevel, BlockPos pPos, BlockState pState, Block block)
    {
        if (CANDLE_SHOULD_BURN_OUT) {
            if (ClassiCraftConfiguration.noRelightEnabled.get()) {
                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
            }
        }else {
            pLevel.setBlockAndUpdate(pPos, block.defaultBlockState());
        }
        pLevel.scheduleTick(pPos,block, TICK_INTERVAL);
    }

    default BlockState getCandleStateForPlacement(BlockPlaceContext pContext, Block baseBlock, Block block) {
        BlockState state = baseBlock.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {

            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > CANDLE_INITIAL_BURN_TIME)
                {
                    return  state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME, CANDLE_INITIAL_BURN_TIME).setValue(AbstractCandleBlock.LIT, true);
                }
                else if(burnTime <= 0)
                {
                    return  state == null ? null:block.defaultBlockState();
                }
                else
                {
                    return state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME,burnTime).setValue(AbstractCandleBlock.LIT, true);
                }
            }
            if(burnTime > CANDLE_INITIAL_BURN_TIME)
            {
                return  state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME, CANDLE_INITIAL_BURN_TIME).setValue(AbstractCandleBlock.LIT,true);
            }
            else if(burnTime <= 0)
            {
                return  state == null ? null:block.defaultBlockState();
            }
            else
            {
                return state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME,burnTime).setValue(AbstractCandleBlock.LIT,true);
            }

        }
        else
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                return  state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME, CANDLE_INITIAL_BURN_TIME).setValue(AbstractCandleBlock.LIT,true);
            }
            return state == null ? null:block.defaultBlockState().setValue(CANDLE_BURNTIME, CANDLE_INITIAL_BURN_TIME).setValue(AbstractCandleBlock.LIT,true);
        }
    }
}
