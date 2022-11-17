package nameless.classicraft.block.realistic;

import nameless.classicraft.api.light.LightAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class RealisticLanternBlock extends LanternBlock implements LightAPI {

    public RealisticLanternBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel(RealisticLanternBlock::getLitState).noOcclusion());
        registerDefaultState(defaultBlockState().setValue(LITSTATE,0).setValue(LANTERN_BURNTIME,0).setValue(OIL,0).setValue(OIL,0));
    }

    public static int getLitState(BlockState state)
    {
        if(state.getValue(LITSTATE) == 0) {
            return 0;
        }
        if (state.getValue(LITSTATE) == 1) {
            return 3;
        }
        if (state.getValue(LITSTATE) == 2) {
            return 6;
        }else {
            return 9;
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        useLantern(pState, pLevel, pPos, pPlayer, pHand, pHit, this);
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tickLantern(state, level, pos, random, this);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock()) {
            defaultBlockState().updateIndirectNeighbourShapes(pLevel,pPos,3);
        }
        if(LANTERN_SHOUD_BURN_OUT&& pState.getBlock() instanceof RealisticLanternBlock &&pState.getValue(LITSTATE) > UNLIT)
            pLevel.scheduleTick(pPos,this,TICK_INTERVAL);
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LANTERN_BURNTIME,LITSTATE,OIL);
        super.createBlockStateDefinition(pBuilder);
    }
}
