package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.realistic.RealisticSoulLanternBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LitSoulLanternItem extends BlockItem {

    public static final boolean HARDCORE = ClassiCraftConfiguration.hardcore.get();
    public static final Boolean WATER_BURNT = ClassiCraftConfiguration.waterBurnt.get();

    public LitSoulLanternItem(Item.Properties pProperties) {
        super(ModBlocks.SOUL_LANTERN.get(), pProperties);
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.lit_soul_lantern";
    }


    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        ItemStack pStack = pContext.getItemInHand();
        int burnTime;
        if(!pStack.getOrCreateTag().contains("burnTime"))
        {
            burnTime = RealisticSoulLanternBlock.getInitialBurnTime();
            pStack.getTag().putInt("burnTime",burnTime);
        }
        else
        {
            burnTime = pStack.getTag().getInt("burnTime");
        }
        BlockState state = super.getPlacementState(pContext);
        if(state != null)
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                pContext.getLevel().playSound(null,pContext.getClickedPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,0.3f, pContext.getLevel().random.nextFloat() * 0.1F + 0.6F);
                return state.setValue(RealisticSoulLanternBlock.getLitState(), RealisticSoulLanternBlock.SMOLDERING).setValue(RealisticSoulLanternBlock.BURNTIME,burnTime);
            }
            else
                return state.setValue(RealisticSoulLanternBlock.getLitState(), RealisticSoulLanternBlock.LIT).setValue(RealisticSoulLanternBlock.BURNTIME,burnTime);
        }
        return null;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!HARDCORE || pLevel.isClientSide() || !(pEntity instanceof Player player)) return;
        if(inWater(player.getOnPos(),pLevel) && WATER_BURNT)
        {
            changeLantern(player,pStack, ItemStackAPI.replaceItemWithCopyNBTTagAndCount(pStack, ModItems.SOUL_LANTERN.get()),pSlotId);
            pLevel.playSound(null,player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,0.3f, pLevel.random.nextFloat() * 0.1F + 0.6F);
        }
    }

    public static boolean inWater(BlockPos pos, Level level)
    {
        return level.getBlockState(pos).is(ModTags.Blocks.TORCH_CAN_BE_BURNT_OUT);
    }

    public static void changeLantern(Player player, ItemStack stack, ItemStack newStack, int slot)
    {
        if(player.getItemInHand(InteractionHand.MAIN_HAND) == stack)
        {
            EquipmentSlot pSlot = EquipmentSlot.MAINHAND;
            player.setItemSlot(pSlot,newStack);
            return;
        }
        else if(player.getItemInHand(InteractionHand.OFF_HAND) == stack )
        {
            player.setItemSlot(EquipmentSlot.OFFHAND,newStack);
            return;
        }
        player.getInventory().setItem(slot,newStack);
    }
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if(oldStack.getOrCreateTag().contains("burnTime") && newStack.getOrCreateTag().contains("burnTime"))
        {
            return oldStack.getOrCreateTag().getInt("burnTime") != oldStack.getOrCreateTag().getInt("burnTime");
        }
        return false;
    }
}