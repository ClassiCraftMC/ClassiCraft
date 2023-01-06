package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockStateProvider extends BlockStateProvider {
    private final DeferredRegister<? extends Block> deferredRegister;
    private final Set<Block> skipBlocks = new HashSet<>();

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper, DeferredRegister<? extends Block> deferredRegister) {
        super(gen.getPackOutput(), ClassiCraftMod.MOD_ID, exFileHelper);
        this.deferredRegister = deferredRegister;
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Block> blocks = getBlocks();
        addSkipBlocks();
        blocks.removeAll(skipBlocks);

        registerBlock(blocks);
    }

    protected void skipBlock(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected void skipBlock(Collection<Block> blocks) {
        skipBlocks.addAll(blocks);
    }

    protected Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }

    protected void addSkipBlocks() {
        skipBlock(ModBlocks.REAL_TORCH.get(),
                ModBlocks.REAL_WALL_TORCH.get(),
                ModBlocks.REAL_SOUL_TORCH.get(),
                ModBlocks.REAL_SOUL_WALL_TORCH.get(),
                ModBlocks.CACTUS_BALL.get(),
                ModBlocks.ROSE.get(),
                ModBlocks.CRACKED_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_BRICKS_STAIRS.get(),
                ModBlocks.MOSSY_BRICKS_SLAB.get(),
                ModBlocks.CRACKED_BRICKS_SLAB.get(),
                ModBlocks.CRACKED_BRICKS_WALL.get(),
                ModBlocks.MOSSY_BRICKS_WALL.get(),
                ModBlocks.ANDESITE_PEBBLE.get(),
                ModBlocks.COBBLESTONE_PEBBLE.get(),
                ModBlocks.DIORITE_PEBBLE.get(),
                ModBlocks.GRANITE_PEBBLE.get(),
                ModBlocks.RED_SANDSTONE_PEBBLE.get(),
                ModBlocks.SANDSTONE_PEBBLE.get(),
                ModBlocks.DEEPSLATE_PEBBLE.get(),
                ModBlocks.TWIGS.get(),
                ModBlocks.POTTED_ROSE.get(),
                ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get(),
                ModBlocks.SOUL_SANDSTONE_PEBBLE.get(),
                ModBlocks.NETHERRACK_PEBBLE.get(),
                ModBlocks.END_STONE_PEBBLE.get(),
                ModBlocks.BASALT_PEBBLE.get(),
                ModBlocks.BLACKSTONE_PEBBLE.get()
        );
    }
}