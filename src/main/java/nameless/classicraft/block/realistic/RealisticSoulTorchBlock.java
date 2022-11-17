package nameless.classicraft.block.realistic;

import nameless.classicraft.api.light.LightAPI;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.ToIntFunction;

public class RealisticSoulTorchBlock extends TorchBlock implements LightAPI {

    public RealisticSoulTorchBlock() {
        super(BlockBehaviour.Properties.of(Material.ICE).noCollission().instabreak().lightLevel(getLightLevelFromState()).sound(SoundType.WOOD), ParticleTypes.FLAME);
        this.registerDefaultState(this.defaultBlockState().setValue(LITSTATE, 0).setValue(TORCH_BURNTIME, 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        useTorch(pState, pLevel, pPos, pPlayer, pHand, pHit, this, ModItems.MATCHBOX.get(), ModBlocks.TORCH.get().asItem(), ModItems.LIT_TORCH.get());
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return CAUSE_FIRE &&pState.getValue(LITSTATE) == LIT  && new Random().nextInt(9) == 0;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        randomTickTorch(state, level, pos, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tickTorch(state, level, pos, random, this);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock())
        {
            defaultBlockState().updateIndirectNeighbourShapes(pLevel,pPos,3);
        }
        if(TORCH_SHOULD_BURN_OUT&&pState.getBlock() instanceof RealisticSoulTorchBlock&&pState.getValue(LITSTATE) > UNLIT)
            pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
        super.onPlace(pState,pLevel,pPos,pOldState,pIsMoving);
    }

    public static IntegerProperty getBurnTime() {
        return TORCH_BURNTIME;
    }

    public static IntegerProperty getLitState() {
        return LITSTATE;
    }

    public static int getInitialBurnTime() {
        return TORCH_SHOULD_BURN_OUT ? INITIAL_BURN_TIME : 0;
    }

    public static ToIntFunction<BlockState> getLightLevelFromState()
    {
        return (state) ->{
            if(state.getValue(RealisticTorchBlock.LITSTATE) == 2)
            {
                return 12;
            }
            else if(state.getValue(RealisticTorchBlock.LITSTATE) == 1)
            {
                return 8;
            }
            else
            {
                return 0;
            }
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        getTorchStateForPlacement(pContext, this);
        return super.getStateForPlacement(pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LITSTATE, TORCH_BURNTIME);
    }

}
