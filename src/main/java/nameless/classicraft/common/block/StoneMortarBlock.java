package nameless.classicraft.common.block;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.block.entity.StoneMortarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class StoneMortarBlock extends BaseEntityBlock {

    public StoneMortarBlock() {
        super(Block.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength(1.5F, 6F)
                .sound(SoundType.METAL));
    }

    public static final VoxelShape SHAPE = Stream.of(
            Block.box(6, 0, 3, 7, 6, 4),
            Block.box(4, 0, 5, 5, 6, 6),
            Block.box(11, 0, 6, 12, 6, 8),
            Block.box(5, 0, 9, 6, 6, 10),
            Block.box(4, 0, 8, 5, 6, 9),
            Block.box(4, 0, 6, 5, 6, 8),
            Block.box(9, 0, 10, 10, 6, 11),
            Block.box(6, 0, 10, 7, 6, 11),
            Block.box(11, 0, 8, 12, 6, 9),
            Block.box(10, 0, 9, 11, 6, 10),
            Block.box(7, 0, 3, 9, 6, 4),
            Block.box(9, 0, 3, 10, 6, 4),
            Block.box(10, 0, 4, 11, 6, 5),
            Block.box(5, 0, 4, 6, 6, 5),
            Block.box(7, 0, 10, 9, 6, 11),
            Block.box(11, 0, 5, 12, 6, 6),
            Shapes.join(Block.box(6, 0, 4, 10, 1, 10),
                    Shapes.join(Block.box(10, 0, 5, 11, 1, 9),
                            Shapes.join(Block.box(7, -2, 6, 9, 7, 8),
                                    Block.box(5, 0, 5, 6, 1, 9), BooleanOp.AND),
                            BooleanOp.AND), BooleanOp.AND)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StoneMortarBlockEntity(blockPos, blockState);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StoneMortarBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, (StoneMortarBlockEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StoneMortarBlockEntity) {
                ((StoneMortarBlockEntity) blockEntity).drop();
            }
        }
        super.onRemove(state, world, pos, state1, flag);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType,
                ModBlockEntities.STONE_MORTAR_BLOCK_ENTITY.get(),
                StoneMortarBlockEntity::tick);
    }
}
