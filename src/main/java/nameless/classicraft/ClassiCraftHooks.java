package nameless.classicraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.Locale;

public class ClassiCraftHooks {

    private static final String MODEL_DIR = "textures/model/";

    @SuppressWarnings("deprecation")
    public static void handleFood() {
        if (Items.DRIED_KELP.getFoodProperties() != null) {
            Items.DRIED_KELP.getFoodProperties().canAlwaysEat = true;
            Items.DRIED_KELP.getFoodProperties().nutrition = 0;
            Items.DRIED_KELP.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.CHORUS_FRUIT.getFoodProperties() != null) {
            Items.CHORUS_FRUIT.getFoodProperties().canAlwaysEat = true;
            Items.CHORUS_FRUIT.getFoodProperties().nutrition = 0;
            Items.CHORUS_FRUIT.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.ROTTEN_FLESH.getFoodProperties() != null) {
            Items.ROTTEN_FLESH.getFoodProperties().canAlwaysEat = true;
            Items.ROTTEN_FLESH.getFoodProperties().nutrition = 0;
            Items.ROTTEN_FLESH.getFoodProperties().saturationModifier = 0.0F;
        }
    }

    public static ResourceLocation getModelTexture(String name) {
        return new ResourceLocation(ClassiCraft.MODID, MODEL_DIR + name);
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(ClassiCraft.MODID, name.toLowerCase(Locale.ROOT));
    }
}
