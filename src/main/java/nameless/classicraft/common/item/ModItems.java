package nameless.classicraft.common.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
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
    public static final RegistryObject<Item> UNLIT_LANTERN = common_block(ModBlocks.UNLIT_LANTERN);
    public static final RegistryObject<Item> UNLIT_SOUL_LANTERN = common_block(ModBlocks.UNLIT_SOUL_LANTERN);
    public static final RegistryObject<Item> MUSHROOM_PLANTER = common_block(ModBlocks.MUSHROOM_PLANTER);
    public static final RegistryObject<Item> GLISTERING_MELON = common_block(ModBlocks.GLISTERING_MELON);
    public static final RegistryObject<Item> NETHER_MUSHROOM_STEW = normal("nether_mushroom_stew");
    public static final RegistryObject<Item> CACTUS_FRUIT = normal("cactus_fruit");
    public static final RegistryObject<Item> COOKED_EGG = normal("cooked_egg");
    public static final RegistryObject<Item> SALT = normal("salt");
    public static final RegistryObject<Item> ROTTEN_FOOD = normal("rotten_food", p -> p.food(new FoodProperties.Builder().build()));
    public static final RegistryObject<Item> DOUGH = normal("dough");
    public static final RegistryObject<Item> RICE = normal("rice");
    public static final RegistryObject<Item> FLOUR = normal("flour");
    public static final RegistryObject<Item> RICE_HUSK = normal("rice_husk");
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