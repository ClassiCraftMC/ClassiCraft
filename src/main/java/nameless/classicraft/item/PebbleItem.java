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
package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.api.item.MetaItemImpl;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.init.ModSounds;
import nameless.classicraft.init.ModTags;
import nameless.classicraft.util.Helpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class PebbleItem extends MetaItemImpl {
    public static String getPebbleType(ItemStack stack) {
        Helpers.setVanillaMeta(stack);
        String meta = MetaItem.getMeta(stack);
        return meta.substring(0, meta.length() - 7);
    }

    public PebbleItem() {
        super(new Item.Properties(), List.of(
                "andesite_pebble",
                "basalt_pebble",
                "blackstone_pebble",
                "cobblestone_pebble",
                "deepslate_pebble",
                "diorite_pebble",
                "granite_pebble",
                "red_sandstone_pebble",
                "sandstone_pebble",
                "quartz_sandstone_pebble",
                "soul_sandstone_pebble",
                "netherrack_pebble",
                "end_stone_pebble"
        ));
    }

    public static String metaFromBlock(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    public static ItemStack fromBlock(Block block) {
        ItemStack result = new ItemStack(ModItems.PEBBLE.get());
        MetaItem.setMeta(result, metaFromBlock(block));
        return result;
    }

    public static CompoundTag getTagFrom(Block block) {
        CompoundTag result = new CompoundTag();
        result.put(ClassiCraftMod.MOD_ID, new CompoundTag());
        result.getCompound(ClassiCraftMod.MOD_ID).putString("Meta", metaFromBlock(block));
        return result;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        ItemStack item = pContext.getItemInHand();
        Player player = pContext.getPlayer();

        if (player != null && player.isShiftKeyDown() &&
                level.getBlockState(pContext.getClickedPos()).getMaterial() == Material.STONE) {
            player.swing(pContext.getHand());
            Helpers.addCoolDown(player, item.getItem(), 60);
            if (addItem(player, item))
                item.shrink(1);

            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResult.SUCCESS;
        } else
            return Objects.requireNonNull(ForgeRegistries.BLOCKS
                            .getValue(new ResourceLocation(ClassiCraftMod.MOD_ID, MetaItem.getMeta(item))))
                    .asItem()
                    .useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pUsedHand == InteractionHand.MAIN_HAND) {
            ItemStack main = pPlayer.getMainHandItem();
            ItemStack off = pPlayer.getOffhandItem();

            if (off.getItem() instanceof PebbleItem || off.is(ModTags.Items.VANILLA_PEBBLES)) {
                pPlayer.swing(pUsedHand);
                Helpers.addCoolDown(pPlayer, this, 60);
                if (addItem(pPlayer, main)) {
                    main.shrink(1);
                    off.shrink(1);
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    /**
     * Add random tools to player inventory and check wheather if success
     *
     * @param player Player
     * @param item   HandHeld Item(Left or Right Hand)
     * @return result to true if you need decrease
     * Item count(The tools exist, and in the server side,player isn't creative mode
     */
    public static boolean addItem(Player player, ItemStack item) {
        Level level = player.level;

        if (!level.isClientSide) {
            ItemStack tool = PebbleToolItem.randomFrom(item, level.random);

            if (!tool.isEmpty()
                    && level.random.nextInt(100) >= 85)
                player.addItem(ItemStack.EMPTY);
            if (!tool.isEmpty()
                    && level.random.nextInt(100) <= 50)
                player.addItem(tool);
            if (!tool.isEmpty()
                    && level.random.nextInt(100) > 50
                    && level.random.nextInt(100) < 85)
                return false;

            return !player.isCreative();
        } else
            player.playSound(ModSounds.PEBBLE.get());

        return false;
    }
}
