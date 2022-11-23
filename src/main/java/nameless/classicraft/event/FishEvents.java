package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.fish.FishingConversion;
import nameless.classicraft.fish.FishingManager;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FishEvents {

    public static final FishingManager FISHING_MANAGER = new FishingManager();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerStarting(AddReloadListenerEvent event) {
        event.addListener(FISHING_MANAGER);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void itemFished(ItemFishedEvent event) {
        Player angler = event.getEntity();
        FishingHook hook = event.getHookEntity();
        List<ItemStack> drops = event.getDrops();
        for (ItemStack stack : drops) {
            FishingConversion conversion = FISHING_MANAGER.getConversionFromStack(stack);
            if (conversion != null && conversion.result().entity() != null) {
                FishingConversion.FishingResult result = conversion.result();
                Entity entity = result.entity().create(angler.level);
                if (entity != null) {
                    result.tag().ifPresent(entity::load);
                    Level l = angler.level;
                    if (l instanceof ServerLevel level) {
                        entity.moveTo(hook.position().x(), hook.position().y(), hook.position().z(), hook.xRotO, hook.yRotO);
                        double dX = angler.position().x() - hook.position().x();
                        double dY = angler.position().y() - hook.position().y();
                        double dZ = angler.position().z() - hook.position().z();
                        double mult = 0.12;
                        entity.setDeltaMovement(dX * mult, dY * mult + Math.sqrt(Math.sqrt(dX * dX + dY * dY + dZ * dZ)) * 0.14D, dZ * mult);

                        level.addFreshEntity(new ExperienceOrb(angler.level, angler.position().x(), angler.position().y() + 0.5D, angler.position().z() + 0.5D, angler.level.getRandom().nextInt(6) + 1));

                        if (stack.is(ItemTags.FISHES)) {
                            angler.awardStat(Stats.FISH_CAUGHT, 1);
                        }

                        if (angler instanceof ServerPlayer) {
                            CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) angler, angler.getUseItem(), hook, Arrays.asList(stack));
                        }

                        if (result.randomizeNbt() && entity instanceof Mob mob ) {
                            mob.finalizeSpawn(level, level.getCurrentDifficultyAt(angler.blockPosition()), MobSpawnType.NATURAL, null, null);
                        }
                        level.addFreshEntity(entity);
                    }
                    if (!angler.isCreative()) {
                        event.damageRodBy(1);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }
}
