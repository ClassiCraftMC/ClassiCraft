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
    public static final RegistryObject<Item> RICE_SEED = ITEMS.register("rice_seed", () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), common().food(ModFoodDatas.RICE_SEED)));
    public static final RegistryObject<Item> CLASSIC_CRAFT = ITEMS.register("classic_craft", () -> new Item(new Item.Properties()));

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
            normal("matchbox");

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