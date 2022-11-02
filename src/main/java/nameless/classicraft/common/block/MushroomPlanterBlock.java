package nameless.classicraft.common.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import nameless.classicraft.common.block.entity.MushroomPlanterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author DustW
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class MushroomPlanterBlock extends AbstractEntityBlock<MushroomPlanterBlockEntity> {

    public static final IntegerProperty GROW_STATE = IntegerProperty.create("grow_state", 0, 3);
    public static final IntegerProperty DIRT = IntegerProperty.create("dirt", 0, 1);
    public static final IntegerProperty WOOD = IntegerProperty.create("wood", 0, 7);
    public static final IntegerProperty MUSHROOM = IntegerProperty.create("mushroom", 0, 1);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected MushroomPlanterBlock(Properties properties, Supplier<BlockEntityType<MushroomPlanterBlockEntity>> supplier) {
        super(properties, supplier);
        registerDefaultState(stateDefinition.any()
                .setValue(GROW_STATE, 0)
                .setValue(DIRT, 0)
                .setValue(WOOD, 0)
                .setValue(MUSHROOM, 0)
                .setValue(FACING, Direction.NORTH));
    }

    public static boolean hasMushroom(BlockState state) {
        return state.getValue(GROW_STATE) != 0;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        ItemStack item = pContext.getItemInHand();
        BlockState blockState = defaultBlockState();
        CompoundTag tag = item.getTag();

        if (tag != null) {
            if (tag.contains("grow_state")) {
                blockState = blockState.setValue(GROW_STATE, tag.getInt("grow_state"));
            }

            if (tag.contains("dirt")) {
                blockState = blockState.setValue(DIRT, tag.getInt("dirt"));
            }

            if (tag.contains("wood")) {
                blockState = blockState.setValue(WOOD, tag.getInt("wood"));
            }

            if (tag.contains("mushroom")) {
                blockState = blockState.setValue(MUSHROOM, tag.getInt("mushroom"));
            }
        }

        return blockState.setValue(FACING, pContext.getHorizontalDirection().getClockWise());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(GROW_STATE, DIRT, WOOD, MUSHROOM, FACING);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Direction value = pState.getValue(FACING);
        List<BlockPos> posList = new ArrayList<>();
        posList.add(pPos.relative(value));
        posList.add(pPos.relative(value).relative(value));
        posList.add(pPos.relative(value.getOpposite()));
        posList.add(pPos.relative(value.getOpposite()).relative(value.getOpposite()));
        for(BlockPos pos : posList) {
            BlockState blockstate = pLevel.getBlockState(pos);
            Material material = blockstate.getMaterial();
            if (material.isSolid() || pLevel.getFluidState(pos).is(FluidTags.LAVA)) {
                return false;
            }
        }

        return super.canSurvive(pState, pLevel, pPos);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            ItemStack held = pPlayer.getItemInHand(pHand);
            if (held.getItem() instanceof BlockItem bi) {
                Block block = bi.getBlock();
                if (!hasMushroom(pState)) {
                    if (WOOD_BLOCKS.containsKey(block)) {
                        int ordinal = WOOD_BLOCKS.get(block).ordinal();
                        if (ordinal != pState.getValue(WOOD)) {
                            pLevel.setBlock(pPos, pState.setValue(WOOD, ordinal), Block.UPDATE_NONE);
                            if (!pPlayer.isCreative()) {
                                held.shrink(1);
                            }
                            return InteractionResult.SUCCESS;
                        }
                    } else if (DIRT_BLOCKS.containsKey(block)) {
                        int ordinal = DIRT_BLOCKS.get(block).ordinal();
                        if (ordinal != pState.getValue(DIRT)) {
                            pLevel.setBlock(pPos, pState.setValue(DIRT, ordinal), Block.UPDATE_NONE);

                            if (!pPlayer.isCreative()) {
                                held.shrink(1);
                            }

                            return InteractionResult.SUCCESS;
                        }
                    } else if (MUSHROOM_BLOCKS.containsKey(block)) {
                        BlockState newState = pState.setValue(MUSHROOM, MUSHROOM_BLOCKS.get(block).ordinal());
                        if (pLevel.getBlockEntity(pPos) instanceof MushroomPlanterBlockEntity mpb) {
                            mpb.refresh(newState);
                            if (!pPlayer.isCreative()) {
                                held.shrink(1);
                            }
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            } else if (held.isEmpty() && pState.getValue(GROW_STATE) >= 3) {
                if (pLevel.getBlockEntity(pPos) instanceof MushroomPlanterBlockEntity mpb) {
                    mpb.replaceOnRot(pLevel);
                }

                pPlayer.addItem(new ItemStack(MUSHROOM_BLOCKS.inverse().get(MUSHROOMS[pState.getValue(MUSHROOM)]), 4));
            }
        }

        return InteractionResult.PASS;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        ItemStack wood = new ItemStack(WOOD_BLOCKS.inverse().get(WOODS[pState.getValue(WOOD)]).asItem());
        ItemStack dirt = new ItemStack(DIRT_BLOCKS.inverse().get(DIRTS[pState.getValue(DIRT)]).asItem());
        if (pState.getValue(GROW_STATE) > 2) {
            Item mushroomItem = MUSHROOM_BLOCKS.inverse().get(MUSHROOMS[pState.getValue(MUSHROOM)]).asItem();
            ItemStack mushroom = new ItemStack(mushroomItem, (pState.getValue(GROW_STATE) - 1) * 2);
            return List.of(wood, dirt, mushroom);
        } else {
            return List.of(wood, dirt);
        }
    }

    public static final BiMap<Block, Dirt> DIRT_BLOCKS = HashBiMap.create(ImmutableMap.<Block, Dirt>builder()
            .put(Blocks.PODZOL, Dirt.PODZOL).put(Blocks.MYCELIUM, Dirt.MYCELIUM).build());

    public enum Dirt {
        //灰化土 minecraft:
        PODZOL,
        //菌丝 minecraft:
        MYCELIUM;
    }

    public static final Dirt[] DIRTS = Dirt.values();

    public static final BiMap<Block, Wood> WOOD_BLOCKS = HashBiMap.create(ImmutableMap.<Block, Wood>builder()
            .put(Blocks.OAK_LOG, Wood.OAK_LOG)
            .put(Blocks.SPRUCE_LOG, Wood.SPRUCE_LOG)
            .put(Blocks.BIRCH_LOG, Wood.BIRCH_LOG)
            .put(Blocks.JUNGLE_LOG, Wood.JUNGLE_LOG)
            .put(Blocks.ACACIA_LOG, Wood.ACACIA_LOG)
            .put(Blocks.DARK_OAK_LOG, Wood.DARK_OAK_LOG)
            .put(Blocks.CRIMSON_HYPHAE, Wood.CRIMSON_HYPHAE)
            .put(Blocks.WARPED_HYPHAE, Wood.WARPED_HYPHAE)
            .build());

    public enum Wood {
        //橡木原木
        OAK_LOG,
        //云杉原木
        SPRUCE_LOG,
        //白桦原木
        BIRCH_LOG,
        //丛林原木
        JUNGLE_LOG,
        //金合欢原木
        ACACIA_LOG,
        //深色橡木原木
        DARK_OAK_LOG,
        //绯红菌柄
        CRIMSON_HYPHAE,
        //诡异菌柄
        WARPED_HYPHAE
    }

    public static final Wood[] WOODS = Wood.values();

    public static final BiMap<Block, Mushroom> MUSHROOM_BLOCKS = HashBiMap.create(ImmutableMap.<Block, Mushroom>builder()
            .put(Blocks.RED_MUSHROOM, Mushroom.RED).put(Blocks.BROWN_MUSHROOM, Mushroom.BROWN).build());

    public enum Mushroom {
        RED,
        BROWN;
    }

    public static final Mushroom[] MUSHROOMS = Mushroom.values();

    @Override
    public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {
            for (MushroomPlanterBlock.Wood wood : MushroomPlanterBlock.WOODS) {
                ItemStack result = new ItemStack(this);
                result.getOrCreateTag().putInt("dirt", Dirt.PODZOL.ordinal());
                result.getOrCreateTag().putInt("wood", wood.ordinal());
                pItems.add(result);
            }
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

}