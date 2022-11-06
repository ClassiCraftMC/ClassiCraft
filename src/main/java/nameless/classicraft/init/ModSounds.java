package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ClassiCraftMod.MODID);

    public static final RegistryObject<SoundEvent> DEER_DEATH =
            register("entity.classicraft.deer.death");
    public static final RegistryObject<SoundEvent> DEER_HURT =
            register("entity.classicraft.deer.hurt");
    public static final RegistryObject<SoundEvent> DEER_AMBIENT =
            register("entity.classicraft.deer.ambient");
    public static final RegistryObject<SoundEvent> BOAR_AMBIENT =
            register("entity.classicraft.boar.ambient");
    public static final RegistryObject<SoundEvent> BOAR_DEATH =
            register("entity.classicraft.boar.death");
    public static final RegistryObject<SoundEvent> BOAR_HURT =
            register("entity.classicraft.boar.hurt");
    public static final RegistryObject<SoundEvent> BOAR_STEP =
            register("entity.classicraft.boar.step");

    private static RegistryObject<SoundEvent> register(String name) {
        return register(name, () -> new SoundEvent(new ResourceLocation(name.toLowerCase(Locale.ROOT))));
    }

    private static <T extends SoundEvent> RegistryObject<T> register(String name, Supplier<T> soundEvent) {
        return SOUNDS.register(name.toLowerCase(Locale.ROOT), soundEvent);
    }
}
