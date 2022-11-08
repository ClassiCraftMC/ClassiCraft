package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.BoarEntity;
import nameless.classicraft.entity.DeerEntity;
import nameless.classicraft.entity.LionfishEntity;
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

    public static final RegistryObject<EntityType<LionfishEntity>> LIONFISH_ENTITY =
            register("lionfish", EntityType.Builder.of(LionfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.5F));

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
