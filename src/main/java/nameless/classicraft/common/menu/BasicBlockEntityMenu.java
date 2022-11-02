package nameless.classicraft.common.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * @author DustW
 **/
public class BasicBlockEntityMenu<T extends BlockEntity> extends BasicMenu {
    public final T blockEntity;

    public BasicBlockEntityMenu(MenuType<?> type, int windowId, Inventory inv, T blockEntity) {
        super(type, windowId, inv);
        this.blockEntity = blockEntity;
    }
}
