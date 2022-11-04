package nameless.classicraft.common.entity;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.entity.passive.DeerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ClassiCraft.MODID);

    public static RegistryObject<EntityType<DeerEntity>> DEER_ENEITY = register("deer",
            EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE)
                    .sized(0.7F, 1.8F));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(ClassiCraft.MODID + ":" + id);
        });
    }
}
