/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.block.QuickSandBlock;
import nameless.classicraft.block.SandStoneBlock;
import nameless.classicraft.block.StoneLikeBlock;
import nameless.classicraft.item.*;
import nameless.classicraft.util.Helpers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Set;

public class ModCreativeModeTabs {

    public static CreativeModeTab COMMON_TAB;
    public static CreativeModeTab BUILDING_BLOCKS;
    public static CreativeModeTab MATERIALS;
    public static CreativeModeTab NATURAL_BLOCKS;
    public static CreativeModeTab TOOLS;

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        Set<Item> items = Helpers.getItems();
        Set<Block> blocks = Helpers.getBlocks();
        COMMON_TAB = event.registerCreativeModeTab(Helpers.identifier("common"),
                builder -> builder.icon(() -> new ItemStack(ModItems.CLASSIC_CRAFT.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModItems.TORCH_UNLIT.get());
                            output.accept(ModItems.SOUL_TORCH_UNLIT.get());
                            output.accept(ModItems.TROUT.get());
                            output.accept(ModItems.COOKED_TROUT.get());
                            output.accept(ModItems.OCEAN_SHARK_SPAWN_EGG.get());
                            output.accept(ModItems.TROUT_SPAWN_EGG.get());
                            output.accept(ModItems.GLARE_SPAWN_EGG.get());
                            output.accept(ModItems.COOKED_EGG.get());
                            output.accept(ModItems.NETHER_MUSHROOM_STEW.get());
                            output.accept(ModItems.ROTTEN_FOOD.get());
                            output.accept(ModItems.PUMPKIN_SLICE.get());
                            output.accept(ModItems.CACTUS_FRUIT.get());
                            effects(ModPotions.MILK.get()).forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof ButtonBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof PressurePlateBlock)
                                    .forEach(output::accept);
                        }).title(Component.translatable("itemGroup.classicraft.common"))
                        .build());
        TOOLS = event.registerCreativeModeTab(Helpers.identifier("tools"),
                builder -> builder.icon(() -> new ItemStack(ModItems.IRON_HAMMER.get()))
                        .displayItems(((features, output, hasPermissions) -> {
                            buckets("_wooden").forEach(output::accept);
                            output.accept(ModItems.CERAMIC_BOWL.get());
                            output.accept(ModItems.DEBUG_BURN_TIME_STICK.get());
                            output.accept(ModItems.DEPTH_METER.get());
                            items.stream().filter(item -> item instanceof HammerItem)
                                    .forEach(output::accept);
                            items.stream().filter(item -> item instanceof DaggerItem)
                                    .forEach(output::accept);
                            items.stream().filter(item -> item instanceof JavelinItem)
                                    .forEach(output::accept);
                        })).title(Component.translatable("itemGroup.classicraft.tools"))
                        .build());
        BUILDING_BLOCKS = event.registerCreativeModeTab(Helpers.identifier("building_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.MOSSY_BRICKS.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            blocks.stream().filter(block -> block instanceof StairBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof FenceBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof WallBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SlabBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof StoneLikeBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SandStoneBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof QuickSandBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof SandBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof InfestedBlock)
                                    .forEach(output::accept);
                            blocks.stream().filter(block -> block instanceof CarpetBlock)
                                    .forEach(output::accept);
                            output.accept(ModBlocks.FLINT_BLOCK.get());
                            output.accept(ModBlocks.TALLOW_BLOCK.get());
                            output.accept(ModBlocks.THATCH.get());
                            output.accept(ModBlocks.DRIED_THATCH.get());
                        }).title(Component.translatable("itemGroup.classicraft.building_blocks"))
                        .build());

        MATERIALS = event.registerCreativeModeTab(Helpers.identifier("materials"),
                builder -> builder.icon(() -> new ItemStack(ModItems.MATERIAL.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModItems.SULFUR.get());
                            output.accept(ModItems.TALLOW.get());
                            output.accept(ModItems.MOSS_CLUMP.get());
                            output.accept(ModItems.PLAINTIVE_SOUL.get());
                            output.accept(ModItems.WITHER_BONE.get());
                            output.accept(ModItems.SHIVER_BONE.get());
                            output.accept(ModItems.FURIOUS_SOUL.get());
                            output.accept(ModItems.CAREFREE_SOUL.get());
                            output.accept(ModItems.TERRIFIED_SOUL.get());
                            output.accept(ModItems.NITER.get());
                            output.accept(ModItems.PHOBOS_OUTPOST_DISC.get());
                            output.accept(ModItems.DRAGON_FISH_DISC.get());
                            output.accept(ModItems.UNFIRED_CLAY_BRICK.get());
                            output.accept(ModItems.UNFIRED_CLAY_BOWL.get());
                            output.accept(ModItems.UNFIRED_CLAY_FLOWER_POT.get());
                            MetaItem.getMetaItems()
                                    .stream().filter(metaItem -> metaItem instanceof PebbleItem)
                                    .forEach(mi -> mi.acceptToCreativeModeTab(output));
                            MetaItem.getMetaItems()
                                    .stream().filter(metaItem -> metaItem instanceof PebbleToolItem)
                                    .forEach(mi -> mi.acceptToCreativeModeTab(output));
                        }).title(Component.translatable("itemGroup.classicraft.materials"))
                        .build());

        NATURAL_BLOCKS = event.registerCreativeModeTab(Helpers.identifier("natural_blocks"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.DEEPSLATE_SULFUR_ORE.get()))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(ModBlocks.DEEPSLATE_SULFUR_ORE.get());
                            output.accept(ModBlocks.NETHER_SULFUR_ORE.get());
                            output.accept(ModBlocks.NITER_BLOCK.get());
                            output.accept(ModBlocks.SANDSTONE_NITER_ORE.get());
                            output.accept(ModBlocks.QUARTZ_SANDSTONE_NITER_ORE.get());
                            output.accept(ModBlocks.RED_SANDSTONE_NITER_ORE.get());
                            output.accept(ModBlocks.SOUL_SANDSTONE_NITER_ORE.get());
                            output.accept(ModBlocks.SULFUR_BLOCK.get());
                            output.accept(ModBlocks.CACTUS_BALL.get());
                            output.accept(ModBlocks.ROSE.get());
                            output.accept(ModBlocks.CHARCOAL_BLOCK.get());
                            output.accept(ModBlocks.GLISTERING_MELON.get());
                            output.accept(ModBlocks.CATTAIL.get());
                            output.accept(ModBlocks.REED.get());
                        }).title(Component.translatable("itemGroup.classicraft.natural_blocks"))
                        .build());
    }

    static List<ItemStack> effects(Potion potion) {
        return List.of(
                PotionUtils.setPotion(new ItemStack(Items.POTION), potion),
                PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion),
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion),
                PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), potion)
        );
    }

    static List<ItemStack> buckets(String meta) {
        return List.of(
                MetaItem.setMetaItem(new ItemStack(Items.BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.WATER_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.LAVA_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.PUFFERFISH_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.SALMON_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.COD_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.TROPICAL_FISH_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.AXOLOTL_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.TADPOLE_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(ModItems.TROUT_BUCKET.get()), meta),
                MetaItem.setMetaItem(new ItemStack(Items.MILK_BUCKET), meta),
                MetaItem.setMetaItem(new ItemStack(Items.POWDER_SNOW_BUCKET), meta)
        );
    }
}
