package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,
                            ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Block> REAL_TORCH =
            register("real_torch", RealTorchBlock::new);
    public static final RegistryObject<Block> REAL_SOUL_TORCH =
            register("real_soul_torch", RealSoulTorchBlock::new);

    public static final RegistryObject<Block> REAL_WALL_TORCH =
            register("real_wall_torch", RealWallTorchBlock::new);
    public static final RegistryObject<Block> REAL_SOUL_WALL_TORCH =
            register("real_soul_wall_torch", RealSoulWallTorchBlock::new);

    public static final RegistryObject<Block> CHARCOAL_BLOCK =
            register("charcoal_block", CharCoalBlock::new);

    /**
     * Used for registry blocks
     * @param name Blocks' registry name
     * @param item Block Instance
     * @return new RegistryObject<Block>
     * @param <T> sth extends Block
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> item) {
        return BLOCKS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
