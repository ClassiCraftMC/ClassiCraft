package nameless.classicraft.item;

import nameless.classicraft.ClassiCraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * ClassicCraft物品栏类
 * 直接静态引用使用
 * 使用例子:
 * ClassicCraftTab.TAB
 */
public class ClassiCraftTab extends CreativeModeTab {

    public static final ClassiCraftTab TAB = new ClassiCraftTab();

    public ClassiCraftTab() {
        super(ClassiCraft.MODID + ".tab");
    }

    @Override
    public ItemStack makeIcon() {
        return Items.CACTUS.getDefaultInstance();
    }

}
