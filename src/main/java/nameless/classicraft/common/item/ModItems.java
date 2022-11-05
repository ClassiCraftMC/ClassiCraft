package nameless.classicraft.common.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClassiCraft.MODID);

    public static final RegistryObject<Item> FRIDGE = common_block(ModBlocks.FRIDGE);
    public static final RegistryObject<Item> STONE_MORTAR_BLOCK = common_block(ModBlocks.STONE_MORTAR_BLOCK);
    public static final RegistryObject<Item> UNLIT_LANTERN = common_block(ModBlocks.UNLIT_LANTERN);
    public static final RegistryObject<Item> UNLIT_SOUL_LANTERN = common_block(ModBlocks.UNLIT_SOUL_LANTERN);
    public static final RegistryObject<Item> MUSHROOM_PLANTER = common_block(ModBlocks.MUSHROOM_PLANTER);
    public static final RegistryObject<Item> GLISTERING_MELON = common_block(ModBlocks.GLISTERING_MELON);
    public static final RegistryObject<Item> SALT_ORE = common_block(ModBlocks.SALT_ORE);
    public static final RegistryObject<Item> WILD_RICE = common_block(ModBlocks.WILD_RICE);
    public static final RegistryObject<Item> NETHER_MUSHROOM_STEW = food("nether_mushroom_stew", ModFoodDatas.NETHER_MUSHROOM_STEW);
    public static final RegistryObject<Item> CACTUS_FRUIT = food("cactus_fruit", ModFoodDatas.CACTUS_FRUIT);
    public static final RegistryObject<Item> COOKED_EGG = food("cooked_egg", ModFoodDatas.COOKED_EGG);
    public static final RegistryObject<Item> SALT = food("salt", ModFoodDatas.SALT);
    public static final RegistryObject<Item> SALT_BLOCK = common_block(ModBlocks.SALT_BLOCK);
    public static final RegistryObject<Item> SALT_CRYSTAL = common_block(ModBlocks.SALT_CRYSTAL);
    public static final RegistryObject<Item> ROTTEN_FOOD = normal("rotten_food", p -> p.food(ModFoodDatas.ROTTEN_FOOD));
    public static final RegistryObject<Item> DOUGH = food("dough", ModFoodDatas.DOUGH);
    public static final RegistryObject<Item> RICE = food("rice", ModFoodDatas.RICE);
    public static final RegistryObject<Item> FLOUR = food("flour", ModFoodDatas.FLOUR);
    public static final RegistryObject<Item> RICE_HUSK = food("rice_husk", ModFoodDatas.RICE_HUSK);
    public static final RegistryObject<Item> RICE_SEED = ITEMS.register("rice_seed", () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), common()));
    public static final RegistryObject<Item> CLASSIC_CRAFT = ITEMS.register("classic_craft", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_ROT_VIEWER = ITEMS.register("block_rot_viewer", () -> new BlockRotViewerItem(common()));
    public static final RegistryObject<Item> UNLIT_TORCH = ITEMS.register("unlit_torch", () -> new StandingAndWallBlockItem(
            ModBlocks.UNLIT_TORCH.get(), ModBlocks.WALL_UNLIT_TORCH.get(), decoration()));
    public static final RegistryObject<Item> UNLIT_SOUL_TORCH = ITEMS.register("unlit_soul_torch", () -> new StandingAndWallBlockItem(
            ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.WALL_UNLIT_SOUL_TORCH.get(), decoration()));

    // 批量添加不同材料和颜色的烛台物品
    public static final ArrayList<RegistryObject<Item>> UNLIT_CANDLEHOLDERS = new ArrayList<RegistryObject<Item>>(){{
        for (RegistryObject<Block> UNLIT_CANDLEHOLDER: ModBlocks.UNLIT_CANDLEHOLDERS)
                add(decoration_block(UNLIT_CANDLEHOLDER));
    }};

    public static final ArrayList<RegistryObject<Item>> UNLIT_LARGE_CANDLEHOLDERS = new ArrayList<RegistryObject<Item>>(){{
        for (RegistryObject<Block> UNLIT_LARGE_CANDLEHOLDER: ModBlocks.UNLIT_LARGE_CANDLEHOLDERS)
            add(decoration_block(UNLIT_LARGE_CANDLEHOLDER));
    }};

    public static final RegistryObject<Item> UNLIT_FIRE_BOWL = decoration_block(ModBlocks.UNLIT_FIRE_BOWL);
    public static final RegistryObject<Item> UNLIT_SOUL_FIRE_BOWL = decoration_block(ModBlocks.UNLIT_SOUL_FIRE_BOWL);
    public static final RegistryObject<Item> UNLIT_LARGE_FIRE_BOWL = decoration_block(ModBlocks.UNLIT_LARGE_FIRE_BOWL);
    public static final RegistryObject<Item> UNLIT_LARGE_SOUL_FIRE_BOWL = decoration_block(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL);

    public static final RegistryObject<Item> DEER_SPAWN_EGG = registerSpawnEgg(ModEntities.DEER_ENEITY, 0x7b4d2e, 0x4b241d);

    public static final RegistryObject<Item> SALT_WATER_BOTTLE = drink("salt_water_bottle", ModFoodDatas.SALT_WATER_BOTTLE);

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

    private static RegistryObject<Item> common_block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), common()));
    }

    private static RegistryObject<Item> decoration_block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), decoration()));
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