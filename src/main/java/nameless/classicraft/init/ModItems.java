package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Item> TORCH_UNLIT =
            register("torch_unlit", UnlitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_UNLIT =
            register("soul_torch_unlit", UnlitSoulTorchItem::new);

    public static final RegistryObject<Item> TORCH_LIT =
            register("torch_lit", LitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_LIT =
            register("soul_torch_lit", LitSoulTorchItem::new);

    public static final RegistryObject<Item> CLASSIC_CRAFT =
            register("classic_craft", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBUG_TIME_STICK =
            register("debug_time_stick", DebugBugTimeStick::new);

    public static final RegistryObject<Item> DEPTH_METER =
            register("depth_meter", DepthMeterItem::new);

    public static final RegistryObject<Item> BIOME_COMPASS =
            register("biome_compass", BiomeCompass::new);

    public static final RegistryObject<Item> TROUT =
            food("trout", Foods.COD);

    public static final RegistryObject<Item> COOKED_TROUT =
            food("cooked_trout", Foods.COOKED_COD);

    public static final RegistryObject<Item> TROUT_BUCKET =
            registerMobBuckteItem(ModEntities.TROUT_ENTITY);

    public static final RegistryObject<Item> OCEAN_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.OCEAN_SHARK_ENTITY, 0x302521, 0xe4edf2);

    public static final RegistryObject<Item> TROUT_SPAWN_EGG =
            registerSpawnEgg(ModEntities.TROUT_ENTITY, 0x5a867c, 0x6b9f93);

    public static final RegistryObject<Item> LIVING_DEAD_SPAWN_EGG =
            registerSpawnEgg(ModEntities.LIVING_DEAD, 0x5a867c, 0x6b9f93);

    public static final RegistryObject<Item> TALLOW =
            register("tallow", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COOKED_EGG =
            food("cooked_egg", Foods.COOKED_COD);

    public static final RegistryObject<Item> NETHER_MUSHROOM_STEW =
            food("nether_mushroom_stew", Foods.MUSHROOM_STEW);

    public static final RegistryObject<Item> ROTTEN_FOOD =
            food("rotten_food", p -> p.food(new FoodProperties.Builder().build()));

    private static RegistryObject<Item> food(String name, FoodProperties foodData) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(foodData)));
    }

    private static RegistryObject<Item> food(String name, Function<Item.Properties, Item.Properties> func) {
        return ITEMS.register(name, () -> new Item(func.apply(new Item.Properties())));
    }

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerSpawnEgg(RegistryObject<T> entity, int color1, int color2) {
        return register("spawn_egg/" + entity.getId().getPath(), () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
    }

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerMobBuckteItem(RegistryObject<T> entity) {
        return register("bucket/" + entity.getId().getPath(), () ->
                new MobBucketItem(entity,
                        () -> Fluids.WATER,
                        () -> SoundEvents.BUCKET_EMPTY_FISH,
                        new Item.Properties().stacksTo(1)));
    }

    /**
     * Used for registry items
     * @param name Items' registry name
     * @param item Item Instance
     * @return new RegistryObject<Item>
     * @param <T> sth extends Item
     */
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
