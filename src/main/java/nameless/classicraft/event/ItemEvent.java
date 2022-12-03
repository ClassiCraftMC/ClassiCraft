package nameless.classicraft.event;

import nameless.classicraft.api.san.ISanHandler;
import nameless.classicraft.util.SanUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemEvent {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void fireBow(LivingEntityUseItemEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        if (entity instanceof Player) {
            if (SanUtils.checkSanExist()
                    && itemStack.is(Items.BOW)) {
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                    ((ISanHandler) entity).reduceSan(1.0F);
                }
            }
        }
    }
}
