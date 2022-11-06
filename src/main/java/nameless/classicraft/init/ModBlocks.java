package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.*;
import nameless.classicraft.block.entity.ModBlockEntities;
import nameless.classicraft.block.WallUnlitTorchBlock;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.block.realistic.RealisticWallTorchBlock;
import nameless.classicraft.crop.RiceBlock;
import nameless.classicraft.crop.RicePaniclesBlock;
import nameless.classicraft.item.ClassiCraftTab;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ClassiCraftMod.MODID);

    public static final RegistryObject<Block> FRIDGE =
            registerNormal("fridge",
            () -> new FridgeBlock(wood(), ModBlockEntities.FRIDGE));
    public static final RegistryObject<Block> MUSHROOM_PLANTER =
            registerNormal("mushroom_planter",
            () -> new MushroomPlanterBlock(wood().noOcclusion(), ModBlockEntities.MUSHROOM_PLANTER));
    public static final RegistryObject<Block> UNLIT_LANTERN =
            registerDecoration("unlit_lantern", () -> new UnlitLanternBlock(lantern()));
    public static final RegistryObject<Block> UNLIT_SOUL_LANTERN =
            registerDecoration("unlit_soul_lantern", () -> new UnlitLanternBlock(lantern()));
    public static final RegistryObject<Block> GLISTERING_MELON =
            registerNormal("glistering_melon",
                    () -> new GlisteringMelonBlock(melon()));
    public static final RegistryObject<Block> CACTUS_FRUIT =
            registerFood("cactus_fruit",
                    () -> new CactusFruitBlock(cactus()), ModFoodDatas.CACTUS_FRUIT);
    /**public static final RegistryObject<Block> UNLIT_TORCH =
           register ("unlit_torch",
                   () -> new UnlitTorchBlock(torch()));*/
    public static final RegistryObject<Block> WALL_UNLIT_TORCH =
           register("wall_unlit_torch",
                   () -> new WallUnlitTorchBlock(torch()));
    public static final RegistryObject<Block> UNLIT_SOUL_TORCH =
            register("unlit_soul_torch",
                    () -> new UnlitTorchBlock(torch()));
    public static final RegistryObject<Block> WALL_UNLIT_SOUL_TORCH =
            register("wall_unlit_soul_torch",
                    () -> new WallUnlitTorchBlock(torch()));
    public static final RegistryObject<Block> SALT_ORE =
            registerNormal("salt_ore",
                    () -> new DropExperienceBlock(BlockBehaviour
                            .Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F),
                            UniformInt.of(0, 2)));
    public static final RegistryObject<Block> WILD_RICE =
            registerNormal("wild_rice", WildRiceBlock::new);
    public static final RegistryObject<Block> SALT_CRYSTAL =
            registerNormal("salt_crystal", SaltCrystalBlock::new);
    public static final RegistryObject<Block> SALT_BLOCK =
            registerNormal("salt_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> SALT_ROCK_BLOCK =
            registerNormal("salt_rock_block", SaltRockBlock::new);

    public static final RegistryObject<Block> STONE_MORTAR_BLOCK =
            registerNormal("stone_mortar_block", StoneMortarBlock::new);

    public static final RegistryObject<Block> RICE_CROP =
            register("rice_crop",
                    () -> new RiceBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F)));
    public static final RegistryObject<Block> RICE_CROP_PANICLES =
            registerNormal("rice_panicles",
                    () -> new RicePaniclesBlock(Block.Properties.copy(Blocks.WHEAT)));

    // 批量添加不同材料和颜色的烛台方块
    public static final ArrayList<RegistryObject<Block>> UNLIT_CANDLEHOLDERS = new ArrayList<RegistryObject<Block>>(){{
        for (String material : Arrays.asList("golden_","","copper_"))//铁制烛台的命名空间不带有iron
           for (DyeColor dyeColor: DyeColor.values())
                add(registerDecoration("unlit_" + material + dyeColor.getName() + "_candleholder", () -> new UnlitCandleholderBlock(candleholder())));
    }};

    public static final ArrayList<RegistryObject<Block>> UNLIT_LARGE_CANDLEHOLDERS = new ArrayList<RegistryObject<Block>>(){{
        for (String material : Arrays.asList("golden_","","copper_"))
            for (DyeColor dyeColor: DyeColor.values())
                add(registerDecoration("unlit_large_" + material + dyeColor.getName() + "_candleholder", () -> new UnlitLargeCandleholderBlock(largeCandleholder())));
    }};

    public static final RegistryObject<Block> UNLIT_FIRE_BOWL =
            registerDecoration("unlit_fire_bowl",
                    () -> new UnlitFireBowlBlock(fireBowl()));
    public static final  RegistryObject<Block> UNLIT_SOUL_FIRE_BOWL =
            registerDecoration("unlit_soul_fire_bowl",
                    () -> new UnlitFireBowlBlock(largeFireBowl()));
    public static final RegistryObject<Block> UNLIT_LARGE_FIRE_BOWL =
            registerDecoration("unlit_large_fire_bowl",
                    () -> new UnlitFireBowlBlock(largeFireBowl()));
    public static final RegistryObject<Block> UNLIT_LARGE_SOUL_FIRE_BOWL =
            registerDecoration("unlit_large_soul_fire_bowl",
                    () -> new UnlitLargeFireBowlBlock(largeFireBowl()));

    public static final RegistryObject<Block> TORCH =
            register("torch", RealisticTorchBlock::new);
    public static final RegistryObject<Block> WALL_TORCH =
            register("torch_wall", RealisticWallTorchBlock::new);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return registerBlock(ModBlocks.BLOCKS, ModItems.ITEMS, name, blockSupplier, blockItemFactory);
    }

    private static <T extends Block> RegistryObject<T> registerDecoration(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(ClassiCraftTab.DECORATION)));
    }

    private static <T extends Block> RegistryObject<T> registerFood(String name, Supplier<T> blockSupplier, FoodProperties foodData) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().food(foodData).tab(ClassiCraftTab.COMMON)));
    }

    private static <T extends Block> RegistryObject<T> registerNormal(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(ClassiCraftTab.COMMON)));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return BLOCKS.register(name.toLowerCase(Locale.ROOT), block);
    }

    private static BlockBehaviour.Properties lantern() {
        return BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN);
    }

    private static BlockBehaviour.Properties torch() {
        return BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties candleholder() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties largeCandleholder() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties fireBowl() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties largeFireBowl() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties wood() {
        return BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties melon() {
        return BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties cactus() {
        return BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).noOcclusion().sound(SoundType.WOOL);
    }

}