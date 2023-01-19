package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            ClassiCraftMod.MOD_ID);

    public static final RegistryObject<SoundEvent> PEBBLE =
            register("misc.knapping.pebble");

    private static RegistryObject<SoundEvent> register(String sound) {
        return SOUND_EVENTS.register(sound, () ->
                SoundEvent.createVariableRangeEvent(
                        new ResourceLocation(ClassiCraftMod.MOD_ID, sound)));
    }
}
