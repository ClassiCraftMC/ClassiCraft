package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.item.LitTorchItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Item> TORCH_UNLIT =
            register("torch_unlit", () ->
            new StandingAndWallBlockItem(
                    ModBlocks.REAL_TORCH.get(),
                    ModBlocks.REAL_WALL_TORCH.get(),
                    new Item.Properties().stacksTo(64).tab(ModCreativeModeTabs.COMMON)));

    public static final RegistryObject<Item> SOUL_TORCH_UNLIT =
            register("soul_torch_unlit", () ->
                    new StandingAndWallBlockItem(
                            ModBlocks.REAL_SOUL_TORCH.get(),
                            ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                            new Item.Properties().stacksTo(64).tab(ModCreativeModeTabs.COMMON)));

    public static final RegistryObject<Item> TORCH_LIT =
            register("torch_lit", LitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_LIT =
            register("soul_torch_lit", LitTorchItem::new);

    public static final RegistryObject<Item> CLASSIC_CRAFT =
            register("classic_craft", () -> new Item(new Item.Properties()));

    /**
     * Used for registry items
     * @param name Items' registry name
     * @param item Item Instance
     * @return new RegistryObject<Item>
     * @param <T> sth extends Item
     */
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
