package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author wdog5
 */
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            ClassiCraftMod.MOD_ID);

    public static final RegistryObject<SoundEvent> PEBBLE =
            register("misc.knapping.pebble");
    public static final RegistryObject<SoundEvent> DRAGON_FISH =
            register("music.event.dragon_fish");
    public static final RegistryObject<SoundEvent> PHOBOS_OUTPOST =
            register("music.event.phobos_outpost");

    private static RegistryObject<SoundEvent> register(String sound) {
        return SOUND_EVENTS.register(sound, () ->
                SoundEvent.createVariableRangeEvent(
                        new ResourceLocation(ClassiCraftMod.MOD_ID, sound)));
    }
}
