package nameless.classicraft.common.rot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber
public class RotManagerHandler {
    static int timer;

    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && timer++ > 20) {
            timer = 0;
            RotManager.INSTANCE.second();
        }
    }

    static final Map<Player, RotManager.Info> playerMap = new HashMap<>();
    static final Map<Player, RotManager.Info> enderChestMap = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        RotManager.Info info = RotManager.Info.playerInv(player.getInventory(), player);
        playerMap.put(player, info);
        RotManager.INSTANCE.addInfo(info);

        RotManager.Info info1 = RotManager.Info.enderChest(player.getEnderChestInventory());
        enderChestMap.put(player, info1);
        RotManager.INSTANCE.addInfo(info1);
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        RotManager.INSTANCE.removeInfo(playerMap.get(player));
        playerMap.remove(player);

        RotManager.INSTANCE.removeInfo(enderChestMap.get(player));
        enderChestMap.remove(player);
    }

    static final Map<Entity, RotManager.Info> entityMap = new HashMap<>();

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof ItemEntity itemEntity && itemEntity.getItem().getCapability(ModCapabilities.ROT).isPresent()) {
            RotManager.Info info = RotManager.Info.itemEntity(itemEntity);
            entityMap.put(entity, info);
            RotManager.INSTANCE.addInfo(info);
        }
    }

    @SubscribeEvent
    public static void onEntityDead(EntityLeaveLevelEvent event) {
        if (entityMap.containsKey(event.getEntity())) {
            RotManager.INSTANCE.removeInfo(entityMap.get(event.getEntity()));
        }
    }
}
