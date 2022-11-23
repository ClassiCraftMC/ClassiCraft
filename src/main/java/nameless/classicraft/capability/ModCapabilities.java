package nameless.classicraft.capability;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.capability.rot.AbstractRot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    public static final Capability<AbstractRot> ROT = CapabilityManager.get(new CapabilityToken<>(){});
    public static Capability<WaterLevelCapability> PLAYER_WATER_LEVEL = CapabilityManager.get(new CapabilityToken<>(){});
    public static Capability<PlayerLastPosCapability> PLAYER_LAST_POSITION = CapabilityManager.get(new CapabilityToken<>(){});
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(AbstractRot.class);
        event.register(WaterLevelCapability.class);
        event.register(PlayerLastPosCapability.class);
    }

    @SubscribeEvent
    public static void addCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(new ResourceLocation(ClassiCraftMod.MODID, "player_thirst_level"), new WaterLevelCapability.Provider());
            event.addCapability(new ResourceLocation(ClassiCraftMod.MODID, "player_last_position"), new PlayerLastPosCapability.Provider());
        }
    }
}
