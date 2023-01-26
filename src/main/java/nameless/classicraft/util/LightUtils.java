package nameless.classicraft.util;

import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;

/**
 * @author wdog5
 */
public class LightUtils {

    protected static final int TICK_INTERVAL = ModBlockProperties.TICK_INTERVAL;

    public static void torchPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving, Block pBlock) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock()) {
            pBlock.defaultBlockState().updateIndirectNeighbourShapes(pLevel, pPos, 3);
        }
        if(pOldState.is(pBlock) && pState.getValue(AbstractLightBlock.getLitState())) {
            pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
        }
    }

    public static void tickTorch(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, Block pBlock, IntegerProperty burnTime) {
        if (!pLevel.isClientSide && pState.getValue(AbstractLightBlock.getLitState())) {
            int newBurnTime = pState.getValue(burnTime) - 1;
            if (pLevel.isRainingAt(pPos.above())) {
                playExtinguishSound(pLevel, pPos);
                newBurnTime -= pRandom.nextInt(20,35);
                if (newBurnTime <= 0) {
                    changeToStick(pLevel, pPos);
                } else {
                    changeToUnlit(pLevel, pPos, pBlock);
                }
                pLevel.updateNeighborsAt(pPos, pBlock);
            }
            if (newBurnTime <= 0) {
                playExtinguishSound(pLevel, pPos);
                changeToStick(pLevel, pPos);
                pLevel.updateNeighborsAt(pPos, pBlock);
            } else {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(burnTime, pState.getValue(burnTime) -1 ));
                pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
            }
        }
    }

    private static void changeToStick(Level pLevel, BlockPos pPos) {
        ItemEntity itemEntity =
                new ItemEntity(pLevel,
                        pPos.getX(),
                        pPos.getY(),
                        pPos.getZ(),
                        Items.STICK.getDefaultInstance());
        pLevel.addFreshEntity(itemEntity);
        pLevel.gameEvent(itemEntity, GameEvent.ENTITY_PLACE, pPos);
    }

    public static InteractionResult addFuel(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, int initialBurnTime, int fuelLevel, Block block, IntegerProperty burnTime) {
        int i = pState.getValue(AbstractLightBlock.getLevel());
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (ComposterBlock.COMPOSTABLES.containsKey(itemstack.getItem()) && !pLevel.isClientSide) {
            BlockState blockstate = addItem(pState, pLevel, pPos, itemstack);
            pLevel.levelEvent(1500, pPos, pState != blockstate ? 1 : 0);
            pPlayer.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            replaceBlockNeedFuel(pPos, pLevel, initialBurnTime, fuelLevel, block, burnTime);
        }
        return InteractionResult.SUCCESS;
    }

    public static void replaceBlockNeedFuel(BlockPos pos, Level level,int initialBurnTime, int fuelLevel, Block block, IntegerProperty burnTime) {
        level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(burnTime, initialBurnTime).setValue(AbstractLightBlock.getLitState(), true).setValue(AbstractLightBlock.getLevel(), fuelLevel));
        level.updateNeighborsAt(pos,block);
    }

    public static InteractionResult useAsFlint(BlockState pState,Level pLevel,BlockPos pPos, Player pPlayer,InteractionHand pHand, Block block, IntegerProperty totalBurnTime, int initialBurnTime) {
        if(pState.getValue(AbstractLightBlock.getLevel()) <= 0) {
            pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1,pLevel.random.nextFloat() * 0.1F + 0.3F);
            pPlayer.swing(pHand);
            return InteractionResult.SUCCESS;
        }
        replaceBlockNeedFuel(pPos, pLevel, initialBurnTime ,pState.getValue(AbstractLightBlock.getLevel()), block, totalBurnTime);
        pLevel.updateNeighborsAt(pPos,block);
        pLevel.playSound(pPlayer,pPos,SoundEvents.FLINTANDSTEEL_USE,SoundSource.PLAYERS,1,0.9f);
        if(!pPlayer.isCreative()) {
            pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
        }
        pPlayer.swing(pHand);
        return InteractionResult.SUCCESS;
    }

    public static void shiftItem(Player pPlayer, ItemStack pOldItem, Item pNewItem) {
        if (pPlayer.isShiftKeyDown()) {
            int oldCount = pOldItem.getCount();
            pNewItem.getDefaultInstance().setCount(oldCount);
            ItemStackAPI.replaceItemWitchNoNBT(pOldItem, pNewItem);
        }
    }

    static BlockState addItem(BlockState pState, LevelAccessor pLevel, BlockPos pPos, ItemStack pStack) {
        int i = pState.getValue(AbstractLightBlock.getLevel());
        float f = ComposterBlock.COMPOSTABLES.getFloat(pStack.getItem());
        if ((i != 0 || !(f > 0.0F)) && !(pLevel.getRandom().nextDouble() < (double)f)) {
            return pState;
        } else {
            int j = i + 1;
            BlockState blockstate = pState.setValue(AbstractLightBlock.getLevel(), j);
            pLevel.setBlock(pPos, blockstate, 3);
            if (j == 7) {
                pLevel.scheduleTick(pPos, pState.getBlock(), 20);
            }

            return blockstate;
        }
    }

    public static InteractionResult interactTorch(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, Block pBlock, Item fireStarter, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getMainHandItem();
        if (heldStack.is(fireStarter) && !pBlock.defaultBlockState().getValue(AbstractLightBlock.getLitState())) {
            playLightingSound(pLevel, pPos);
            pPlayer.swing(pHand);
            if (!pPlayer.isCreative()) {
                heldStack.hurtAndBreak(1, pPlayer, (player) -> {
                    player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }
            if (pLevel.isRainingAt(pPos.above())) {
                playExtinguishSound(pLevel, pPos);
                changeToUnlit(pLevel, pPos, pBlock);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_PLACE, pPos);
            } else {
                playLightingSound(pLevel, pPos);
                changeToLit(pLevel, pPos, pBlock, burnTime, initialBurnTime);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_PLACE, pPos);
            }
            pLevel.updateNeighborsAt(pPos, pBlock);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    public static void changeToLit(Level pLevel, BlockPos pPos, Block pBlock, IntegerProperty burnTime, int initialBurnTime) {
        pLevel.setBlockAndUpdate(pPos, pBlock.defaultBlockState().setValue(AbstractLightBlock.getLitState(),true).setValue(burnTime, initialBurnTime));
        pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
    }

    public static void changeToUnlit(Level pLevel, BlockPos pPos, Block pBlock) {
        pLevel.setBlockAndUpdate(pPos, pBlock.defaultBlockState());
        pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
    }

    public static void playExtinguishSound(Level pLevel,BlockPos pPos) {
        pLevel.playSound(null,pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,1, pLevel.random.nextFloat() * 0.1F + 0.9F);
    }

    public static void playLightingSound(Level pLevel,BlockPos pPos) {
        pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1, pLevel.random.nextFloat() * 0.1F + 0.9F);
    }
}
