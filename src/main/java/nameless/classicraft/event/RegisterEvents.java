package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterEvents {

    public static CreativeModeTab COMMON_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        COMMON_TAB = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID, "common"),
                builder -> builder.icon(() -> new ItemStack(ModItems.CLASSIC_CRAFT.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModItems.TORCH_UNLIT.get());
                            output.accept(ModItems.SOUL_TORCH_UNLIT.get());
                            output.accept(ModItems.DEBUG_TIME_STICK.get());
                            output.accept(ModItems.DEPTH_METER.get());
                            output.accept(ModItems.BIOME_COMPASS.get());
                            output.accept(ModItems.TROUT.get());
                            output.accept(ModItems.COOKED_TROUT.get());
                            output.accept(ModItems.TROUT_BUCKET.get());
                            output.accept(ModItems.OCEAN_SHARK_SPAWN_EGG.get());
                            output.accept(ModItems.TROUT_SPAWN_EGG.get());
                            output.accept(ModItems.LIVING_DEAD_SPAWN_EGG.get());
                            output.accept(ModItems.TALLOW.get());
                            output.accept(ModItems.COOKED_EGG.get());
                            output.accept(ModItems.NETHER_MUSHROOM_STEW.get());
                            output.accept(ModItems.ROTTEN_FOOD.get());
                            output.accept(ModBlocks.CHARCOAL_BLOCK.get());
                            output.accept(ModBlocks.CACTUS_BALL.get());
                            output.accept(ModBlocks.QUICKSAND.get());
                            output.accept(ModBlocks.RED_QUICKSAND.get());
                            output.accept(ModBlocks.ROSE.get());
                            output.accept(ModBlocks.TALLOW_BLOCK.get());
                            output.accept(ModBlocks.MOSSY_BRICKS.get());
                            output.accept(ModBlocks.MOSSY_BRICKS_STAIRS.get());
                            output.accept(ModBlocks.MOSSY_BRICKS_WALL.get());
                            output.accept(ModBlocks.MOSSY_BRICKS_SLAB.get());
                            output.accept(ModBlocks.CRACKED_BRICKS.get());
                            output.accept(ModBlocks.CRACKED_BRICKS_STAIRS.get());
                            output.accept(ModBlocks.CRACKED_BRICKS_WALL.get());
                            output.accept(ModBlocks.CRACKED_BRICKS_SLAB.get());
                            output.accept(ModBlocks.TWIGS.get());
                            output.accept(ModBlocks.SULFUR_ORE.get());
                            output.accept(ModItems.SULFUR.get());
                            output.accept(ModBlocks.STONE_WALL.get());
                            output.accept(ModBlocks.SMOOTH_STONE_STAIRS.get());
                            output.accept(ModBlocks.SMOOTH_STONE_WALL.get());
                            output.accept(ModBlocks.FLINT_BLOCK.get());
                            output.accept(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get());
                            output.accept(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
                            output.accept(ModBlocks.CUT_QUARTZ_SANDSTONE.get());
                            output.accept(ModBlocks.CUT_SOUL_SANDSTONE.get());
                            output.accept(ModBlocks.QUARTZ_QUICKSAND.get());
                            output.accept(ModBlocks.QUARTZ_SANDSTONE.get());
                            MetaItem.getMetaItems().forEach(mi -> mi.acceptToCreativeModeTab(output));
                        })
                        .title(Component.translatable("itemGroup.classicraft.common"))
                        .build());
    }
}
