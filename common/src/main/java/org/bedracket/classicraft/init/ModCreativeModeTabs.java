package org.bedracket.classicraft.init;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.bedracket.classicraft.ClassiCraftMod;

public class ModCreativeModeTabs {

    public static final CreativeTabRegistry.TabSupplier COMMON = CreativeTabRegistry.create(
            new ResourceLocation(ClassiCraftMod.MOD_ID, "common"),
            () -> new ItemStack(ModItems.CLASSIC_CRAFT.get())
    );
}
