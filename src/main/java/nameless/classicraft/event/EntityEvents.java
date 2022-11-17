package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.entity.RanchuEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityEvents {

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
