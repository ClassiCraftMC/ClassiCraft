package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.item.*;
import net.minecraft.world.item.Item;
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
            register("torch_unlit", UnlitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_UNLIT =
            register("soul_torch_unlit", UnlitSoulTorchItem::new);

    public static final RegistryObject<Item> TORCH_LIT =
            register("torch_lit", LitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_LIT =
            register("soul_torch_lit", LitSoulTorchItem::new);

    public static final RegistryObject<Item> CLASSIC_CRAFT =
            register("classic_craft", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBUG_TIME_STICK =
            register("debug_time_stick", DebugBugTimeStick::new);

    public static final RegistryObject<Item> DEPTH_METER =
            register("depth_meter", DepthMeterItem::new);

    public static final RegistryObject<Item> BIOME_COMPASS =
            register("biome_compass", BiomeCompass::new);

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
