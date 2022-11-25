package nameless.classicraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTabs {

    public static final CreativeModeTab COMMON = new CreativeModeTab("classicraft") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.TORCH);
        }
    };
}
