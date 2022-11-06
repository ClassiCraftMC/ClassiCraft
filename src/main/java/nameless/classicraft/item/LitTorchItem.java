package nameless.classicraft.item;

import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitTorchItem extends StandingAndWallBlockItem {

    public LitTorchItem(Properties pProperties) {
        super(ModBlocks.TORCH.get(), ModBlocks.WALL_TORCH.get(), pProperties);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        BlockState state = super.getPlacementState(pContext);
        if (state != null) {
            return state.setValue(RealisticTorchBlock.getLitState(), RealisticTorchBlock.LIT).setValue(RealisticTorchBlock.getBurnTime(), RealisticTorchBlock.getInitialBurnTime());
        }
        return super.getPlacementState(pContext);
    }

}
