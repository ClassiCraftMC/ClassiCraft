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

import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.block.RealSoulTorchBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModConfigurations;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.LightUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitSoulTorchItem extends StandingAndWallBlockItem {

    private static final boolean HARDCORE = ModConfigurations.hardcore.get();
    private static final Boolean WATER_BURNT = ModConfigurations.waterBurnt.get();

    public LitSoulTorchItem() {
        super(ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                new Properties().stacksTo(64),
                Direction.DOWN);
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.soul_torch_lit";
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        LightUtils.shiftItem(pPlayer, this.getDefaultInstance(), ModItems.SOUL_TORCH_UNLIT.get());
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!HARDCORE || pLevel.isClientSide() || !(pEntity instanceof Player player)) return;
        if(pLevel.isRainingAt(player.getOnPos().above(2))) {
            changeTorch(player,pStack, ItemStackAPI.replaceItemWitchNoNBT(pStack, Items.STICK), pSlotId);
            pLevel.playSound(null,player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,0.3f, pLevel.random.nextFloat() * 0.1F + 0.6F);
        }
        if(inWater(player.getOnPos(),pLevel) && WATER_BURNT) {
            changeTorch(player,pStack, ItemStackAPI.replaceItemWitchNoNBT(pStack, Items.STICK), pSlotId);
            pLevel.playSound(null,player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,0.3f, pLevel.random.nextFloat() * 0.1F + 0.6F);
        }
    }

    private boolean inWater(BlockPos pos, Level level) {
        return level.getBlockState(pos).is(Blocks.WATER);
    }

    public void changeTorch(Player player,ItemStack stack,ItemStack newStack,int slot) {
        if(player.getItemInHand(InteractionHand.MAIN_HAND) == stack) {
            EquipmentSlot pSlot = EquipmentSlot.MAINHAND;
            player.setItemSlot(pSlot,newStack);
            return;
        } else if(player.getItemInHand(InteractionHand.OFF_HAND) == stack ) {
            player.setItemSlot(EquipmentSlot.OFFHAND,newStack);
            return;
        }
        player.getInventory().setItem(slot,newStack);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        ItemStack pStack = pContext.getItemInHand();
        int burnTime;
        if(!pStack.getOrCreateTag().contains("burnTime")) {
            burnTime = RealSoulTorchBlock.getInitialBurnTime();
            if (pStack.getTag() != null) {
                pStack.getTag().putInt("burnTime",burnTime);
            }
        } else {
            if (pStack.getTag() != null) {
                burnTime = pStack.getTag().getInt("burnTime");
            }
        }
        BlockState state = super.getPlacementState(pContext);
        if(state != null) {
            if (pContext.getLevel().isRainingAt(pContext.getClickedPos().above())) {
                pContext.getLevel().playSound(null, pContext.getClickedPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.3f, pContext.getLevel().random.nextFloat() * 0.1F + 0.6F);
                return state.setValue(AbstractLightBlock.getLitState(), false).setValue(RealSoulTorchBlock.getBurnTime(), RealSoulTorchBlock.getInitialBurnTime());
            } else {
                return state.setValue(AbstractLightBlock.getLitState(), true).setValue(RealSoulTorchBlock.getBurnTime(), RealSoulTorchBlock.getInitialBurnTime());
            }
        }
        return null;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if(oldStack.getOrCreateTag().contains("burnTime") && newStack.getOrCreateTag().contains("burnTime")) {
            return oldStack.getOrCreateTag().getInt("burnTime") != oldStack.getOrCreateTag().getInt("burnTime");
        }
        return false;
    }
}
