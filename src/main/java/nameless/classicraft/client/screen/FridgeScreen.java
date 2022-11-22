package nameless.classicraft.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.screen.widget.ProgressWidget;
import nameless.classicraft.client.menu.FridgeMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 */
public class FridgeScreen extends AbstractScreen<FridgeMenu> {
    static final ResourceLocation TEXTURE = new ResourceLocation(ClassiCraftMod.MODID, "textures/gui/icebox_gui.png");

    ProgressWidget progress = new ProgressWidget(
            this, TEXTURE,
            4, 14, 3, 182,
            170, 4, false,
            () -> 1000F,
            () -> 1000 - menu.blockEntity.energy.get()
    );

    public FridgeScreen(FridgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
        imageHeight = 180;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        inventoryLabelY = topPos + 57;
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        progress.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
}
