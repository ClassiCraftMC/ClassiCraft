package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.potion.NormalEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ClassiCraftMod.MODID);

    public static final RegistryObject<NormalEffect> THIRST =
            MOB_EFFECTS.register("thirst",
                    () -> new NormalEffect(MobEffectCategory.BENEFICIAL, 0xE4D49F));

    public static final RegistryObject<NormalEffect> ACCOMPANYING_SOUL =
            MOB_EFFECTS.register("accompanying_soul",
                    () -> new NormalEffect(MobEffectCategory.BENEFICIAL, 0x634C3E));

    public static final RegistryObject<NormalEffect> WATER_RESTORING =
            MOB_EFFECTS.register("water_restoring",
                    () -> new NormalEffect(MobEffectCategory.BENEFICIAL, 0x379AD6));


}
