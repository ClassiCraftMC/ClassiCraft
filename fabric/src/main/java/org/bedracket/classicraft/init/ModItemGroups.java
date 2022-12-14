package org.bedracket.classicraft.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.bedracket.classicraft.ClassiCraftMod;

public class ModItemGroups {

    public static final CreativeModeTab COMMON_TAB = FabricItemGroup.builder(new ResourceLocation
            (ClassiCraftMod.MOD_ID, "common"))
            .icon(() -> new ItemStack(ModItems.CLASSIC_CRAFT.get()))
            .displayItems((features, output, hasPermissions) -> {
                output.accept(ModItems.TROUT.get());
                output.accept(ModItems.COOKED_TROUT.get());
                output.accept(ModItems.TALLOW.get());
                output.accept(ModItems.COOKED_EGG.get());
                output.accept(ModItems.NETHER_MUSHROOM_STEW.get());
                output.accept(ModItems.ROTTEN_FOOD.get());
                output.accept(ModItems.SULFUR.get());
            })
            .build();
}
