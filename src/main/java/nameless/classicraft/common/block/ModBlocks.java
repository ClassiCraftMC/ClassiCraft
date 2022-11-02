package nameless.classicraft.common.block;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ClassiCraft.MODID);

    public static final RegistryObject<Block> FRIDGE = REGISTER.register("fridge",
            () -> new FridgeBlock(wood(), ModBlockEntities.FRIDGE));
    public static final RegistryObject<Block> MUSHROOM_PLANTER = REGISTER.register("mushroom_planter",
            () -> new MushroomPlanterBlock(wood().noOcclusion(), ModBlockEntities.MUSHROOM_PLANTER));
    public static final RegistryObject<Block> UNLIT_LANTERN = REGISTER.register("unlit_lantern", () -> new UnlitLanternBlock(lantern()));
    public static final RegistryObject<Block> UNLIT_SOUL_LANTERN = REGISTER.register("unlit_soul_lantern", () -> new UnlitLanternBlock(lantern()));
    public static final RegistryObject<Block> GLISTERING_MELON = REGISTER.register("glistering_melon", () -> new GlisteringMelonBlock(melon()));
    public static final RegistryObject<Block> CACTUS_FRUIT = REGISTER.register("cactus_fruit", () -> new CactusFruitBlock(cactus()));
    public static final RegistryObject<Block> UNLIT_TORCH = REGISTER.register("unlit_torch", () -> new UnlitTorchBlock(torch()));
    public static final RegistryObject<Block> WALL_UNLIT_TORCH = REGISTER.register("wall_unlit_torch", () -> new WallUnlitTorchBlock(torch()));
    public static final RegistryObject<Block> UNLIT_SOUL_TORCH = REGISTER.register("unlit_soul_torch", () -> new UnlitTorchBlock(torch()));
    public static final RegistryObject<Block> WALL_UNLIT_SOUL_TORCH = REGISTER.register("wall_unlit_soul_torch", () -> new WallUnlitTorchBlock(torch()));

    // 批量添加不同材料和颜色的烛台方块
    public static final ArrayList<RegistryObject<Block>> UNLIT_CANDLEHOLDERS = new ArrayList<RegistryObject<Block>>(){{
        for (String material : Arrays.asList("golden_","","copper_"))//铁制烛台的命名空间不带有iron
           for (DyeColor dyeColor: DyeColor.values())
                add(REGISTER.register("unlit_" + material + dyeColor.getName() + "_candleholder", () -> new UnlitCandleholderBlock(candleholder())));
    }};

    public static final ArrayList<RegistryObject<Block>> UNLIT_LARGE_CANDLEHOLDERS = new ArrayList<RegistryObject<Block>>(){{
        for (String material : Arrays.asList("golden_","","copper_"))
            for (DyeColor dyeColor: DyeColor.values())
                add(REGISTER.register("unlit_large_" + material + dyeColor.getName() + "_candleholder", () -> new UnlitLargeCandleholderBlock(largeCandleholder())));
    }};

    public static final RegistryObject<Block> UNLIT_FIRE_BOW = REGISTER.register("unlit_fire_bowl", () -> new UnlitFireBowlBlock(fireBowl()));


    public static final  RegistryObject<Block> UNLIT_SOUL_FIRE_BOW = REGISTER.register("unlit_soul_fire_bowl", () -> new UnlitFireBowlBlock(fireBowl()));
    public static final RegistryObject<Block> UNLIT_LARGE_FIRE_BOW = REGISTER.register("unlit_large_fire_bowl", () -> new UnlitFireBowlBlock(fireBowl()));
    public static final RegistryObject<Block> UNLIT_LARGE_SOUL_FIRE_BOW = REGISTER.register("unlit_large_soul_fire_bowl", () -> new UnlitLargeFireBowlBlock(fireBowl()));
    static BlockBehaviour.Properties lantern() {
        return BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN);
    }

    static BlockBehaviour.Properties torch() {
        return BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties candleholder() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties largeCandleholder() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties fireBowl() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties largeFireBow() {
        return BlockBehaviour.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties wood() {
        return BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties melon() {
        return BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD);
    }

    static BlockBehaviour.Properties cactus() {
        return BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).noOcclusion().sound(SoundType.WOOL);
    }

}