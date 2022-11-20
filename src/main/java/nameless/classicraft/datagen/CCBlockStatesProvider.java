package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CCBlockStatesProvider extends BlockStateProvider {


    public CCBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ClassiCraftMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SALT_BLOCK.get());
        simpleBlock(ModBlocks.SALT_ROCK_BLOCK.get());
        simpleBlock(ModBlocks.ALGAE.get());
        simpleBlock(ModBlocks.CHARCOAL_BLOCK.get());
    }
}