package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    public static void initClient() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ClientEvents::onScreenLoad);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onScreenLoad(ScreenEvent.Init.Post e) {
        if (!(e.getScreen() instanceof PauseScreen pauseScreen) || !pauseScreen.showPauseMenu) return;

        if (ModList.get().isLoaded("bettergamemenu")) return;
        if (ClassiCraftConfiguration.removeSendFeedbackAndReportBugs.get()) {
            Button feedback = discoverButton(e.getListenersList(), "menu.sendFeedback");
            Button reportbugs = discoverButton(e.getListenersList(), "menu.reportBugs");

            if (feedback != null)
                e.removeListener(feedback);
            if (reportbugs != null)
                e.removeListener(reportbugs);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static Button discoverButton(List<GuiEventListener> listeners, String name) {
        for (GuiEventListener listener : listeners) {
            if (listener instanceof Button button && button.getMessage() instanceof MutableComponent mutableComponent
                    && mutableComponent.getContents() instanceof TranslatableContents translatableContents) {
                if (translatableContents.getKey().equals(name))
                    return button;
            }
        }
        return null;
    }
}
