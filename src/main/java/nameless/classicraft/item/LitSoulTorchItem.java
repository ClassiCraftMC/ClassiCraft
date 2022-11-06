package nameless.classicraft.item;

import nameless.classicraft.block.realistic.RealisticSoulTorchBlock;
import nameless.classicraft.block.realistic.RealisticSoulWallTorchBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitSoulTorchItem extends StandingAndWallBlockItem {

    public LitSoulTorchItem(Properties pProperties) {
        super(ModBlocks.SOUL_TORCH.get(), ModBlocks.SOUL_WALL_TORCH.get(), pProperties);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        BlockState state = super.getPlacementState(pContext);
        if (state != null) {
            return state.setValue(RealisticSoulTorchBlock.getLitState(), RealisticSoulTorchBlock.LIT).setValue(RealisticSoulWallTorchBlock.getBurnTime(), RealisticSoulTorchBlock.getInitialBurnTime());
        }
        return super.getPlacementState(pContext);
    }

}
