package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;


public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen) {
        super(gen, ClassiCraftMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "Lit Torch");
        add(ModItems.TORCH_UNLIT.get(), "Unlit Torch");
        add(ModBlocks.REAL_TORCH.get(), "Real Torch");
        add("info.classicraft.burntime", "BurnTime");
        add("info.classicraft.minutes", "Minutes");
        add("info.classicraft.depth", "Depth");
    }
}
