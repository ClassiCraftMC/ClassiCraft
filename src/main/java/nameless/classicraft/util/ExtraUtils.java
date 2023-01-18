package nameless.classicraft.util;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class ExtraUtils {

    public static final DeferredRegister<? extends Block> deferredRegister = ModBlocks.BLOCKS;

    public static void setVanillaMeta(ItemStack stack) {
        if (stack.is(Items.FLINT)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "flint_pebble");
        }
        if (stack.is(Items.PRISMARINE_SHARD)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "prismarine");
        }
        if (stack.is(Items.QUARTZ)) {
            new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", "quartz_pebble");
        }
    }

    public static Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

}
