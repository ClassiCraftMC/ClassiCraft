package nameless.classicraft.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab COMMON =
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 6)
                    .title(Component.translatable("itemGroup.classicraft")).icon(() -> {
                return new ItemStack(ModItems.CLASSIC_CRAFT.get());
            }).build();
}
