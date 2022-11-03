package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.item.ModItems;
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
        super(generator, ClassiCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleTexture(ModItems.ROTTEN_FOOD);
        simpleTexture(ModItems.COOKED_EGG);
        simpleTexture(ModItems.NETHER_MUSHROOM_STEW);
        simpleTexture(ModItems.CACTUS_FRUIT);
        simpleTexture(ModItems.SALT);
        block(ModBlocks.FRIDGE);
        block(ModBlocks.GLISTERING_MELON);
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