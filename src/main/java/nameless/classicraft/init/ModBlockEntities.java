package nameless.classicraft.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.entity.*;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ClassiCraftMod.MODID);

    // new
    public static final RegistryObject<BlockEntityType<RealisticCampfireBlockEntity>> CAMPFIRE
            = BLOCK_ENTITY_TYPES.register("campfire",
            () -> BlockEntityType.Builder
                    .of(RealisticCampfireBlockEntity::new,
                            ModBlocks.CAMPFIRE.get()).build(null));

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

    public static RegistryObject<BlockEntityType<StoneMortarBlockEntity>> STONE_MORTAR_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("stone_mortar_block", () -> BlockEntityType.Builder.of(
                    StoneMortarBlockEntity::new, ModBlocks.STONE_MORTAR_BLOCK.get()).build(null));
}