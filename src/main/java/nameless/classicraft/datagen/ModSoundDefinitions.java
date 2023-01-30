package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModSounds;
import nameless.classicraft.util.Helpers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitions extends SoundDefinitionsProvider {

    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param helper The existing file helper provided by the event you are initializing this provider in.
     */
    protected ModSoundDefinitions(PackOutput output,ExistingFileHelper helper) {
        super(output, ClassiCraftMod.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.PEBBLE.get(),
                definition().with(
                        sound(Helpers.identifier("misc/pebble")).pitch(0.9),
                        sound(Helpers.identifier("misc/pebble")).pitch(1.1)
                ).subtitle("subtitles.misc.classicraft.knapping.pebble"));
        add(ModSounds.DRAGON_FISH.get(),
                definition().with(
                        sound(Helpers.identifier("music/dragon_fish")).pitch(0.9),
                        sound(Helpers.identifier("music/dragon_fish")).pitch(1.1)
                ).subtitle("subtitles.music.classicraft.event.dragon_fish"));
        add(ModSounds.PHOBOS_OUTPOST.get(),
                definition().with(
                        sound(Helpers.identifier("music/phobos_outpost")).pitch(0.9),
                        sound(Helpers.identifier("music/phobos_outpost")).pitch(1.1)
                ).subtitle("subtitles.music.classicraft.event.phobos_outpost"));
        add(ModSounds.MOSSY_OFF.get(),
                definition().with(
                        sound(Helpers.identifier("misc/mossy_off")).pitch(0.9),
                        sound(Helpers.identifier("misc/mossy_off")).pitch(1.1)
                ).subtitle("subtitles.misc.classicraft.moss.mossy_off"));
    }
}
