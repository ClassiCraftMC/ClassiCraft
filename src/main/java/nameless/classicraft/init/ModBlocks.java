package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.*;
import nameless.classicraft.block.container.FridgeBlock;
import nameless.classicraft.block.container.StoneMortarBlock;
import nameless.classicraft.block.container.WoodcutterBlock;
import nameless.classicraft.block.realistic.*;
import nameless.classicraft.crop.RiceBlock;
import nameless.classicraft.crop.RicePaniclesBlock;
import nameless.classicraft.item.ClassiCraftTab;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

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

    public static final RegistryObject<Block> GLISTERING_MELON =
            registerNormal("glistering_melon",
                    () -> new GlisteringMelonBlock(melon()));
    public static final RegistryObject<Block> CACTUS_FRUIT =
            registerFood("cactus_fruit",
                    () -> new CactusFruitBlock(cactus()), ModFoodDatas.CACTUS_FRUIT);

    public static final RegistryObject<Block> SALT_ORE =
            registerNormal("salt_ore",
                    () -> new DropExperienceBlock(BlockBehaviour
                            .Properties.of(Material.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F),
                            UniformInt.of(0, 2)));
    public static final RegistryObject<Block> WILD_RICE =
            registerNormal("wild_rice", WildRiceBlock::new);

    public static final RegistryObject<Block> SALT_STALACTITE =
            registerNormal("salt_stalactite", SaltStalactiteBlock::new);

    public static final RegistryObject<Block> SALT_CLUSTER =
            registerNormal("salt_cluster", () -> new AmethystClusterBlock(
                    7, 3,
                    BlockBehaviour.Properties
                            .of(Material.AMETHYST)
                            .noOcclusion()
                            .randomTicks()
                            .sound(SoundType.AMETHYST_CLUSTER)
                            .strength(1.5F).lightLevel((p_152632_) -> {return 5;
            })));

    public static final RegistryObject<Block> LARGE_SALT_BUD =
            registerNormal("large_salt_bud", () -> new AmethystClusterBlock(
                    5, 3, BlockBehaviour.Properties.copy(Blocks.STONE)
                    .sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((p_152629_) -> {
                return 4;
            })));

    public static final RegistryObject<Block> MEDIUM_SALT_BUD =
            registerNormal("medium_salt_bud", () -> new AmethystClusterBlock(
                    4, 3, BlockBehaviour.Properties.copy(Blocks.STONE)
                    .sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((p_152617_) -> {
                return 2;
            })));
    public static final RegistryObject<Block> SMALL_SALT_BUD =
            registerNormal("small_salt_bud", () -> new AmethystClusterBlock(
                    3, 4, BlockBehaviour.Properties.copy(Blocks.STONE)
                    .sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((p_187409_) -> {
                return 1;
            })));

    public static final RegistryObject<Block> SALT_BLOCK =
            registerNormal("salt_block", () -> new AmethystBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> SALT_ROCK_BLOCK =
            registerNormal("salt_rock_block", () -> new AmethystBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> STONE_MORTAR_BLOCK =
            registerNormal("stone_mortar_block", StoneMortarBlock::new);

    public static final RegistryObject<Block> RICE_CROP =
            register("rice_crop",
                    () -> new RiceBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F)));

    public static final RegistryObject<Block> RICE_CROP_PANICLES =
            register("rice_panicles",
                    () -> new RicePaniclesBlock(Block.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> FIRE_BOWL =
            registerDecoration("fire_bowl", RealisticFireBowlBlock::new);
    public static final RegistryObject<Block> SOUL_FIRE_BOWL =
            registerDecoration("soul_fire_bowl", RealisticSoulFireBowlBlock::new);

    public static final RegistryObject<Block> LARGE_FIRE_BOWL =
            registerDecoration("large_fire_bowl", RealisticLargeFireBowlBlock::new);

    public static final RegistryObject<Block> LARGE_SOUL_FIRE_BOWL =
            registerDecoration("large_soul_fire_bowl", RealisticLargeSoulFireBowl::new);

    public static final RegistryObject<Block> TORCH =
            register("torch", RealisticTorchBlock::new);

    public static final RegistryObject<Block> WALL_TORCH =
            register("torch_wall", RealisticWallTorchBlock::new);

    public static final RegistryObject<Block> SOUL_TORCH =
            register("soul_torch", RealisticSoulTorchBlock::new);
    public static final RegistryObject<Block> SOUL_WALL_TORCH =
            register("wall_soul_torch", RealisticSoulWallTorchBlock::new);

    public static final RegistryObject<Block> LANTERN =
            register("lantern", RealisticLanternBlock::new);
    public static final RegistryObject<Block> SOUL_LANTERN =
            register("soul_lantern", RealisticSoulLanternBlock::new);

    public static final RegistryObject<Block> WOODCUTTER =
            registerNormal("woodcutter", () -> new WoodcutterBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> AZALEA = registerDecoration("azalea", () -> new FlowerBlock(MobEffects.DIG_SPEED,
                    2, BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> PEONY = registerDecoration("peony", () -> new FlowerBlock(MobEffects.DIG_SPEED, 2
                    , BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> ROSE = registerDecoration("rose", () -> new FlowerBlock(MobEffects.DIG_SPEED, 2
            , BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> CHRYSANTHEMUM = registerDecoration("chrysanthemum",
            () -> new FlowerBlock(MobEffects.DIG_SPEED, 2, BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> CACTUS_BALL = registerDecoration("cactus_ball", () -> new CactusBallBlock(MobEffects.DIG_SPEED, 2
            , BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<Block> ALGAE =
            registerDecoration("algae", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.LILY_PAD).noOcclusion()));

    public static final RegistryObject<Block> CANDLE =
            registerDecoration("candle", RealisticCandleBlock::new);

    public static final RegistryObject<Block> CAMPFIRE =
            registerDecoration("campfire", RealisticCampFireBlock::new);

    public static final RegistryObject<Block> CHARCOAL_BLOCK =
            registerNormal("charcoal_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));

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