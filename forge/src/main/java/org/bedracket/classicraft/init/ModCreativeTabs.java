package org.bedracket.classicraft.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bedracket.classicraft.ClassiCraftMod;

public class ModCreativeTabs {

    public static CreativeModeTab COMMON_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        COMMON_TAB = event.registerCreativeModeTab(new ResourceLocation(ClassiCraftMod.MOD_ID,
                "common"), builder -> {
            builder.icon(() -> new ItemStack(ModItems.CLASSIC_CRAFT.get()))
                    .displayItems((features, output, hasPermissions) -> {
                        output.accept(ModItems.TROUT.get());
                        output.accept(ModItems.COOKED_TROUT.get());
                        output.accept(ModItems.TALLOW.get());
                        output.accept(ModItems.COOKED_EGG.get());
                        output.accept(ModItems.NETHER_MUSHROOM_STEW.get());
                        output.accept(ModItems.ROTTEN_FOOD.get());
                        output.accept(ModItems.SULFUR.get());
                    })
                    .title(Component.translatable("itemGroup.classicraft.common"))
                    .build();
        });
    }
}
