package nameless.classicraft.util;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class ExtraUtils {

    public static final DeferredRegister<? extends Block> deferredRegister = ModBlocks.BLOCKS;

    public static Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

}
