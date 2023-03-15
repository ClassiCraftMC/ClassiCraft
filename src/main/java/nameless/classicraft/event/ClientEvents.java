/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ClassiCraftMod.MOD_ID)
public class ClientEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onWidgetAdded(ScreenEvent.Init.Post event) {
        var screen = event.getScreen();
        int x_offset = (screen.height - 176) / 2;
        if (event.getScreen() instanceof net.minecraft.client.gui.screens.inventory.InventoryScreen) {
            disableBook(event, x_offset + 104, (screen.height / 2 - 22));
        } else if (event.getScreen() instanceof net.minecraft.client.gui.screens.inventory.CraftingScreen) {
            disableBook(event, x_offset + 5, (screen.height / 2 - 49));
        } else if (event.getScreen() instanceof net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen) {
            disableBook(event, x_offset + 20, (screen.height / 2 - 49));
        }
    }

    private static void disableBook(ScreenEvent.Init.Post event, int x, int y) {
        var screen = event.getScreen();
        List<Renderable> renderableList = screen.renderables;
        for (Renderable renderable : renderableList) {
            if (renderable instanceof final ImageButton imageButton) {
                if (imageButton.resourceLocation.equals(new ResourceLocation("textures/gui/recipe_button.png"))) {
                    imageButton.visible = false;
                    return;
                }
                if (imageButton.getX() == x && imageButton.getY() == y) {
                    imageButton.visible = false;
                    return;
                }
            }
        }
    }
}
