package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.item.BlockRotViewerItem;
import nameless.classicraft.item.ClassiCraftTab;
import nameless.classicraft.item.DrinkItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ClassiCraftMod.MODID);

    public static final RegistryObject<Item> NETHER_MUSHROOM_STEW = food("nether_mushroom_stew", ModFoodDatas.NETHER_MUSHROOM_STEW);
    public static final RegistryObject<Item> COOKED_EGG = food("cooked_egg", ModFoodDatas.COOKED_EGG);
    public static final RegistryObject<Item> SALT = normal("salt");
    public static final RegistryObject<Item> ROTTEN_FOOD = normal("rotten_food", p -> p.food(ModFoodDatas.ROTTEN_FOOD));
    public static final RegistryObject<Item> DOUGH = food("dough", ModFoodDatas.DOUGH);
    public static final RegistryObject<Item> RICE = food("rice", ModFoodDatas.RICE);
    public static final RegistryObject<Item> FLOUR = food("flour", ModFoodDatas.FLOUR);
    public static final RegistryObject<Item> RICE_HUSK = food("rice_husk", ModFoodDatas.RICE_HUSK);
    public static final RegistryObject<Item> RICE_SEED = ITEMS.register("rice_seed", () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), common()));
    public static final RegistryObject<Item> CLASSIC_CRAFT = ITEMS.register("classic_craft", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_ROT_VIEWER = ITEMS.register("block_rot_viewer", () -> new BlockRotViewerItem(common()));

    public static final RegistryObject<Item> DEER_SPAWN_EGG = registerSpawnEgg(ModEntities.DEER_ENEITY, 0x7b4d2e, 0x4b241d);

    public static final RegistryObject<Item> SALT_WATER_BOTTLE = normal("salt_water_bottle");
    public static final RegistryObject<Item> UNLIT_TORCH = ITEMS.register("unlit_torch", () -> new StandingAndWallBlockItem(
            ModBlocks.UNLIT_TORCH.get(), ModBlocks.WALL_UNLIT_TORCH.get(), decoration()));
    public static final RegistryObject<Item> UNLIT_SOUL_TORCH = ITEMS.register("unlit_soul_torch", () -> new StandingAndWallBlockItem(
            ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.WALL_UNLIT_SOUL_TORCH.get(), decoration()));

    private static RegistryObject<Item> food(String name, FoodProperties foodData) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(foodData).tab(ClassiCraftTab.COMMON)));
    }

    private static RegistryObject<Item> drink(String name, FoodProperties drinkData) {
        return ITEMS.register(name, () -> new DrinkItem(new Item.Properties().food(drinkData).tab(ClassiCraftTab.COMMON).stacksTo(1)));
    }

    private static RegistryObject<Item> normal(String name) {
        return normal(name, p -> p);
    }

    private static RegistryObject<Item> normal(String name, Function<Item.Properties, Item.Properties> func) {
        return ITEMS.register(name, () -> new Item(func.apply(common())));
    }

    private static Item.Properties common() {
        return new Item.Properties().tab(ClassiCraftTab.COMMON);
    }

    private static Item.Properties decoration() {
        return new Item.Properties().tab(ClassiCraftTab.DECORATION);
    }

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerSpawnEgg(RegistryObject<T> entity, int color1, int color2) {
        return register("spawn_egg/" + entity.getId().getPath(), () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties().tab(ClassiCraftTab.COMMON)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

}