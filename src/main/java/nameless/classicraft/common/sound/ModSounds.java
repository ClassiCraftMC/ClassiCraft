package nameless.classicraft.common.sound;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.ClassiCraftHooks;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ClassiCraft.MODID);

    public static final RegistryObject<SoundEvent> DEER_DEATH = createEvent("entity.classicraft.deer.death");
    public static final RegistryObject<SoundEvent> DEER_HURT = createEvent("entity.classicraft.deer.hurt");
    public static final RegistryObject<SoundEvent> DEER_AMBIENT = createEvent("entity.classicraft.deer.ambient");

    private static RegistryObject<SoundEvent> createEvent(String sound) {
        return SOUNDS.register(sound, () -> new SoundEvent(ClassiCraftHooks.prefix(sound)));
    }

}
