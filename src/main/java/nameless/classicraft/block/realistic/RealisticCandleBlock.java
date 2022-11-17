package nameless.classicraft.block.realistic;

import nameless.classicraft.api.light.CandleLightAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RealisticCandleBlock extends CandleBlock implements CandleLightAPI {

    public RealisticCandleBlock() {
        super(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.SAND).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION));
        this.registerDefaultState(this.defaultBlockState().setValue(CANDLE_BURNTIME, 0));
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        tickCandle(pState, pLevel, pPos, pRandom, this);
        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getCandleStateForPlacement(pContext, Blocks.CANDLE, this);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(CANDLE_BURNTIME);
    }
}
