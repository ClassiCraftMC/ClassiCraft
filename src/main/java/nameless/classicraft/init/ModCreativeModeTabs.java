package nameless.classicraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeModeTabs {

    public static final CreativeModeTab COMMON = new CreativeModeTab("classicraft") {

        @Override
        @NotNull
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CLASSIC_CRAFT.get());
        }
    };
}
