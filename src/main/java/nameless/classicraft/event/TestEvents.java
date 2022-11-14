package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player != null) {
            ItemStack firstItem = player.getOffhandItem();
            ItemStack itemStack = event.getItemStack();
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            if (itemStack.is(Items.FLINT_AND_STEEL)
                    && firstItem.is(ModItems.TORCH.get())
                    && firstItem.getCount() == 1) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                EquipmentSlot pSlot = EquipmentSlot.OFFHAND;
                player.setItemSlot(pSlot, newItem);
            }
            if (itemStack.is(ModItems.TORCH.get())
                    && firstItem.is(Items.FLINT_AND_STEEL)
                    && itemStack.getCount() == 1) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                EquipmentSlot pSlot = EquipmentSlot.MAINHAND;
                player.setItemSlot(pSlot, newItem);
            }
        }
    }

}
