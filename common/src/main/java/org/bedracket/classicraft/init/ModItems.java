package org.bedracket.classicraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import org.bedracket.classicraft.ClassiCraftMod;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ClassiCraftMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> CLASSIC_CRAFT =
            register("classicraft");
    public static final RegistrySupplier<Item> TROUT =
            food("trout", Foods.COD);
    public static final RegistrySupplier<Item> COOKED_TROUT =
            food("cooked_trout", Foods.COOKED_COD);
    public static final RegistrySupplier<Item> TALLOW =
            register("tallow");
    public static final RegistrySupplier<Item> COOKED_EGG =
            food("cooked_egg", Foods.COOKED_COD);
    public static final RegistrySupplier<Item> NETHER_MUSHROOM_STEW =
            food("nether_mushroom_stew", Foods.MUSHROOM_STEW);
    public static final RegistrySupplier<Item> ROTTEN_FOOD =
            food("rotten_food", p -> p.food(new FoodProperties.Builder().build()));
    public static final RegistrySupplier<Item> SULFUR =
            register("sulfur");

    @SuppressWarnings("UnstableApiUsage")
    private static RegistrySupplier<Item> register(String name) {
        return register(name.toLowerCase(Locale.ROOT), () -> new Item(new Item.Properties().arch$tab(ModCreativeModeTabs.COMMON)));
    }

    @SuppressWarnings("UnstableApiUsage")
    private static RegistrySupplier<Item> food(String name, FoodProperties foodData) {
        return register(name, () -> new Item(new Item.Properties().food(foodData).arch$tab(ModCreativeModeTabs.COMMON)));
    }

    @SuppressWarnings("UnstableApiUsage")
    private static RegistrySupplier<Item> food(String name, Function<Item.Properties, Item.Properties> func) {
        return register(name, () -> new Item(func.apply(new Item.Properties().arch$tab(ModCreativeModeTabs.COMMON))));
    }

    private static <T extends Item> RegistrySupplier<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
