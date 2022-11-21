package nameless.classicraft.client.menu;

import nameless.classicraft.block.entity.FridgeBlockEntity;
import nameless.classicraft.init.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 */
public class FridgeMenu extends BasicBlockEntityMenu<FridgeBlockEntity> {
    public FridgeMenu(int windowId, Inventory inv, FridgeBlockEntity blockEntity) {
        super(ModMenuTypes.FRIDGE.get(), windowId, inv, blockEntity);

        layoutPlayerInventorySlots(8, 99);
        addSlotBox(blockEntity.getHandler(), 0, 8, 28, 9, 18, 3, 18);
    }
}
