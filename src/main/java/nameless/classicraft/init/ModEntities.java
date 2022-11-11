package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.*;
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

    public static final RegistryObject<EntityType<AngleFishEntity>> ANGLEFISH_ENTITY =
            register("anglefish", EntityType.Builder.of(AngleFishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f,0.6f));

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
