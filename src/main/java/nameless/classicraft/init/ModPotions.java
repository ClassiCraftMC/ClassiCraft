package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ClassiCraftMod.MODID);
    public static final RegistryObject<Potion> DROWN_POTION = POTIONS.register("drown_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DROWN.get(), 100, 300, true, true)));
}
