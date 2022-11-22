package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.RanchuEntity;
import nameless.classicraft.util.EventUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EntityEvents {

    /**
    @SubscribeEvent
    public static void onSkeletonAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity hurtMob = entity.getLastHurtByMob();
        if (entity instanceof AbstractSkeleton) {
            if (hurtMob instanceof Player &&) {
                entity.setItemSlot(EquipmentSlot.MAINHAND, Items.BOW.getDefaultInstance());
            }
        }
    }*/

    @SubscribeEvent
    public static void onDamageSkeleton(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity hurtByMob = event.getEntity().getLastHurtByMob();
        if (entity instanceof AbstractSkeleton
                && hurtByMob instanceof Player
                && entity.getLastDamageSource() != null
                && !entity.getLastDamageSource().isProjectile()) {
            entity.setItemSlot(EquipmentSlot.MAINHAND, Items.STONE_SWORD.getDefaultInstance());
        }
    }

    @SubscribeEvent
    public void addWanderingTrader(LivingEvent.LivingTickEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.getLevel();
        if (entity instanceof Player) {
            if (level.getBiome(entity.getOnPos()).is(StructureTags.VILLAGE.location())
                    && level.getDayTime() == 100) {
                WanderingTrader wanderingTrader =
                        new WanderingTrader(EntityType.WANDERING_TRADER, level);
                level.addFreshEntity(wanderingTrader);
            }
        }
    }

    @SubscribeEvent
    public void clearWanderingTraderInNight(LivingEvent.LivingTickEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.getLevel();
        if (entity instanceof WanderingTrader) {
            if (level.getDayTime() >= 14400) {
                entity.remove(Entity.RemovalReason.KILLED);
            }
        }
    }

    @SubscribeEvent
    public void onLivingAttack (LivingAttackEvent event) {

        if (ClassiCraftConfiguration.fireDamageSpreads.get()
                && !event.getEntity().getLevel().isClientSide) {

            final Entity sourceEntity = event.getSource().getEntity();

            if (sourceEntity instanceof final LivingEntity sourceLiving) {

                final ItemStack heldItem = sourceLiving.getMainHandItem();

                if (!(sourceLiving instanceof Zombie) && heldItem.isEmpty() && sourceLiving.isOnFire() && EventUtils.tryPercentage(ClassiCraftConfiguration.fireDamageSpreadChance.get())) {

                    final float damage = Math.max(1, event.getEntity().getLevel().getCurrentDifficultyAt(new BlockPos(event.getEntity().getOnPos())).getEffectiveDifficulty());
                    event.getEntity().setSecondsOnFire(2 * (int) damage);
                }

                if (heldItem.getItem() == Items.FLINT_AND_STEEL && ClassiCraftConfiguration.flintAndSteelDealsFireDamage.get()) {

                    event.getEntity().setSecondsOnFire(ClassiCraftConfiguration.flintAndSteelFireDamage.get());
                    heldItem.hurtAndBreak(1, sourceLiving, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingTick (LivingEvent.LivingTickEvent event) {
        if (ClassiCraftConfiguration.fireResExtinguish.get()
                && !event.getEntity().getLevel().isClientSide
                && event.getEntity().isOnFire()
                && event.getEntity().hasEffect(MobEffects.FIRE_RESISTANCE)) {

            event.getEntity().clearFire();
        }
    }

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {

        if (ClassiCraftConfiguration.flameArrowSkeletons.get()
                && event.getEntity() instanceof Arrow
                && !event.getEntity().getLevel().isClientSide) {

            final Arrow arrowEntity = (Arrow) event.getEntity();
            final Entity shooter = arrowEntity.getOwner();

            if (shooter instanceof AbstractSkeleton && shooter.isOnFire() && shooter.isAlive() && EventUtils.tryPercentage(ClassiCraftConfiguration.flameArrowChance.get())) {

                arrowEntity.setSecondsOnFire(shooter.getRemainingFireTicks());
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath (LivingDeathEvent event) {
        if (event.getSource().isFire()
                && ClassiCraftConfiguration.fireFromDamagesource.get()
                && !event.getEntity().isOnFire() && !event.getEntity().getLevel().isClientSide) {

            event.getEntity().setSecondsOnFire(1);
        }
    }

    @SubscribeEvent
    public static void onDamageSquid(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity attackedEntity = entity.getLastHurtByMob();
        if (entity instanceof Squid
                && attackedEntity!= null
                && entity.isInWater()
                && attackedEntity.isInWater()
                && entity.getLastDamageSource() != null
                && !entity.getLastDamageSource().isProjectile()
                && ClassiCraftConfiguration.enableSquidBlind.get()) {
            attackedEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 70, 1));
        }
    }

    @SubscribeEvent
    public static void onRanchuBreed(BabyEntitySpawnEvent event) {
        if (event.getParentA() instanceof RanchuEntity && event.getParentB() instanceof RanchuEntity) {
            RanchuEntity ranchuA = (RanchuEntity) event.getParentA();
            RanchuEntity ranchuB = (RanchuEntity) event.getParentB();
            RanchuEntity child = (RanchuEntity) event.getChild();
            RandomSource rand = ranchuA.getRandom();

            // Feral + Feral
            if (ranchuA.getVariant() <= 2 && ranchuB.getVariant() <= 2) {
                if (rand.nextFloat() < 0.15) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                } else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            // Fancy + Fancy
            else if (ranchuA.getVariant() > 2 && ranchuB.getVariant() > 2) {
                child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
            }

            // Feral + Fancy
            else if (ranchuA.getVariant() <= 2 || ranchuB.getVariant() <= 2 && ranchuA.getVariant() > 2 || ranchuB.getVariant() > 2) {
                if (rand.nextBoolean()) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                } else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            child.copyPosition(ranchuA);
            child.setBaby(true);
            ranchuA.getCommandSenderWorld().addFreshEntity(child);
        }
    }
}
