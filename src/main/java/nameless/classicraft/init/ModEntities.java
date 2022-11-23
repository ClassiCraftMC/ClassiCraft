package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.*;
import nameless.classicraft.entity.projectile.JavelinThrownEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ClassiCraftMod.MODID);

    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU_ENTITY =
            register("ranchu", EntityType.Builder.of(RanchuEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<LionfishEntity>> LIONFISH_ENTITY =
            register("lionfish", EntityType.Builder.of(LionfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.5F));

    public static final RegistryObject<EntityType<TroutEntity>> TROUT_ENTITY =
            register("trout", EntityType.Builder.of(TroutEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.3F)
                    .clientTrackingRange(4));

    public static final RegistryObject<EntityType<AngleFishEntity>> ANGLEFISH_ENTITY =
            register("anglefish", EntityType.Builder.of(AngleFishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f,0.6f));

    public static final RegistryObject<EntityType<ButterflyEntity>> BUTTERFLY_ENTITY =
            register("butterfly", EntityType.Builder.of(ButterflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.4f,0.3f));

    public static final RegistryObject<EntityType<FoxFaceFishEntity>> FOXFACE_FISH_ENTITY =
            register("foxface_fish", EntityType.Builder.of(FoxFaceFishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4f,0.3f));

    public static final RegistryObject<EntityType<SwineEntity>> SWINE_ENTITY =
            register("swine", EntityType.Builder.of(SwineEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.9F, 0.9F)
                    .clientTrackingRange(10));

    public static final RegistryObject<EntityType<BaskingSharkEntity>> BASKING_SHARK_ENTITY =
            register("basking_shark", EntityType.Builder.of(BaskingSharkEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.5f,1.8f));

    public static final RegistryObject<EntityType<BullSharkEntity>> BULL_SHARK_ENTITY =
            register("bull_shark", EntityType.Builder.of(BullSharkEntity::new, MobCategory.WATER_CREATURE)
                    .sized(1.4f,1.1f));

    public static final RegistryObject<EntityType<OceanSharkEntity>> OCEAN_SHARK_ENTITY =
            register("ocean_shark", EntityType.Builder.of(OceanSharkEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f));

    public static final RegistryObject<EntityType<LemonSharkEntity>> LEMON_SHARK_ENTITY =
            register("lemon_shark", EntityType.Builder.of(LemonSharkEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f));

    public static final RegistryObject<EntityType<SleeperSharkEntity>> SLEEPER_SHARK_ENTITY =
            register("sleeper_shark", EntityType.Builder.of(SleeperSharkEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f));

    public static final RegistryObject<EntityType<ArapaimaEntity>> ARAPAIMA_ENTITY =
            register("arapaima", EntityType.Builder.of(ArapaimaEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.0f,0.6f));

    public static final RegistryObject<EntityType<BelugaSturgeonEntity>> BELUGA_STURGEON_ENTITY =
            register("beluga_sturgeon", EntityType.Builder.of(BelugaSturgeonEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.4f,1.1f));

    public static final RegistryObject<EntityType<PerchEntity>> PERCH_ENTITY =
            register("perch", EntityType.Builder.of(PerchEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.5F));

    public static final RegistryObject<EntityType<BoxfishEntity>> BOXFISH_ENTITY =
            register("boxfish", EntityType.Builder.of(BoxfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4f,0.3f));

    public static final RegistryObject<EntityType<FootballFishEntity>> FOOTBALLFISH_ENTITY =
            register("footballfish", EntityType.Builder.of(FootballFishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1.0f, 0.8f));

    public static RegistryObject<EntityType<DeerEntity>> DEER_ENEITY =
            register("deer", EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE)
                    .sized(0.7F, 1.8F));

    public static final RegistryObject<EntityType<BoarEntity>> BOAR_ENTITY =
            register("boar", EntityType.Builder.of(BoarEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.9F));

    public static final RegistryObject<EntityType<RiverSharkEntity>> RIVER_SHARK_ENTITY =
            register("river_shark", EntityType.Builder.of(RiverSharkEntity::new, MobCategory.CREATURE)
                    .sized(0.6f,0.7f));

    public static final RegistryObject<EntityType<SoulEmptyEntity>> SOUL_EMPTY_ENTITY =
            register("soul_empty", EntityType.Builder.of(SoulEmptyEntity::new, MobCategory.MISC)
                    .setTrackingRange(256)
                    .setUpdateInterval(20)
                    .sized(0.0001F, 0.0001F));

    public static final RegistryObject<EntityType<JavelinThrownEntity>> THROWN_JAVELIN =
            register("thrown_javelin",
                    EntityType.Builder.<JavelinThrownEntity>of(JavelinThrownEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(ClassiCraftMod.MODID + ":" + id);
        });
    }
}
