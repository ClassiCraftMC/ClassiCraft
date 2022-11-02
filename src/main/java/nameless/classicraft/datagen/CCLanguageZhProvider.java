package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 用于自动生成中文语言文件
 */
public class CCLanguageZhProvider extends LanguageProvider {

    public CCLanguageZhProvider(DataGenerator gen, String locale) {
        super(gen, ClassiCraft.MODID, locale);
    }

    /**
     * 用于添加翻译
     * 使用例子：
     * add(实例, "翻译名");
     */
    @Override
    protected void addTranslations() {
        add("itemGroup.classicraft.tab", "天工开物");
    }
}
