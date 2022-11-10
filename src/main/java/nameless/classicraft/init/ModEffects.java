package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.effect.DrownEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ClassiCraftMod.MODID);

    public static final RegistryObject<MobEffect> DROWN =
            register("drown", DrownEffect::new);

    private static <T extends MobEffect> RegistryObject<MobEffect> register(String name, Supplier<T> effect) {
        return MOB_EFFECTS.register(name.toLowerCase(Locale.ROOT), effect);
    }
}
