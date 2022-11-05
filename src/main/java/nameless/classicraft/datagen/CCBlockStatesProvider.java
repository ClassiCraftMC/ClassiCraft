package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CCBlockStatesProvider extends BlockStateProvider {


    public CCBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ClassiCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SALT_BLOCK.get());
    }
}