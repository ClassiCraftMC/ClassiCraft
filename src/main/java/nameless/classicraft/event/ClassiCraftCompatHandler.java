package nameless.classicraft.event;

import nameless.classicraft.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import toughasnails.api.potion.TANEffects;

public class ClassiCraftCompatHandler {

    public static void initCompats() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ClassiCraftCompatHandler::addEffect);
    }

    public static void addEffect(LivingEntityUseItemEvent event) {
        Entity entity = event.getEntity();
        ItemStack itemStack = event.getItem();
        if (entity instanceof Player) {
            if (ModList.get().isLoaded("toughasnails")) {
                if (itemStack.is(ModItems.SALT.get()) || itemStack.is(ModItems.RAW_SALT.get())) {
                    ((Player) entity).eat(entity.getLevel(), itemStack);
                    ((Player) entity).addEffect(
                            new MobEffectInstance(TANEffects.THIRST.get(), 100, 1), entity);
                }
                if (itemStack.is(ModItems.SALT_WATER_BOTTLE.get())) {
                    ((Player) entity).addEffect(
                            new MobEffectInstance(TANEffects.THIRST.get(), 300, 2), entity);
                }
            }
        }
    }
}
