package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.LivingDeadEntity;
import nameless.classicraft.entity.OceanSharkEntity;
import nameless.classicraft.entity.TroutEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

/**
 * @author wdog5
 */
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
                    ClassiCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<TroutEntity>> TROUT_ENTITY =
            register("trout", EntityType.Builder.of(TroutEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.3F)
                    .clientTrackingRange(4));

    public static final RegistryObject<EntityType<LivingDeadEntity>> LIVING_DEAD =
            register("living_dead", EntityType.Builder.<LivingDeadEntity>of(LivingDeadEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8));

    public static final RegistryObject<EntityType<OceanSharkEntity>> OCEAN_SHARK_ENTITY =
            register("ocean_shark", EntityType.Builder.of(OceanSharkEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f)
                    .clientTrackingRange(10));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(ClassiCraftMod.MOD_ID + ":" + id);
        });
    }
}
