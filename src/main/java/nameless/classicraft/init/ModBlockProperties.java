package nameless.classicraft.init;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

    public static final int LIT = 2;
    public static final int SMOLDERING = 1;
    public static final int UNLIT = 0;
    public static final int TICK_INTERVAL = 1200;
    public static final int TICK_RATE = 1200;
    public static final IntegerProperty LITSTATE = IntegerProperty.create("litstate", 0, 2);

    public static void playLightingSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public static void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

}
