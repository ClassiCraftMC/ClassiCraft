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
        add("info.classicraft.minutes", "minutes");
        add("info.classicraft.depth.sky_land", "It's in the height of sky land: ");
        add("info.classicraft.depth.sky", "It's in the height of sky: ");
        add("info.classicraft.depth.cloud", "It's in the height of cloud: ");
        add("info.classicraft.depth.base_cloud", "It's in the height of base cloud: ");
        add("info.classicraft.depth.sea_level", "It's in the height of sea level: ");
        add("info.classicraft.depth.surface", "It's in the height of surface: ");
        add("info.classicraft.depth.underground", "It's in the height of sea underground: ");
        add("info.classicraft.depth.deep_underground", "It's in the height of deep underground: ");
        add("info.classicraft.depth.bedrock", "It's in the height of bedrock: ");
        add("info.classicraft.depth.void.sky", "It's in the height of void: ");
        add("info.classicraft.depth.void.underground", "It's in the height of void: ");
    }
}
