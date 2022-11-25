package nameless.classicraft.block;

import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;

public class RealTorchBlock extends AbstractLightBlock {

    protected static final IntegerProperty TORCH_BURN_TIME = ModBlockProperties.TORCH_BURN_TIME;
    protected static final int TORCH_INITIAL_BURN_TIME = ModBlockProperties.TORCH_INITIAL_BURN_TIME;

    public RealTorchBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).noCollission().instabreak().lightLevel(getLightFromState()).sound(SoundType.WOOD));
        this.registerDefaultState(defaultBlockState().setValue(LIT, false).setValue(TORCH_BURN_TIME, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT, TORCH_BURN_TIME);
    }

    @Override
   IntegerProperty getBurnTime() {
        return TORCH_BURN_TIME;
    }

    @Override
    int getInitialBurnTime() {
        return TORCH_INITIAL_BURN_TIME;
    }
}
