package nameless.classicraft.util;

import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class LightUtils {

    protected static final int TICK_INTERVAL = ModBlockProperties.TICK_INTERVAL;

    public static void torchPlace(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving, Block block) {
        if(!isMoving && state.getBlock() != newState.getBlock()) {
            block.getDefaultState().updateNeighbors(world, pos, 3);
        }
        if(newState.isOf(block) && state.get(AbstractLightBlock.getLitState())) {
            world.scheduleBlockTick(pos, block, TICK_INTERVAL);
        }
    }

    public static void tickTorch(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom, Block pBlock, IntProperty burnTime) {
        if (!pLevel.isClient && pState.get(AbstractLightBlock.getLitState())) {
            int newBurnTime = pState.get(burnTime) - 1;
            if (pLevel.hasRain(pPos.up())) {
                playExtinguishSound(pLevel, pPos);
                newBurnTime -= pRandom.nextBetween(20, 35);
                if (newBurnTime <= 0) {
                    changeToStick(pLevel, pPos);
                } else {
                    changeToUnlit(pLevel, pPos, pBlock);
                }
                pLevel.updateNeighbors(pPos, pBlock);
            }
            if (newBurnTime <= 0) {
                playExtinguishSound(pLevel, pPos);
                changeToStick(pLevel, pPos);
                pLevel.updateNeighbors(pPos, pBlock);
            } else {
                pLevel.setBlockState(pPos, pState.with(burnTime, pState.get(burnTime) -1 ));
                pLevel.scheduleBlockTick(pPos, pBlock, TICK_INTERVAL);
            }
        }
    }

    private static void changeToStick(World pLevel, BlockPos pPos) {
        ItemEntity itemEntity =
                new ItemEntity(pLevel,
                        pPos.getX(),
                        pPos.getY(),
                        pPos.getZ(),
                        Items.STICK.getDefaultStack());
        pLevel.spawnEntity(itemEntity);
        pLevel.emitGameEvent(itemEntity, GameEvent.ENTITY_PLACE, pPos);
    }

    public static ActionResult addFuel(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, int initialBurnTime, int fuelLevel, Block block, IntProperty burnTime) {
        int i = pState.get(AbstractLightBlock.getLevel());
        ItemStack itemstack = pPlayer.getStackInHand(pHand);
        if (ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.containsKey(itemstack.getItem()) && !pLevel.isClient) {
            BlockState blockstate = addItem(pState, pLevel, pPos, itemstack);
            pLevel.syncWorldEvent(1500, pPos, pState != blockstate ? 1 : 0);
            pPlayer.increaseTravelMotionStats(Stats.ITEM_USED.get(itemstack.getItem()));
            if (!pPlayer.isCreative()) {
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

    public static ActionResult interactTorch(World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, Block pBlock, Item fireStarter, IntProperty burnTime, int initialBurnTime) {
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
