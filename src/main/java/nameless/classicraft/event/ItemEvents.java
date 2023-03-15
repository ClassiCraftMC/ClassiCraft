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
package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class ItemEvents {

    @SubscribeEvent
    public static void fillBucket(FillBucketEvent event) {
        if (event.getTarget() instanceof BlockHitResult bhr) {
            if (bhr.getType() != HitResult.Type.MISS
                    && bhr.getType() == HitResult.Type.BLOCK) {
                BlockPos targetPos = bhr.getBlockPos();
                Direction direction = bhr.getDirection();
                BlockPos relativePos = targetPos.relative(direction);

                Level level = event.getLevel();
                Player player = event.getEntity();
                ItemStack sourceBucket = event.getEmptyBucket();

                if (level.mayInteract(player, targetPos) && player.mayUseItemAt(relativePos, direction, sourceBucket)
                        && sourceBucket.getItem() instanceof BucketItem bi) {
                    BlockState targetBlock = level.getBlockState(targetPos);

                    if (bi.content == Fluids.EMPTY) {
                        if (targetBlock.getBlock() instanceof BucketPickup bp) {
                            if (MetaItem.getMeta(sourceBucket).equals("_wooden") && targetBlock.getFluidState().getType() == Fluids.LAVA) {
                                event.setCanceled(true);
                                return;
                            }

                            ItemStack pickItem = bp.pickupBlock(level, targetPos, targetBlock);

                            MetaItem.setMeta(pickItem, Objects.requireNonNullElse(MetaItem.getMeta(sourceBucket), ""));

                            level.playSound(null, targetPos, SoundEvents.BUCKET_FILL,
                                    SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);

                            event.setFilledBucket(pickItem);
                            event.setResult(Event.Result.ALLOW);
                        }
                    } else {
                        bi.content = bi.getFluid();

                        BlockPos pos = canBlockContainFluid(level, targetPos, targetBlock, bi.content) ? targetPos : relativePos;

                        if (bi.emptyContents(player, level, pos, bhr)) {
                            bi.checkExtraContent(player, level, sourceBucket, pos);
                            ItemStack bucket = new ItemStack(Items.BUCKET);
                            MetaItem.setMeta(bucket, MetaItem.getMeta(sourceBucket));
                            level.playSound(null, targetPos, SoundEvents.BUCKET_EMPTY,
                                    SoundSource.BLOCKS,1, level.random.nextFloat() * 0.1F + 0.9F);
                            event.setFilledBucket(bucket);
                            event.setResult(Event.Result.ALLOW);
                        }
                    }
                }
            }
        }
    }

    private static boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate, Fluid content) {
        return blockstate.getBlock() instanceof LiquidBlockContainer lbc && lbc.canPlaceLiquid(worldIn, posIn, blockstate, content);
    }

    @SubscribeEvent
    public static void addTooltip(ItemTooltipEvent event) {
        var itemStack = event.getItemStack();
        var toolTip = event.getToolTip();
        var food = itemStack.getItem();
        var foodData = food.getFoodProperties();
        if (itemStack.isEdible()) {
            if (!Screen.hasShiftDown()) {
                toolTip.add(Component.translatable("info.classicraft.shift_press").withStyle(ChatFormatting.ITALIC));
            } else {
                if (foodData != null) {
                    int nutrition = foodData.getNutrition();
                    float satur = foodData.getSaturationModifier();
                    toolTip.add(Component.translatable("info.classicraft.food.nutrition").append(":" + nutrition).withStyle(ChatFormatting.WHITE));
                    toolTip.add(Component.translatable("info.classicraft.food.saturation").append(":" + satur).withStyle(ChatFormatting.WHITE));
                }
            }
        }
    }
}
