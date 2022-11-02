package nameless.classicraft.block;

import nameless.classicraft.ClassicCraft;
import nameless.classicraft.item.CCItems;
import nameless.classicraft.item.ClassicCraftTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 注册ClassicCraft模组所有方块的类.
 * 包括该模组物品的所有列表
 * register 方法应该随时返回一个新的实例
 * 使用例子:
 * public static final RegistryObject<Block> EXAMPLE_Block = register("example_block");
 */
public class CCBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ClassicCraft.MODID);

    public static final RegistryObject<Block> SLAT_GRAVEL = register("salt_gravel", SaltGravelBlock::new);

    /**
     * 用于注册新的方块以及对应物品
     * @param name 注册名
     * @param blockSupplier 方块实例
     * @return 注册新的方块
     * @param <T> 继承自方块类，需要使用RegistryObject类
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(ClassicCraftTab.TAB)));
    }

    /**
     * 用于注册方块以及对应方块物品
     * @param blocks 方块实例列表
     * @param items 物品实例列表
     * @param name 方块注册名
     * @param blockSupplier 方块实例
     * @param blockItemFactory 物品实例
     * @return 注册新的方块
     * @param <T> 继承自方块类，需要使用RegistryObject类
     */
    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    /**
     * 用于注册方块以及对应的方块物品
     * @param name 方块注册名
     * @param blockSupplier 方块实例
     * @param blockItemFactory 添加到物品实例
     * @return 注册整体方块
     * @param <T> 继承自方块类，需要使用RegistryObject类
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return registerBlock(CCBlocks.BLOCKS, CCItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
