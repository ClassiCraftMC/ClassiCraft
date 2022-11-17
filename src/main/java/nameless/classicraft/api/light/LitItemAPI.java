package nameless.classicraft.api.light;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public interface LitItemAPI {

    boolean HARDCORE = ClassiCraftConfiguration.hardcore.get();
    Boolean WATER_BURNT = ClassiCraftConfiguration.waterBurnt.get();

    default BlockState getLanternPlacementState(BlockPlaceContext pContext, Block block) {
        ItemStack pStack = pContext.getItemInHand();
        if(!pStack.getOrCreateTag().contains("oil"))
            pStack.getOrCreateTag().putInt("oil",0);
        if(!pStack.getOrCreateTag().contains("burnTime"))
            pStack.getOrCreateTag().putInt("burnTime", ClassiCraftConfiguration.lanternBurnoutTime.get());
        if(!pStack.getOrCreateTag().contains("lit_state"))
            pStack.getOrCreateTag().putInt("lit_state",1);
        BlockState state = getSuperPlacementState(pContext, block);
        if(state != null)
        {
            return state.setValue(LightAPI.LITSTATE, pStack.getOrCreateTag().getInt("lit_state")).setValue(RealisticLanternBlock.LANTERN_BURNTIME,pContext.getItemInHand().getOrCreateTag().getInt("burnTime")).setValue(RealisticLanternBlock.OIL,pContext.getItemInHand().getOrCreateTag().getInt("oil"));
        }
        return null;
    }

    default void inventoryLanternTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected, Item unlit) {
        if(!HARDCORE || pLevel.isClientSide() || !(pEntity instanceof Player player)) return;
        if(WATER_BURNT && pEntity.isInWater())
        {
            changeLantern(player,pStack, ItemStackAPI.replaceItemWithCopyNBTTagAndCount(pStack, unlit),pSlotId);
            pLevel.playSound(null,player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,0.3f, pLevel.random.nextFloat() * 0.1F + 0.6F);
        }
    }

   default BlockState getSuperPlacementState(BlockPlaceContext pContext, Block block) {
       BlockState blockstate = block.getStateForPlacement(pContext);
       return blockstate != null && canBePlace(pContext, blockstate) ? blockstate : null;
    }

    default boolean canBePlace(BlockPlaceContext pContext, BlockState pState) {
        Player player = pContext.getPlayer();
        CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return (!this.mustBeSurvive() || pState.canSurvive(pContext.getLevel(), pContext.getClickedPos())) && pContext.getLevel().isUnobstructed(pState, pContext.getClickedPos(), collisioncontext);
    }

    default boolean shouldLanternCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if(oldStack.getOrCreateTag().contains("burnTime") && newStack.getOrCreateTag().contains("burnTime"))
        {
            return oldStack.getOrCreateTag().getInt("burnTime") != oldStack.getOrCreateTag().getInt("burnTime");
        }
        return false;
    }

    default void changeLantern(Player player,ItemStack stack,ItemStack newStack,int slot)
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

    default boolean mustBeSurvive() {
        return true;
    }
}