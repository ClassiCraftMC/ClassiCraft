package nameless.classicraft.item;

import nameless.classicraft.ClassicCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * 注册ClassicCraft模组所有物品的类.
 * 包括该模组物品的所有列表
 * register 方法应该随时返回一个新的实例
 * 使用例子:
 * public static final RegistryObject<Item> EXAMPLE_ITEM = register("example_item");
 */
public class CCItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClassicCraft.MODID);

    public static final RegistryObject<Item> SALT = register("salt", SaltItem::new);

    /**
     * 用于注册物品
     * @param name 物品的注册名
     * @return 返回新的注册实例
     */
    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new Item(new Item.Properties().tab(ClassicCraftTab.TAB)));
    }

    /**
     * 用于处理注册物品
     * @param name 物品的注册名
     * @param item 物品实例
     * @return 返回到注册为一个新的物品实例
     * @param <T> 需要设置RegistryObject类型
     */
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

}
