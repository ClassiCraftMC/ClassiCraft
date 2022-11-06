package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * ClassicCraft物品栏类
 * 直接静态引用使用
 * 使用例子:
 * ClassicCraftTab.TAB
 */
public class ClassiCraftTab {

    public static final CreativeModeTab COMMON = new CreativeModeTab(ClassiCraftMod.MODID + ".common") {
        @NotNull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CLASSIC_CRAFT.get());
        }
    };

    public static final CreativeModeTab DECORATION = new CreativeModeTab(ClassiCraftMod.MODID + ".decoration") {
        @NotNull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.UNLIT_FIRE_BOWL.get());
        }
    };
}
