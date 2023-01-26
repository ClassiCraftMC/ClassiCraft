package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wdog5
 */
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

    public static final RegistryObject<Item> MATERIAL =
            register("material", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBUG_BURN_TIME_STICK =
            register("debug_burn_time_stick", DebugBurnTimeStick::new);

    public static final RegistryObject<Item> DEPTH_METER =
            register("depth_meter", DepthMeterItem::new);
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

    public static final RegistryObject<Item> SULFUR =
            register("sulfur", () -> new Item(new Item.Properties()));

    public static final RegistryObject<MetaItem> PEBBLE = register("pebble", PebbleItem::new);

    public static final RegistryObject<MetaItem> POINT =
            register("point", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_point",
                    "deepslate_point",
                    "flint_point",
                    "blackstone_point",
                    "quartz_point"
            )));

    public static final RegistryObject<MetaItem> ADZE =
            register("adze", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_adze",
                    "deepslate_adze",
                    "flint_adze",
                    "blackstone_adze",
                    "quartz_adze"
            )));

    public static final RegistryObject<MetaItem> SCRAPER =
            register("scraper", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_scraper",
                    "deepslate_scraper",
                    "flint_scraper",
                    "blackstone_scraper",
                    "quartz_scraper"
            )));

    public static final RegistryObject<MetaItem> AWL =
            register("awl", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_awl",
                    "deepslate_awl",
                    "flint_awl",
                    "blackstone_awl",
                    "quartz_awl"
            )));

    public static final RegistryObject<MetaItem> CHOPPER =
            register("chopper", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_chopper",
                    "deepslate_chopper",
                    "flint_chopper",
                    "blackstone_chopper",
                    "quartz_chopper"
            )));

    public static final RegistryObject<Item> PHOBOS_OUTPOST_DISC =
            register("phobos_outpost_disc", () ->
                    new RecordItem(11, ModSounds.PHOBOS_OUTPOST, new Item.Properties().stacksTo(1), 238));

    public static final RegistryObject<Item> DRAGON_FISH_DISC =
            register("dragon_fish_disc", () ->
                    new RecordItem(11, ModSounds.DRAGON_FISH, new Item.Properties().stacksTo(1), 238));

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
     *
     * @param name Items' registry name
     * @param item Item Instance
     * @param <T>  sth extends Item
     * @return new RegistryObject<Item>
     */
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
