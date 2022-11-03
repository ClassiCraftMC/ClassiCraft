package nameless.classicraft.common.menu;

import nameless.classicraft.ClassiCraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ClassiCraft.MODID);

    public static final RegistryObject<MenuType<FridgeMenu>> FRIDGE = MENUS_TYPES.register("fridge", () -> from(FridgeMenu::new));

    private interface KKBeMenuCreator<M extends AbstractContainerMenu, T extends BlockEntity> {
        M create(int windowId, Inventory inv, T blockEntity);
    }

    @SuppressWarnings("unchecked")
    private static <M extends AbstractContainerMenu, T extends BlockEntity> MenuType<M> from(KKBeMenuCreator<M, T> creator) {
        return IForgeMenuType.create((id, inv, data) ->
                creator.create(id, inv, (T) inv.player.getLevel().getBlockEntity(data.readBlockPos())));
    }
}
