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
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ClassiCraft.MODID);

    // attach

    public static final RegistryObject<BlockEntityType<BushBlockEntity>> BUSH = BLOCK_ENTITY_TYPES.register("bush",
            () -> BlockEntityType.Builder.of(BushBlockEntity::new,
                    Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK,
                    Blocks.CRIMSON_FUNGUS, Blocks.WARPED_FUNGUS).build(null));

    public static final RegistryObject<BlockEntityType<CakeBlockEntity>> CAKE = BLOCK_ENTITY_TYPES.register("cake",
            () -> BlockEntityType.Builder.of(CakeBlockEntity::new, Blocks.CAKE).build(null));

    public static final RegistryObject<BlockEntityType<PumpkinBlockEntity>> PUMPKIN = BLOCK_ENTITY_TYPES.register("pumpkin",
            () -> BlockEntityType.Builder.of(PumpkinBlockEntity::new, Blocks.PUMPKIN).build(null));

    public static final RegistryObject<BlockEntityType<WheatBlockEntity>> WHEAT = BLOCK_ENTITY_TYPES.register("wheat",
            () -> BlockEntityType.Builder.of(WheatBlockEntity::new, Blocks.WHEAT).build(null));

    public static final RegistryObject<BlockEntityType<CarrotBlockEntity>> CARROT = BLOCK_ENTITY_TYPES.register("carrot",
            () -> BlockEntityType.Builder.of(CarrotBlockEntity::new, Blocks.CARROTS).build(null));

    public static final RegistryObject<BlockEntityType<PotatoBlockEntity>> POTATO = BLOCK_ENTITY_TYPES.register("potato",
            () -> BlockEntityType.Builder.of(PotatoBlockEntity::new, Blocks.POTATOES).build(null));

    public static final RegistryObject<BlockEntityType<SugarCaneBlockEntity>> SUGAR_CANE = BLOCK_ENTITY_TYPES.register("sugar_cane",
            () -> BlockEntityType.Builder.of(SugarCaneBlockEntity::new, Blocks.SUGAR_CANE).build(null));

    public static final RegistryObject<BlockEntityType<TurtleEggBlockEntity>> TURTLE_EGG = BLOCK_ENTITY_TYPES.register("turtle_egg",
            () -> BlockEntityType.Builder.of(TurtleEggBlockEntity::new, Blocks.TURTLE_EGG).build(null));

    // new
    public static final RegistryObject<BlockEntityType<FridgeBlockEntity>> FRIDGE = BLOCK_ENTITY_TYPES.register("fridge",
            () -> BlockEntityType.Builder.of(FridgeBlockEntity::new, ModBlocks.FRIDGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GlisteringMelonBlockEntity>> GLISTERING_MELON =
            BLOCK_ENTITY_TYPES.register("glistering_melon", () -> BlockEntityType.Builder
                    .of(GlisteringMelonBlockEntity::new, ModBlocks.GLISTERING_MELON.get()).build(null));

    public static final RegistryObject<BlockEntityType<CactusFruitBlockEntity>> CACTUS_FRUIT =
            BLOCK_ENTITY_TYPES.register("cactus_fruit", () -> BlockEntityType.Builder
                    .of(CactusFruitBlockEntity::new, ModBlocks.CACTUS_FRUIT.get()).build(null));

    public static final RegistryObject<BlockEntityType<MushroomPlanterBlockEntity>> MUSHROOM_PLANTER =
            BLOCK_ENTITY_TYPES.register("mushroom_planter", () -> BlockEntityType.Builder
                    .of(MushroomPlanterBlockEntity::new, ModBlocks.MUSHROOM_PLANTER.get()).build(null));
}
