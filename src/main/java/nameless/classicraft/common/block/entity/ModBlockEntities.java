package nameless.classicraft.common.block.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.block.entity.attach.*;
import nameless.classicraft.common.block.entity.attach.crop.CarrotBlockEntity;
import nameless.classicraft.common.block.entity.attach.crop.PotatoBlockEntity;
import nameless.classicraft.common.block.entity.attach.crop.WheatBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ClassiCraft.MODID);

    // attach

    public static final RegistryObject<BlockEntityType<BushBlockEntity>> BUSH = REGISTER.register("bush",
            () -> BlockEntityType.Builder.of(BushBlockEntity::new,
                    Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK,
                    Blocks.CRIMSON_FUNGUS, Blocks.WARPED_FUNGUS).build(null));

    public static final RegistryObject<BlockEntityType<CakeBlockEntity>> CAKE = REGISTER.register("cake",
            () -> BlockEntityType.Builder.of(CakeBlockEntity::new, Blocks.CAKE).build(null));

    public static final RegistryObject<BlockEntityType<PumpkinBlockEntity>> PUMPKIN = REGISTER.register("pumpkin",
            () -> BlockEntityType.Builder.of(PumpkinBlockEntity::new, Blocks.PUMPKIN).build(null));

    public static final RegistryObject<BlockEntityType<WheatBlockEntity>> WHEAT = REGISTER.register("wheat",
            () -> BlockEntityType.Builder.of(WheatBlockEntity::new, Blocks.WHEAT).build(null));

    public static final RegistryObject<BlockEntityType<CarrotBlockEntity>> CARROT = REGISTER.register("carrot",
            () -> BlockEntityType.Builder.of(CarrotBlockEntity::new, Blocks.CARROTS).build(null));

    public static final RegistryObject<BlockEntityType<PotatoBlockEntity>> POTATO = REGISTER.register("potato",
            () -> BlockEntityType.Builder.of(PotatoBlockEntity::new, Blocks.POTATOES).build(null));

    public static final RegistryObject<BlockEntityType<SugarCaneBlockEntity>> SUGAR_CANE = REGISTER.register("sugar_cane",
            () -> BlockEntityType.Builder.of(SugarCaneBlockEntity::new, Blocks.SUGAR_CANE).build(null));

    public static final RegistryObject<BlockEntityType<TurtleEggBlockEntity>> TURTLE_EGG = REGISTER.register("turtle_egg",
            () -> BlockEntityType.Builder.of(TurtleEggBlockEntity::new, Blocks.TURTLE_EGG).build(null));

    // new

    public static final RegistryObject<BlockEntityType<FridgeBlockEntity>> FRIDGE = REGISTER.register("fridge",
            () -> BlockEntityType.Builder.of(FridgeBlockEntity::new, ModBlocks.FRIDGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GlisteringMelonBlockEntity>> GLISTERING_MELON =
            REGISTER.register("glistering_melon", () -> BlockEntityType.Builder
                    .of(GlisteringMelonBlockEntity::new, ModBlocks.GLISTERING_MELON.get()).build(null));

    public static final RegistryObject<BlockEntityType<CactusFruitBlockEntity>> CACTUS_FRUIT =
            REGISTER.register("cactus_fruit", () -> BlockEntityType.Builder
                    .of(CactusFruitBlockEntity::new, ModBlocks.CACTUS_FRUIT.get()).build(null));

    public static final RegistryObject<BlockEntityType<MushroomPlanterBlockEntity>> MUSHROOM_PLANTER =
            REGISTER.register("mushroom_planter", () -> BlockEntityType.Builder
                    .of(MushroomPlanterBlockEntity::new, ModBlocks.MUSHROOM_PLANTER.get()).build(null));
}
