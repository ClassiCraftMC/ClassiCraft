package nameless.classicraft.item;

import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.block.RealTorchBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModConfigs;
import nameless.classicraft.init.ModCreativeModeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
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

    private static final boolean HARDCORE = ModConfigs.hardcore.get();
    private static final Boolean WATER_BURNT = ModConfigs.waterBurnt.get();

    public LitSoulTorchItem() {
        super(ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                new Properties().stacksTo(64)
                        .tab(ModCreativeModeTabs.COMMON));
    }

    @Override
    public String getDescriptionId() {
        return "item.classicraft.soul_torch_lit";
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
            burnTime = RealTorchBlock.getInitialBurnTime();
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
                return state.setValue(AbstractLightBlock.getLitState(), false).setValue(RealTorchBlock.getBurnTime(), RealTorchBlock.getInitialBurnTime());
            } else {
                return state.setValue(AbstractLightBlock.getLitState(), true).setValue(RealTorchBlock.getBurnTime(), RealTorchBlock.getInitialBurnTime());
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
