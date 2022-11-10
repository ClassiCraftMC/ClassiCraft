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
            register("deer_death");
    public static final RegistryObject<SoundEvent> DEER_HURT =
            register("deer_hurt");
    public static final RegistryObject<SoundEvent> DEER_AMBIENT =
            register("deer_ambient");

    public static final RegistryObject<SoundEvent> GIEGIE =
            register("giegie");

    private static RegistryObject<SoundEvent> register(String name) {
        return register(name, () -> new SoundEvent(new ResourceLocation(name.toLowerCase(Locale.ROOT))));
    }

    private static <T extends SoundEvent> RegistryObject<T> register(String name, Supplier<T> soundEvent) {
        return SOUNDS.register(name.toLowerCase(Locale.ROOT), soundEvent);
    }
}
