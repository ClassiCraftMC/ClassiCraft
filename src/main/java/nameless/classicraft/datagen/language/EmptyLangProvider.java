package nameless.classicraft.datagen.language;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * @author DustW
 */
public class EmptyLangProvider extends LanguageProvider {
    public EmptyLangProvider(PackOutput output, String modid) {
        super(output, modid, "empty");
    }

    @Override
    protected void addTranslations() {
        // metas();
    }

    void metas() {
        for (MetaItem metaItem : MetaItem.getMetaItems()) {
            metaItem.getMetaItemStacks().forEach(s -> add(s.getDescriptionId(), " "));
        }
    }
}
