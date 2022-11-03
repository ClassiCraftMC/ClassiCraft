package nameless.classicraft.common.rot;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RotBlocks {
    public static final Map<Block, Integer> MAP = ImmutableMap.<Block, Integer>builder()
            .put(Blocks.POTATOES, 168000)
            .put(Blocks.CAVE_VINES, 168000)
            .put(Blocks.SWEET_BERRY_BUSH, 168000)
            .put(Blocks.CARROTS, 168000)
            .put(Blocks.SUGAR_CANE, 1440000)
            .put(Blocks.TURTLE_EGG, 240000)
            .put(Blocks.BROWN_MUSHROOM, 168000)
            .put(Blocks.RED_MUSHROOM, 168000)
            .put(Blocks.CRIMSON_FUNGUS, 48000)
            .put(Blocks.WARPED_FUNGUS, 48000)
            .put(ModBlocks.GLISTERING_MELON.get(), 1728000)
            .put(Blocks.MELON, 168000)
            .put(Blocks.PUMPKIN, 720000)
            .put(ModBlocks.CACTUS_FRUIT.get(), 24000)
            .put(Blocks.CAKE, 24000).build();
}
