package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.block.*;
import nameless.classicraft.util.ExtraUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Set;

public class ModCreativeModeTabs {

    public static CreativeModeTab COMMON_TAB;
    public static CreativeModeTab BUILDING_BLOCKS;
    public static CreativeModeTab MATERIAL;
    public static CreativeModeTab NATURAL_BLOCKS;

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        COMMON_TAB = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID, "common"),
                builder -> builder.icon(() -> new ItemStack(ModItems.CLASSIC_CRAFT.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModItems.TORCH_UNLIT.get());
                            output.accept(ModItems.SOUL_TORCH_UNLIT.get());
                            output.accept(ModItems.DEBUG_BURN_TIME_STICK.get());
                            output.accept(ModItems.DEPTH_METER.get());
                            output.accept(ModItems.TROUT.get());
                            output.accept(ModItems.COOKED_TROUT.get());
                            output.accept(ModItems.TROUT_BUCKET.get());
                            output.accept(ModItems.OCEAN_SHARK_SPAWN_EGG.get());
                            output.accept(ModItems.TROUT_SPAWN_EGG.get());
                            output.accept(ModItems.LIVING_DEAD_SPAWN_EGG.get());
                            output.accept(ModItems.COOKED_EGG.get());
                            output.accept(ModItems.NETHER_MUSHROOM_STEW.get());
                            output.accept(ModItems.ROTTEN_FOOD.get());
                            output.accept(ModItems.PHOBOS_OUTPOST_DISC.get());
                            output.accept(ModItems.DRAGON_FISH_DISC.get());
                        })
                        .title(Component.translatable("itemGroup.classicraft.common"))
                        .build());

        BUILDING_BLOCKS = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID, "building_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.MOSSY_BRICKS.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            Set<Block> blocks = ExtraUtils.getBlocks();
                            blocks.stream().filter(block -> block instanceof StairBlock)
                                            .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof StainedGlassStairsBlock)
                                            .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof FenceBlock)
                                            .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof WallBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof StainedGlassWallBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SlabBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof StainedGlassSlabBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof StoneBricksBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SandStoneBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof QuickSandBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SandBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof InfestedBlock)
                                            .forEach(output::accept);
                            output.accept(ModBlocks.FLINT_BLOCK.get());
                            output.accept(ModBlocks.TALLOW_BLOCK.get());
                        }).title(Component.translatable("itemGroup.classicraft.building_blocks"))
                        .build());

        MATERIAL = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID, "material"),
                builder -> builder.icon(() -> new ItemStack(ModItems.MATERIAL.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModItems.SULFUR.get());
                            output.accept(ModItems.TALLOW.get());
                            MetaItem.getMetaItems().forEach(mi -> mi.acceptToCreativeModeTab(output));                        })
                        .title(Component.translatable("itemGroup.classicraft.material"))
                        .build());

        NATURAL_BLOCKS = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID, "natural_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.SULFUR_ORE.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModBlocks.SULFUR_ORE.get());
                            output.accept(ModBlocks.CACTUS_BALL.get());
                            output.accept(ModBlocks.ROSE.get());
                            output.accept(ModBlocks.CHARCOAL_BLOCK.get());
                        }).title(Component.translatable("itemGroup.classicraft.natural_blocks"))
                        .build());
    }
}
