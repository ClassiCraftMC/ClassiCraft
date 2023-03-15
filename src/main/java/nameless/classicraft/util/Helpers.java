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
package nameless.classicraft.util;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A utility class of ClassiCraft
 */
public final class Helpers {


    public static final DeferredRegister<? extends Block> BLOCK_LIST = ModBlocks.BLOCKS;
    public static final DeferredRegister<? extends Item> ITEM_LIST = ModItems.ITEMS;

    /**
     * A method that create a new ItemEntity
     * @param level the world taht ItemEntity generate
     * @param pos drop block pos
     * @param stack the origin ItemStack
     * @return a new Item Entity
     */
    public static ItemEntity itemEntity(Level level, BlockPos pos, ItemStack stack) {
        return new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static String namespace(String name) {
        return ClassiCraftMod.MOD_ID + ":" + name;
    }

    public static ItemStack randomStack(Level level, Item drop, int min, int max) {
        return new ItemStack(drop, max - min > 0 ? level.getRandom().nextInt(max - min + 1) + min : min);
    }

    public static ItemStack randomStack(Level level, Item drop, int min, int max, int looting) {
        if (looting > 0) {
            return new ItemStack(drop, max+looting - min > 0
                    ? level.getRandom().nextInt(max+looting - min + 1)
                    + min : min);
        }else {
            return randomStack(level, drop, min, max);
        }
    }

    public static ItemStack stack(Level level, Item drop, int count) {
        return randomStack(level, drop, count, count);
    }

    public static ItemStack stack(Level level, Item drop, int count, int looting) {
        return randomStack(level, drop, count, count, looting);
    }

    /**
     * A method that create a new ItemStack in order to simplify code
     * @param item the Item
     * @return new ItemStack
     */
    public static ItemStack stack(ItemLike item) {
        return new ItemStack(item);
    }

    /**
     * Use for create cool down of item
     * @param player the player who use it
     * @param item the items
     * @param ticks remain ticks
     */
    public static void addCoolDown(Player player, Item item, int ticks) {
        player.getCooldowns().addCooldown(item, ticks);
    }

    /**
     * Use for vanilla meta handle
     * @param stack item stack should handle
     */
    public static void setVanillaMeta(ItemStack stack) {
        if (stack.is(Items.FLINT)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "flint_pebble");
        }
        if (stack.is(Items.PRISMARINE_SHARD)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "prismarine_pebble");
        }
        if (stack.is(Items.QUARTZ)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "quartz_pebble");
        }
    }

    /**
     * Default {@link ResourceLocation}, except with a ClassiCraft namespace
     */
    public static ResourceLocation identifier(String name) {
        return new ResourceLocation(ClassiCraftMod.MOD_ID, name);
    }

    /**
     * A method used to get Blocks that ClassiCraft mod registered
     * @return a set list of blocks
     */
    public static Set<Block> getBlocks() {
        return BLOCK_LIST.getEntries().stream()
                .map(RegistryObject::get).collect(Collectors.toSet());
    }

    /**
     * A method used to get Items that ClassiCraft mod registered
     * @return a set list of items
     */
    public static Set<Item> getItems() {
        return ITEM_LIST.getEntries().stream()
                .map(RegistryObject::get).collect(Collectors.toSet());
    }

    /**
     * Used to handle food values
     */
    @SuppressWarnings("deprecation")
    public static void handleFood() {
        if (Items.DRIED_KELP.getFoodProperties() != null) {
            Items.DRIED_KELP.getFoodProperties().canAlwaysEat = true;
            Items.DRIED_KELP.getFoodProperties().nutrition = 0;
            Items.DRIED_KELP.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.CHORUS_FRUIT.getFoodProperties() != null) {
            Items.CHORUS_FRUIT.getFoodProperties().canAlwaysEat = true;
            Items.CHORUS_FRUIT.getFoodProperties().nutrition = 0;
            Items.CHORUS_FRUIT.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.ROTTEN_FLESH.getFoodProperties() != null) {
            Items.ROTTEN_FLESH.getFoodProperties().canAlwaysEat = true;
            Items.ROTTEN_FLESH.getFoodProperties().nutrition = 0;
            Items.ROTTEN_FLESH.getFoodProperties().saturationModifier = 0.0F;
        }
    }

    public static void obtainAdvancement(Player player, String id) {
        if (player instanceof ServerPlayer serverPlayer) {
            Advancement advancement =
                    Objects.requireNonNull(serverPlayer.getServer())
                            .getAdvancements().getAdvancement(Helpers.identifier(id));
            assert advancement != null;
            AdvancementProgress progress = serverPlayer.getAdvancements()
                    .getOrStartProgress(advancement);
            if (!progress.isDone()) {
                for (String s : progress.getRemainingCriteria())
                    serverPlayer.getAdvancements().award(advancement, s);
            }
        }
    }
}
