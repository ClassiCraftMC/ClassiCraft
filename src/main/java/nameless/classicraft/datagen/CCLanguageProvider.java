package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 用于自动生成英语语言文件
 */
public class CCLanguageProvider extends LanguageProvider {

    public CCLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ClassiCraft.MODID, locale);
    }

    /**
     * 用于添加翻译
     * 使用例子：
     * add(实例, "翻译名");
     */
    @Override
    protected void addTranslations() {
        add("itemGroup.classiccraft.tab", "ClassicCraft Tab");
        add(ModItems.SALT.get(), "Salt");
    }

}

