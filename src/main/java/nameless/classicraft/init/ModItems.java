package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
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
    public static final RegistryObject<Item> SALT = food("salt", ModFoodDatas.NONE);
    public static final RegistryObject<Item> ROTTEN_FOOD = normal("rotten_food", p -> p.food(ModFoodDatas.ROTTEN_FOOD));
    public static final RegistryObject<Item> DOUGH = food("dough", ModFoodDatas.DOUGH);
    public static final RegistryObject<Item> RICE = food("rice", ModFoodDatas.RICE);
    public static final RegistryObject<Item> FLOUR = food("flour", ModFoodDatas.FLOUR);
    public static final RegistryObject<Item> RICE_HUSK = food("rice_husk", ModFoodDatas.RICE_HUSK);
    public static final RegistryObject<Item> RICE_SEED = register("rice_seed", () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), common().food(ModFoodDatas.RICE_SEED)));
    public static final RegistryObject<Item> CLASSIC_CRAFT = register("classic_craft", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TROUT =
            food("trout", Foods.COD);

    public static final RegistryObject<Item> COOKED_TROUT =
            food("cooked_trout", Foods.COOKED_COD);

    public static final RegistryObject<Item> DEER_SPAWN_EGG =
            registerSpawnEgg(ModEntities.DEER_ENEITY, 0x7b4d2e, 0x4b241d);
    public static final RegistryObject<Item> BOAR_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BOAR_ENTITY, 0x83653b, 0xffefca);

    public static final RegistryObject<Item> LIONFISH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.LIONFISH_ENTITY, 15281931, 16111310);

    public static final RegistryObject<Item> LIONFISH_BUCKET =
            registerMobBuckteItem(ModEntities.LIONFISH_ENTITY);

    public static final RegistryObject<Item> PERCH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.PERCH_ENTITY, 7764021, 12555079);

    public static final RegistryObject<Item> PERCH_BUCKET =
            registerMobBuckteItem(ModEntities.PERCH_ENTITY);

    public static final RegistryObject<Item> BOXFISH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BOXFISH_ENTITY, 0xf0ddad, 0xfacd5a);

    public static final RegistryObject<Item> BOXFISH_BUCKET =
            registerMobBuckteItem(ModEntities.BOXFISH_ENTITY);

    public static final RegistryObject<Item> ANGLEFISH_BUCKET =
            registerMobBuckteItem(ModEntities.ANGLEFISH_ENTITY);

    public static final RegistryObject<Item> ANGLEFISH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.ANGLEFISH_ENTITY, 0xf8c243, 0xEB1965);

    public static final RegistryObject<Item> BULL_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BULL_SHARK_ENTITY, 0x6e6c63, 0x999999);

    public static final RegistryObject<Item> LEMON_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.LEMON_SHARK_ENTITY, 0xe6c019, 0xfceda9);

    public static final RegistryObject<Item> SLEEPER_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.SLEEPER_SHARK_ENTITY, 0x847c7a, 0xffffff);

    public static final RegistryObject<Item> TROUT_BUCKET =
            registerMobBuckteItem(ModEntities.TROUT_ENTITY);

    public static final RegistryObject<Item> FOXFACE_FISH_BUCKET =
            registerMobBuckteItem(ModEntities.FOXFACE_FISH_ENTITY);

    public static final RegistryObject<Item> TROUT_SPAWN_EGG =
            registerSpawnEgg(ModEntities.TROUT_ENTITY, 0x5a867c, 0x6b9f93);

    public static final RegistryObject<Item> BUTTERFLY_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BUTTERFLY_ENTITY, 0x282828, 0xEF6F1F);

    public static final RegistryObject<Item> SWINE_SPAWN_EGG =
            registerSpawnEgg(ModEntities.SWINE_ENTITY, 0x282828, 0xEF6F1F);

    public static final RegistryObject<Item> FOXFACE_FISH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.FOXFACE_FISH_ENTITY, 0xdebb21, 0x292824);

    public static final RegistryObject<Item> FOOTBALLFISH_BUCKET =
            registerMobBuckteItem(ModEntities.FOOTBALLFISH_ENTITY);

    public static final RegistryObject<Item> FOOTBALLFISH_SPAWN_EGG =
            registerSpawnEgg(ModEntities.FOOTBALLFISH_ENTITY, 0x501c3d, 0xf1f2ff);

    public static final RegistryObject<Item> SALT_WATER_BOTTLE =
            register("salt_water_bottle", () -> new SaltWaterBottleItem(common()));

    public static final RegistryObject<Item> RAW_PUMPKIN_PIE =
            food("raw_pumpkin_pie", ModFoodDatas.RAW_PUMPKIN_PIE);
    public static final RegistryObject<Item> RAW_COOKIE =
            food("raw_cookie", ModFoodDatas.RAW_COOKIE);
    public static final RegistryObject<Item> RAW_CAKE =
            food("raw_cake", ModFoodDatas.RAW_CAKE);

    public static final RegistryObject<Item> PERCH =
            food("perch", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> LIONFISH =
            food("lionfish", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> COOKED_PERCH =
            food("cooked_perch", Foods.COOKED_COD);

    public static final RegistryObject<Item> COOKED_LIONFISH =
            food("cooked_lionfish", Foods.COOKED_COD);

    public static final RegistryObject<Item> RAW_SALT =
            food("raw_salt", ModFoodDatas.NONE);

    public static final RegistryObject<Item> TORCH =
            register("torch", () -> new StandingAndWallBlockItem(ModBlocks.TORCH.get(), ModBlocks.WALL_TORCH.get(), decoration()));

    public static final RegistryObject<Item> LIT_TORCH =
            register("lit_torch", LitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH =
            register("soul_torch", () -> new StandingAndWallBlockItem(ModBlocks.SOUL_TORCH.get(), ModBlocks.SOUL_WALL_TORCH.get(), decoration()));

    public static final RegistryObject<Item> LIT_SOUL_TORCH =
            register("lit_soul_torch", LitSoulTorchItem::new);

    public static final RegistryObject<Item> MATCHBOX =
            register("matchbox", () ->new Item(new Item.Properties().tab(ClassiCraftTab.COMMON)));

    public static final RegistryObject<Item> RANCHU =
            food("ranchu", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> ANGLEFISH =
            food("anglefish", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> FOOTBALLFISH =
            food("footballfish", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> LANTERN =
            register("lantern", LanternItem::new);

    public static final RegistryObject<Item> SOUL_LANTERN =
            register("soul_lantern", () -> new BlockItem(ModBlocks.SOUL_LANTERN.get(), decoration()));

    public static final RegistryObject<Item> LIT_LANTERN =
            register("lit_lantern", LitLantern::new);

    public static final RegistryObject<Item> LIT_SOUL_LANTERN =
            register("lit_soul_lantern", LitSoulLantern::new);

    public static final RegistryObject<Item> BASKING_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BASKING_SHARK_ENTITY, 0x302521, 0xe4edf2);

    public static final RegistryObject<Item> OCEAN_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.OCEAN_SHARK_ENTITY, 0x302521, 0xe4edf2);

    public static final RegistryObject<Item> ARAPAIMA =
            food("arapaima", Foods.TROPICAL_FISH);

    public static final RegistryObject<Item> ARAPAIMA_SPAWN_EGG =
            registerSpawnEgg(ModEntities.ARAPAIMA_ENTITY, 0x706e6a, 0x860505);

    public static final RegistryObject<Item> BELUGA_STURGEON_SPAWN_EGG =
            registerSpawnEgg(ModEntities.BELUGA_STURGEON_ENTITY, 0x98a2a9, 0xeadacf);

    public static final RegistryObject<Item> ARAPAIMA_BUCKET =
            registerMobBuckteItem(ModEntities.ARAPAIMA_ENTITY);

    public static final RegistryObject<Item> DROWN_ITEM =
            normal("drown_item");

    public static final RegistryObject<Item> RANCHU_BUCKET =
            register("ranchu_bucket", () ->
                    new RanchuBucketItem(ModEntities.RANCHU_ENTITY,
                            () -> Fluids.WATER, Items.BUCKET,
                            false, new Item.Properties()
                            .tab(ClassiCraftTab.COMMON).stacksTo(1)));

    public static final RegistryObject<Item> RANCHU_SPAWN_EGG =
            registerSpawnEgg(ModEntities.RANCHU_ENTITY, 0x736036, 0xd1a965);


    private static RegistryObject<Item> food(String name, FoodProperties foodData) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(foodData).tab(ClassiCraftTab.COMMON)));
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

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerMobBuckteItem(RegistryObject<T> entity) {
            return register("bucket/" + entity.getId().getPath(), () ->
                    new MobBucketItem(entity,
                    () -> Fluids.WATER,
                    () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().tab(ClassiCraftTab.COMMON).stacksTo(1)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

}