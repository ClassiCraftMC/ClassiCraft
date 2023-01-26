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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class ExtraUtils {

    public static final DeferredRegister<? extends Block> deferredRegister = ModBlocks.BLOCKS;

    public static void addPebbleCoolDown(Player player, Item item) {
        player.getCooldowns().addCooldown(item, 10);
    }

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

    public static Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

}
