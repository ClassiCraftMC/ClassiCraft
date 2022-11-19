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
        public static final TagKey<Item> VANILLA_PLUS_SAPLINGS = registerVanilla("saplings");
        public static final TagKey<Item> VANILLA_PLUS_LEAVES = registerVanilla("leaves");

        public static final TagKey<Item> FUEL_LEVEL_0 = registerFuelLevel("fuel_level_0");
        public static final TagKey<Item> FUEL_LEVEL_1 = registerFuelLevel("fuel_level_1");
        public static final TagKey<Item> FUEL_LEVEL_2 = registerFuelLevel("fuel_level_2");
        public static final TagKey<Item> FUEL_LEVEL_4 = registerFuelLevel("fuel_level_4");
        public static final TagKey<Item> FUEL_LEVEL_5 = registerFuelLevel("fuel_level_5");
        public static final TagKey<Item> FUEL_LEVEL_6 = registerFuelLevel("fuel_level_6");
        public static final TagKey<Item> FUEL_LEVEL_8 = registerFuelLevel("fuel_level_8");
        public static final TagKey<Item> FUEL_LEVEL_10 = registerFuelLevel("fuel_level_10");
        public static final TagKey<Item> FUEL_LEVEL_16 = registerFuelLevel("fuel_level_16");
        public static final TagKey<Item> FUEL_LEVEL_26 = registerFuelLevel("fuel_level_26");
        public static final TagKey<Item> FUEL_LEVEL_36 = registerFuelLevel("fuel_level_36");
        public static final TagKey<Item> FUEL_LEVEL_144 = registerFuelLevel("fuel_level_144");

        private static TagKey<Item> registerVanilla(String name)
        {
            return ItemTags.create(new ResourceLocation("cc_vanilla_plus_addon", name));
        }

        private static TagKey<Item> registerFuelLevel(String name) {
            return ItemTags.create(new ResourceLocation("cc_fuel_level", name));
        }

        private static TagKey<Item> register(String name)
        {
            return ItemTags.create(new ResourceLocation(ClassiCraftMod.MODID, name));
        }
    }

}
