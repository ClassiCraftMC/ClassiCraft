package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(DataGenerator gen) {
        super(gen, ClassiCraftMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "点燃的火把");
        add(ModItems.TORCH_UNLIT.get(), "熄灭的火把");
        add(ModBlocks.REAL_TORCH.get(), "火把");
        add("info.classicraft.burntime", "燃烧时间");
        add("info.classicraft.minutes", "分钟");
        add("info.classicraft.depth.sky_land", "当前所在天域高度: ");
        add("info.classicraft.depth.sky", "当前所在高空高度: ");
        add("info.classicraft.depth.cloud", "当前所在云层高度: ");
        add("info.classicraft.depth.base_cloud", "当前所在低空高度: ");
        add("info.classicraft.depth.sea_level", "当前所在海平面高度: ");
        add("info.classicraft.depth.surface", "当前所在地表高度: ");
        add("info.classicraft.depth.underground", "当前所在地底深度: ");
        add("info.classicraft.depth.deep_underground", "当前所在深层地底深度: ");
        add("info.classicraft.depth.bedrock", "当前所在基岩层深度: ");
        add("info.classicraft.depth.void.sky", "当前所在虚空高度: ");
        add("info.classicraft.depth.void.underground", "当前所在虚空深度: ");
    }
}
