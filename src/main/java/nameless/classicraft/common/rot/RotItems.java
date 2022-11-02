package nameless.classicraft.common.rot;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.common.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RotItems {
    public static final Map<Item, Integer> MAP = ImmutableMap.<Item, Integer>builder()
            .put(Items.TROPICAL_FISH, 48000)
            .put(Items.SALMON, 24000)
            .put(Items.PUFFERFISH, 48000)
            .put(Items.COD, 24000)
            .put(Items.MUTTON, 72000)
            .put(Items.CHICKEN, 24000)
            .put(Items.RABBIT, 24000)
            .put(Items.SPIDER_EYE, 168000)
            .put(Items.BEEF, 168000)
            .put(Items.PORKCHOP, 72000)
            .put(Items.COOKED_COD, 48000)
            .put(Items.COOKED_RABBIT, 48000)
            .put(Items.COOKED_CHICKEN, 24000)
            .put(Items.COOKED_MUTTON, 48000)
            .put(Items.COOKED_SALMON, 48000)
            .put(Items.COOKED_PORKCHOP, 24000)
            .put(Items.COOKED_BEEF, 48000)
            .put(Items.DRIED_KELP, 4320000)
            .put(Items.POTATO, 1560000)
            .put(Items.BEETROOT, 720000)
            .put(Items.COOKIE, 360000)
            .put(Items.GLOW_BERRIES, 24000)
            .put(Items.SWEET_BERRIES, 24000)
            .put(Items.MELON_SLICE, 6000)
            .put(Items.CARROT, 168000)
            .put(Items.APPLE, 360000)
            .put(Items.BAKED_POTATO, 48000)
            .put(Items.BREAD, 4320000)
            .put(Items.PUMPKIN_PIE, 24000)
            .put(Items.POISONOUS_POTATO, 168000)
            .put(Items.CHORUS_FRUIT, 24000)
            .put(Items.ENCHANTED_GOLDEN_APPLE, 15552000)
            .put(Items.GOLDEN_APPLE, 1728000)
            .put(Items.GOLDEN_CARROT, 192000)
            .put(Items.ROTTEN_FLESH, 24000)
            .put(Items.SUGAR_CANE, 1440000)
            .put(Items.SUGAR, 720000)
            .put(Items.FERMENTED_SPIDER_EYE, 12 * 24_000)
            .put(Items.EGG, 240000)
            .put(Items.TURTLE_EGG, 240000)
            .put(Items.BROWN_MUSHROOM, 48000)
            .put(Items.RED_MUSHROOM, 48000)
            .put(Items.CRIMSON_FUNGUS, 48000)
            .put(Items.WARPED_FUNGUS, 48000)
            .put(Items.RABBIT_STEW, 9600)
            .put(Items.SUSPICIOUS_STEW, 72000)
            .put(Items.MUSHROOM_STEW, 72000)
            .put(Items.BEETROOT_SOUP, 120000)
            .put(Items.HONEY_BOTTLE, 12960000)
            .put(Items.GLISTERING_MELON_SLICE, 192000)
            .put(ModItems.GLISTERING_MELON.get(), 1728000)
            .put(ModItems.COOKED_EGG.get(), 72000)
            .put(ModItems.NETHER_MUSHROOM_STEW.get(), 144000)
            .put(Items.MELON, 168000)
            .put(Items.PUMPKIN, 720000)
            .put(ModItems.CACTUS_FRUIT.get(), 24000)
            .put(Items.CAKE, 24000).build();
}
