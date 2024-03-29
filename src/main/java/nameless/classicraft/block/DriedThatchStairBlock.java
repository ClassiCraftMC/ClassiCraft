package nameless.classicraft.block;

import nameless.classicraft.api.block.DryingBlockState;
import nameless.classicraft.api.block.EDryingBlockState;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class DriedThatchStairBlock extends StairBlock implements DryingBlockState {

    private final EDryingBlockState dryingState = EDryingBlockState.DRIED;

    public DriedThatchStairBlock() {
        super(ModBlocks.DRIED_THATCH.get()::defaultBlockState,
                BlockBehaviour.Properties.of(Material.GRASS,
                        MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS));
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
        entity.causeFallDamage(distance, 0.2F, entity.damageSources().fall());
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.onRandomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return DryingBlockState.getNext(pState.getBlock()).isPresent();
    }

    @Override
    public EDryingBlockState getAge() {
        return this.dryingState;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }
}
