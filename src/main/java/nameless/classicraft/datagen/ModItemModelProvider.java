package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModItemModelProvider extends ItemModelProvider {
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");
    protected final DeferredRegister<? extends Item> deferredRegister;
    protected Set<Item> skipItems = new HashSet<>();

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper, DeferredRegister<? extends Item> deferredRegister) {
        super(generator, ClassiCraftMod.MOD_ID, existingFileHelper);
        this.deferredRegister = deferredRegister;
    }

    private static String name(Item item) {
        ResourceLocation identifier = ForgeRegistries.ITEMS.getKey(item);
        if (identifier == null) {
            return null;
        }else {
            return identifier.getPath();
        }
    }

    @Override
    protected void registerModels() {
        Set<Item> items = getItems();
        addSkipItems();
        items.removeAll(skipItems);
        registerItemBlock(items.stream()
                .filter(item -> item instanceof BlockItem)
                .map(item -> (BlockItem) item)
                .collect(Collectors.toSet()));

        addSkipItems();
        items.removeAll(skipItems);
        registerItem(items);
    }

    protected void skipItems(Item... items) {
        Collections.addAll(skipItems, items);
    }

    protected void skipItems(Collection<? extends Item> items) {
        skipItems.addAll(items);
    }

    protected Set<Item> getItems() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected void registerItemBlock(@NotNull Set<BlockItem> blockItems) {
        blockItems.forEach(blockItem -> withExistingParent(name(blockItem),
                modLoc("block/" + name(blockItem))));
        skipItems(blockItems);
    }

    protected void registerItem(Set<Item> items) {
        items.forEach(this::generatedItem);
        items.stream()
                .filter(item -> item instanceof TieredItem)
                .forEach(this::handheldItem);
    }

    protected final ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));
    }

    protected final ItemModelBuilder generatedItem(Item item) {
        return generatedItem(name(item));
    }

    protected final ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));
    }

    protected final ItemModelBuilder handheldItem(Item item) {
        return handheldItem(name(item));
    }

    protected void addSkipItems()  {
        skipItems(
                ModItems.TORCH_LIT.get(),
                ModItems.TORCH_UNLIT.get(),
                ModItems.SOUL_TORCH_LIT.get(),
                ModItems.SOUL_TORCH_UNLIT.get(),
                ModItems.DEPTH_METER.get(),
                ModItems.DEBUG_TIME_STICK.get(),
                ModItems.BIOME_COMPASS.get(),
                ModItems.TROUT_SPAWN_EGG.get(),
                ModItems.TROUT_BUCKET.get(),
                ModItems.OCEAN_SHARK_SPAWN_EGG.get(),
                ModBlocks.ROSE.get().asItem(),
                ModBlocks.CACTUS_BALL.get().asItem(),
                ModBlocks.MOSSY_BRICKS_WALL.get().asItem(),
                ModBlocks.CRACKED_BRICKS_WALL.get().asItem()
        );
    }
}