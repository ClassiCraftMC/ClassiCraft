package nameless.classicraft.item;

import nameless.classicraft.ClassicCraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * ClassicCraft物品栏类
 * 直接静态引用使用
 * 使用例子:
 * ClassicCraftTab.TAB
 */
public class ClassicCraftTab extends CreativeModeTab {

    public static final ClassicCraftTab TAB = new ClassicCraftTab();

    public ClassicCraftTab() {
        super(ClassicCraft.MODID + ".tab");
    }

    @Override
    public ItemStack makeIcon() {
        return Items.CACTUS.getDefaultInstance();
    }

}
