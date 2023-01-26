package nameless.classicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author wdog5
 */
@SuppressWarnings("all")
public class StainedGlassWallBlock extends WallBlock implements BeaconBeamBlock {

    private final DyeColor color;

    public StainedGlassWallBlock(DyeColor pDyeColor) {
        super(BlockBehaviour.Properties.of(Material.GLASS, pDyeColor)
                .strength(0.3F)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .isValidSpawn((bs, bt, bg, et) -> false)
                .isRedstoneConductor((bs, bt, bg) -> false)
                .isSuffocating((bs, bt, bg) -> false)
                .isViewBlocking((bs, bt, bg) -> false));
        this.color = pDyeColor;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) ? true : super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }

}
