package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks{
        public static final TagKey<Block> TORCH_CAN_BE_BURNT_OUT = register("torch_can_be_burnt_out");
        public static final TagKey<Block> DESTROY_CROPS = register("destroy_crops");

        private static TagKey<Block> register(String name)
        {
            return BlockTags.create(new ResourceLocation(ClassiCraftMod.MODID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> ATTACH_FOODS = register("attach_foods");
        public static final TagKey<Item> WOOD_FUEL = register("wood_fuel");
        public static final TagKey<Item> OIL_FUEL = register("oil_fuel");


        private static TagKey<Item> register(String name)
        {
            return ItemTags.create(new ResourceLocation(ClassiCraftMod.MODID, name));
        }
    }

}
