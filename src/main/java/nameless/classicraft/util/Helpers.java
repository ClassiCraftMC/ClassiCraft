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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public final class Helpers {


    public static final DeferredRegister<? extends Block> deferredRegister = ModBlocks.BLOCKS;

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
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "prismarine");
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

    public static Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream()
                .map(RegistryObject::get).collect(Collectors.toSet());
    }

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
}
