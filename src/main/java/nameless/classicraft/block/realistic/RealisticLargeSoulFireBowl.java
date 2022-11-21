package nameless.classicraft.block.realistic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static nameless.classicraft.util.LightUtils.*;

public class RealisticLargeSoulFireBowl extends RealisticLargeFireBowlBlock {

    @Override
    public void animateTick(BlockState state, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (state.getValue(LITSTATE) == LIT || (state.getValue(LITSTATE) == SMOLDERING && pLevel.getRandom().nextInt(2) == 1)) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 1.0D;
            double d2 = (double)pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

}
