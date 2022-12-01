package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
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
        add(ModItems.DEBUG_TIME_STICK.get(), "Debug Burn Time Stick");
        add(ModItems.DEPTH_METER.get(), "Depth Meter");
        add(ModBlocks.QUICKSAND.get(), "Quicksand");
        add(ModBlocks.RED_QUICKSAND.get(), "Red Quicksand");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "Charcoal Block");
        add(ModBlocks.CACTUS_BALL.get(), "Cactus ball");
        add(ModBlocks.ROSE.get(), "Rose");
        add(ModEntities.TROUT_ENTITY.get(), "Trout");
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "Ocean Shark");
        add(ModItems.TROUT_SPAWN_EGG.get(), "Trout Spawn Egg");
        add(ModItems.TROUT.get(), "Trout");
        add(ModItems.COOKED_TROUT.get(), "Cooked Trout");
        add(ModItems.TALLOW.get(), "Tallow");
        add(ModBlocks.TALLOW_BLOCK.get(), "Tallow Block");
        add(ModItems.TROUT_BUCKET.get(), "Trout Bucket");
        add("info.classicraft.burntime", "BurnTime");
        add("info.classicraft.minutes", "minutes");
        add("info.classicraft.depth.sky_land", "'s sky land");
        add("info.classicraft.depth.sky", "'s sky");
        add("info.classicraft.depth.cloud", "'s cloud");
        add("info.classicraft.depth.base_cloud", "'s base cloud");
        add("info.classicraft.depth.sea_level", "'s sea level");
        add("info.classicraft.depth.surface", "'s surface");
        add("info.classicraft.depth.underground", "'s sea level");
        add("info.classicraft.depth.deep_underground", "'s deep underground");
        add("info.classicraft.depth.bedrock", "'s bedrock");
        add("info.classicraft.depth.void", "'s void");
        add("info.classicraft.height", "At now is in the height ");
    }
}
