package nameless.classicraft.datagen;

import nameless.classicraft.ClassicCraft;
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用于物品相关的模组json 和 model 生成
 */
public class CCItemModelProvider extends ItemModelProvider {

    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");
    protected final DeferredRegister<? extends Item> deferredRegister;
    protected Set<Item> skipItems = new HashSet<>();

    public CCItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper, DeferredRegister<? extends Item> deferredRegister) {
        super(generator, ClassicCraft.MODID, existingFileHelper);
        this.deferredRegister = deferredRegister;
    }

    /**
     * 获取物品注册名
     * @param item 物品实例
     * @return 返回物品注册名
     */
    private static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    @Override
    protected void registerModels() {
        Set<Item> items = getItems();

        items.removeAll(skipItems);
        setSkipItems();
        registerItemBlock(items.stream()
                .filter(item -> item instanceof BlockItem)
                .map(item -> (BlockItem) item)
                .collect(Collectors.toSet()));

        items.removeAll(skipItems);
        setSkipItems();
        registerItem(items);
    }

    /**
     * 用于跳过生成物品json
     * @param items 需要跳过生成的物品列表
     */
    protected void skipItems(Item... items) {
        Collections.addAll(skipItems, items);
    }

    /**
     * 用于增加需要跳过生成的物品列表
     * @param items 需要跳过生成的物品列表
     */
    protected void skipItems(Collection<? extends Item> items) {
        skipItems.addAll(items);
    }

    /**
     * 用于获取需要生成方块物品json的列表
     * @return 返回需要生成方块物品json的列表
     */
    protected Set<Item> getItems() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    /**
     * 注册生成方块物品json
     * @param blockItems 需要生成方块物品json的列表
     */
    protected void registerItemBlock(@NotNull Set<BlockItem> blockItems) {
        blockItems.forEach(blockItem -> withExistingParent(name(blockItem),
                modLoc("block/" + name(blockItem))));
        skipItems(blockItems);
    }

    /**
     * 注册生成物品json
     * @param items 需要生成物品json的列表
     */
    protected void registerItem(Set<Item> items) {
        items.forEach(this::generatedItem);
        items.stream()
                .filter(item -> item instanceof TieredItem)
                .forEach(this::handheldItem);
    }

    /**
     * 用于生成普通物品json
     * @param name 需要生成json对应的物品实例
     * @return 返回生成物品json的方法
     */
    protected final ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));
    }

    /**
     * 生成物品json
     * @param item 需要生成json对应的物品实例
     * @return 返回生成物品json的方法
     */
    protected final ItemModelBuilder generatedItem(Item item) {
        return generatedItem(name(item));
    }

    /**
     * 用于处理手持物品的json生成
     * @param name 物品的注册名
     * @return 返回生成物品json的方法
     */
    protected final ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));
    }

    /**
     * 用于处理手持物品的json生成
     * @param item 需要生成json对应的物品实例
     * @return 返回生成物品json的方法
     */
    protected final ItemModelBuilder handheldItem(Item item) {
        return handheldItem(name(item));
    }

    /**
     * 设置跳过生成的物品json
     * 使用例子：
     *  skipItems(
     *          ExampleItems.EXAMPLE_ITEM.get(),
     *         );
     * 会跳过生成该物品的json
     */
    protected void setSkipItems() {
        skipItems(
        );
    }
}
