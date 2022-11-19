package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.util.EventUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemEntityEvents {

    @SubscribeEvent
    public static void onLanternTicking(ItemEntityTickEvent event) {
        EventUtils.tickingBlockItemNeedFuel(event, ModItems.LIT_LANTERN.get(), ModItems.LANTERN.get());
        EventUtils.tickingBlockItemNeedFuel(event, ModItems.LIT_SOUL_LANTERN.get(), ModItems.SOUL_LANTERN.get());

    }

    @SubscribeEvent
    public static void onLanternInWater(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        if (itemEntity.getItem().is(ModItems.LIT_LANTERN.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    ModItems.LANTERN.get().getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
        if (itemEntity.getItem().is(ModItems.LIT_SOUL_LANTERN.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    ModItems.SOUL_LANTERN.get().getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
    }

    @SubscribeEvent
    public static void onItemTicking(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getItem();
        if (ClassiCraftConfiguration.enableEntityTorchBurnOut.get()) {
            if (itemStack.is(ModItems.LIT_TORCH.get())
                    && itemEntity.getAge()
                    == ClassiCraftConfiguration.torchEntityBurnOutTime.get() * 1200) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        Items.STICK.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                itemEntity.getLevel().addFreshEntity(newItem);
            }
            if (itemStack.is(ModItems.LIT_SOUL_TORCH.get())
                    && itemEntity.getAge()
                    == ClassiCraftConfiguration.torchEntityBurnOutTime.get() * 1200) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        Items.STICK.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                itemEntity.getLevel().addFreshEntity(newItem);
            }
        }
    }

    @SubscribeEvent
    public static void onItemInRaining(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        Level level = itemEntity.getLevel();
        if (ClassiCraftConfiguration.enableEntityTorchBurnOut.get()) {
            if (itemEntity.getItem().is(ModItems.LIT_TORCH.get())
                    && level.isRainingAt(itemEntity.getOnPos().above(2))) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(),
                        itemEntity.getY(),
                        itemEntity.getZ(),
                        Items.STICK.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                itemEntity.getLevel().addFreshEntity(newItem);
            }
        }
    }

    @SubscribeEvent
    public static void onTorchInWater(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        if (itemEntity.getItem().is(ModItems.LIT_TORCH.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
        if (itemEntity.getItem().is(ModItems.LIT_SOUL_TORCH.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
    }

}
