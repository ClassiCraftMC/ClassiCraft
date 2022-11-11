package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
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

public class LitLantern extends BlockItem {

    public static final boolean HARDCORE = ClassiCraftConfiguration.hardcore.get();
    public static final Boolean WATER_BURNT = ClassiCraftConfiguration.waterBurnt.get();

    public LitLantern() {
        super(ModBlocks.LANTERN.get() , new Item.Properties());
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.lit_lantern";
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!HARDCORE || pLevel.isClientSide() || !(pEntity instanceof Player player)) return;
        if(WATER_BURNT)
        {
            changeLantern(player,pStack, ItemStackAPI.replaceItemWithCopyNBTTagAndCount(pStack, ModItems.LANTERN.get()),pSlotId);
            pLevel.playSound(null,player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,0.3f, pLevel.random.nextFloat() * 0.1F + 0.6F);
        }
    }

    public static void changeLantern(Player player,ItemStack stack,ItemStack newStack,int slot)
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

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        ItemStack pStack = pContext.getItemInHand();
        if(!pStack.getOrCreateTag().contains("oil"))
            pStack.getOrCreateTag().putInt("oil",0);
        if(!pStack.getOrCreateTag().contains("burnTime"))
            pStack.getOrCreateTag().putInt("burnTime", ClassiCraftConfiguration.lanternBurnoutTime.get());
        if(!pStack.getOrCreateTag().contains("lit_state"))
            pStack.getOrCreateTag().putInt("lit_state",1);
        BlockState state = super.getPlacementState(pContext);
        if(state != null)
        {
            return state.setValue(RealisticLanternBlock.LITSTATE,pStack.getOrCreateTag().getInt("lit_state")).setValue(RealisticLanternBlock.BURNTIME,pContext.getItemInHand().getOrCreateTag().getInt("burnTime")).setValue(RealisticLanternBlock.OIL,pContext.getItemInHand().getOrCreateTag().getInt("oil"));
        }
        return null;
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
