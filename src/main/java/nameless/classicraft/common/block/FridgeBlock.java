package nameless.classicraft.common.block;

import nameless.classicraft.common.block.entity.FridgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author DustW
 */
public class FridgeBlock extends AbstractGuiEntityBlock<FridgeBlockEntity> {
    public static final BooleanProperty HAS_ENERGY = BooleanProperty.create("has_energy");
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    protected FridgeBlock(Properties properties, Supplier<BlockEntityType<FridgeBlockEntity>> supplier) {
        super(properties, supplier);
        registerDefaultState(this.stateDefinition.any()
                .setValue(HAS_ENERGY, false)
                .setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HAS_ENERGY, LIT);
    }

    static final Map<Item, Map.Entry<Item, Float>> MAP = Map.of(
            Items.WATER_BUCKET, new AbstractMap.SimpleEntry<>(Items.BUCKET, 5F),
            Items.POWDER_SNOW_BUCKET, new AbstractMap.SimpleEntry<>(Items.BUCKET, 2.5F),
            Items.SNOWBALL, new AbstractMap.SimpleEntry<>(Items.AIR, 2.5F),
            Items.SNOW, new AbstractMap.SimpleEntry<>(Items.AIR, 2F),
            Items.ICE, new AbstractMap.SimpleEntry<>(Items.AIR, 2F),
            Items.PACKED_ICE, new AbstractMap.SimpleEntry<>(Items.AIR, 18F),
            Items.BLUE_ICE, new AbstractMap.SimpleEntry<>(Items.AIR, 162F)
    );

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if (pLevel.getBlockEntity(pPos) instanceof FridgeBlockEntity fridge) {
                ItemStack itemInHand = pPlayer.getItemInHand(pHand);
                Map.Entry<Item, Float> itemFloatEntry = MAP.get(itemInHand.getItem());

                if (itemFloatEntry != null && fridge.energy.get() + itemFloatEntry.getValue() < 1000) {
                    itemInHand.shrink(1);
                    fridge.energy.set(fridge.energy.get() + itemFloatEntry.getValue());

                    if (itemFloatEntry.getKey() != Items.AIR)
                        pPlayer.addItem(new ItemStack(itemFloatEntry.getKey()));

                    return InteractionResult.SUCCESS;
                }
            }

            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(LIT);
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(LIT), 2);
                }
            }

        }
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (isLit(pState) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(LIT), 2);
        }
    }

    public static boolean isLit(BlockState state) {
        return state.getValue(LIT);
    }
}
