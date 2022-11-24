package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * 用于物品相关的模组json 和 model 生成
 */
public class CCItemModelProvider  extends ItemModelProvider {

    public CCItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ClassiCraftMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleTexture(ModItems.ROTTEN_FOOD);
        simpleTexture(ModItems.COOKED_EGG);
        simpleTexture(ModItems.NETHER_MUSHROOM_STEW);
        simpleTexture(ModItems.SALT);
        simpleTexture(ModItems.DOUGH);
        simpleTexture(ModItems.RICE);
        simpleTexture(ModItems.RICE_HUSK);
        simpleTexture(ModItems.RICE_SEED);
        simpleTexture(ModItems.FLOUR);
        simpleTexture(ModItems.SALT_WATER_BOTTLE);
        simpleTexture(ModItems.RAW_PUMPKIN_PIE);
        simpleTexture(ModItems.RAW_COOKIE);
        simpleTexture(ModItems.RAW_CAKE);
        simpleTexture(ModItems.RAW_SALT);
        simpleTexture(ModItems.PERCH);
        simpleTexture(ModItems.LIONFISH);
        simpleTexture(ModItems.COOKED_PERCH);
        simpleTexture(ModItems.COOKED_LIONFISH);
        simpleTexture(ModItems.LIT_TORCH);
        simpleTexture(ModItems.LIT_SOUL_TORCH);
        simpleTexture(ModItems.ANGLEFISH);
        simpleTexture(ModItems.ARAPAIMA);
        simpleTexture(ModItems.LIT_SOUL_TORCH);
        simpleTexture(ModItems.TROUT);
        simpleTexture(ModItems.COOKED_TROUT);
        simpleTexture(ModItems.PUMPKIN_SLICE);
        simpleTexture(ModItems.SONTE_AXE_HEAD);
        simpleTexture(ModItems.SONTE_HOE_HEAD);
        simpleTexture(ModItems.SONTE_JAVELIN_HEAD);
        simpleTexture(ModItems.SONTE_KNIFE_HEAD);
        simpleTexture(ModItems.SONTE_SHOVEL_HEAD);
        simpleTexture(ModItems.SONTE_PICKAXE_HEAD);
        simpleTexture(ModItems.STONE_SWORD_BLADE);
        simpleTexture(ModItems.STONE_JAVELIN);
        simpleTexture(ModItems.PLANT_FIBER);
        block(ModBlocks.CACTUS_BALL);
        block(ModBlocks.SALT_BLOCK);
        block(ModBlocks.SALT_ROCK_BLOCK);
        block(ModBlocks.FRIDGE);
        block(ModBlocks.GLISTERING_MELON);
        block(ModBlocks.ALGAE);
        block(ModBlocks.CHARCOAL_BLOCK);
        block(ModBlocks.QUICKSAND);
    }

    void block(RegistryObject<? extends Block> block) {
        withExistingParent(block.getId().getPath(), blockName(block.getId()));
    }

    ResourceLocation blockName(ResourceLocation block) {
        return new ResourceLocation(block.getNamespace(), "block/" + block.getPath());
    }

    void base() {
        ForgeRegistries.ITEMS.forEach(item -> {
            if (item.getDescriptionId().equals(modid)) {
                if (item instanceof BlockItem) {
                    withExistingParent(item.getDescriptionId(), modLoc("block/" + item.getDescriptionId()));
                }
                else {
                    simpleTexture(() -> item);
                }
            }
        });
    }

    final ResourceLocation POWDER = modLoc("item/powder");

    void powder(Supplier<Item> item) {
        singleTexture(item.get().getDescriptionId(), mcLoc("item/generated"), "layer0", POWDER);
    }

    final ResourceLocation GEAR = modLoc("item/gear");

    void gear(Supplier<Item> item) {
        singleTexture(item.get().getDescriptionId(), mcLoc("item/generated"), "layer0", GEAR);
    }

    void simpleTexture(Supplier<Item> itemSupplier) {
        String name = itemSupplier.get().toString();

        try {
            ResourceLocation texture = modLoc("item/" + name);

            if (existingFileHelper.exists(texture, ModelProvider.TEXTURE)) {
                singleTexture(name, mcLoc("item/generated"), "layer0", texture);
            }
        }
        catch (Exception ignored) {}
    }
}