package nameless.classicraft.glm;

import com.mojang.serialization.Codec;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.ClassiCraftMod;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS;


/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER =
            DeferredRegister.create(GLOBAL_LOOT_MODIFIER_SERIALIZERS, ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Codec<ReplaceDropModifier>> REPLACE_DROP =
            ModLootModifiers.REGISTER.register("replace_drop", ReplaceDropModifier::createCodec);
}
