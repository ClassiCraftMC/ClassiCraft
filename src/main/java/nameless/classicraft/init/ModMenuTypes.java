package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.menu.FridgeMenu;
import nameless.classicraft.client.menu.StoneMortarBlockMenu;
import nameless.classicraft.client.menu.WoodcutterMenu;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ClassiCraftMod.MODID);

    public static final RegistryObject<MenuType<FridgeMenu>> FRIDGE =
            MENUS_TYPES.register("fridge", () -> from(FridgeMenu::new));

    public static final RegistryObject<MenuType<StoneMortarBlockMenu>> STONE_MORTAR_BLOCK_CONTAINER =
           registerMenuType("stone_mortar_block_container", StoneMortarBlockMenu::new);

    public static final RegistryObject<MenuType<WoodcutterMenu>> WOODCUTTER_CONTAINER =
            registerMenuType(WoodcutterMenu::new, "woodcutter_container");

    private interface KKBeMenuCreator<M extends AbstractContainerMenu, T extends BlockEntity> {
        M create(int windowId, Inventory inv, T blockEntity);
    }

    @SuppressWarnings("unchecked")
    private static <M extends AbstractContainerMenu, T extends BlockEntity> MenuType<M> from(KKBeMenuCreator<M, T> creator) {
        return IForgeMenuType.create((id, inv, data) ->
                creator.create(id, inv, (T) inv.player.getLevel().getBlockEntity(data.readBlockPos())));
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(MenuType.MenuSupplier<T> pFactory, String name) {
        return MENUS_TYPES.register(name, () -> new MenuType<>(pFactory));
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name,
                                                                                                  IContainerFactory<T> factory) {
        return MENUS_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }
}
