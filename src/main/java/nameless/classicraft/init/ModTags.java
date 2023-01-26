package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * @author wdog5
 */
public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> LEVEL_SURFACE_PLACEABLE_ON = register("level_surface_placeable_on");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> STONE_PEBBLES = register("stone_pebbles");
        public static final TagKey<Item> VANILLA_PEBBLES = register("vanilla_pebbles");
        public static final TagKey<Item> TAG_DRINKABLE = register("drinkable");
        public static final TagKey<Item> TAG_THICK = register("thick");

        private static TagKey<Item> register(String name) {
            return ItemTags.create(new ResourceLocation(ClassiCraftMod.MOD_ID, name));
        }
    }
}
