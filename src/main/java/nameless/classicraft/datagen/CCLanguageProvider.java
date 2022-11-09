package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 用于自动生成英语语言文件
 */
public class CCLanguageProvider extends LanguageProvider {

    public CCLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ClassiCraftMod.MODID, locale);
    }

    /**
     * 用于添加翻译
     * 使用例子：
     * add(实例, "翻译名");
     */
    @Override
    protected void addTranslations() {
        add("itemGroup.classicraft.common", "ClassicCraft Common Tab");
        add("itemGroup.classicraft.decoration", "ClassicCraft Decoration Tab");
        add("level.classicraft.fresh", "Fresh ");
        add("level.classicraft.stale", "Stale ");
        add("level.classicraft.spoiled", "Spoiled ");
        add("level.classicraft.rotten", "Rotten ");
        add("info.classicraft.rot", "It will rotten after %s day");
        add("info.classicraft.rotting_speed", "Rotting Speed: %s");
        add("container.classicraft.fridge", "Fridge");
        add("container.classicraft.stone_mortar_block", "Stone Mortar Block");
        add(ModBlocks.FRIDGE.get(), "Fridge");
        add(ModBlocks.GLISTERING_MELON.get(), "Glistering Melon");
        add(ModBlocks.CACTUS_FRUIT.get(), "Cactus Fruit");
        add(ModBlocks.MUSHROOM_PLANTER.get(), "Mushroom Planter");
        add(ModItems.ROTTEN_FOOD.get(), "Rotten Food");
        add(ModItems.COOKED_EGG.get(), "Cooked Egg");
        add(ModItems.SALT.get(), "Salt");
        add(ModItems.DOUGH.get(), "Dough");
        add(ModItems.SALT_WATER_BOTTLE.get(), "Salt Water Bottle");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "Nether Mushroom Stew");
        add(ModItems.RICE.get(), "Rice");
        add(ModItems.RICE_HUSK.get(), "Rice Husk");
        add(ModItems.RICE_SEED.get(), "Rice Seed");
        add(ModItems.DEER_SPAWN_EGG.get(), "Deer Spawn Egg");
        add(ModItems.BOAR_SPAWN_EGG.get(), "Boar Spawn Egg");
        add(ModItems.FLOUR.get(), "Flour");
        add(ModBlocks.SALT_ORE.get(), "Salt Ore");
        add(ModBlocks.WILD_RICE.get(), "Wild Rice");
        add(ModBlocks.STONE_MORTAR_BLOCK.get(), "Stone Motar");
        add(ModBlocks.LARGE_FIRE_BOWL.get(), "Large Fire Bowl");
        add(ModBlocks.LARGE_SOUL_FIRE_BOWL.get(), "Large Soul Fire Bowl");
        add(ModBlocks.TORCH.get(), "Torch");
        add(ModBlocks.SOUL_TORCH.get(), "Soul Torch");
        add(ModBlocks.SALT_ROCK_BLOCK.get(), "Salt Rock");
        add(ModBlocks.SALT_BLOCK.get(), "Salt Block");
        add(ModBlocks.LANTERN.get(), "Lantern");
        add(ModBlocks.SOUL_LANTERN.get(), "Soul Lantern");
        add(ModItems.RAW_PUMPKIN_PIE.get(), "Raw Pumpkin Pie");
        add(ModItems.RAW_COOKIE.get(), "Raw Cookie");
        add(ModItems.RAW_CAKE.get(), "Raw Cake");
        add(ModBlocks.SALT_STALACTITE.get(), "Salt Stalactite");
        add(ModBlocks.LARGE_SALT_BUD.get(), "Large Salt Bud");
        add(ModBlocks.MEDIUM_SALT_BUD.get(), "Medium Salt Bud");
        add(ModBlocks.SMALL_SALT_BUD.get(), "Small Salt Bud");
        add("info.classicraft.stop_use_lantern", "Could't place the Vanilla Lantern Block in survival mode, you can configured it in ClassisCraft Mod Config.");
        add("info.classicraft.stop_use_torch", "Could't place the Vanilla Torch Block in survival mode, you can configured it in ClassisCraft Mod Config.");
    }

}

