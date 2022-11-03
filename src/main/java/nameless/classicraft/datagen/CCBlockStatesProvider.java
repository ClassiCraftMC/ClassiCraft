package nameless.classicraft.datagen;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class CCBlockStatesProvider extends BlockStateProvider {

    ExistingFileHelper ex;

    public CCBlockStatesProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, ClassiCraft.MODID, helper);
        this.ex = helper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleSideBlock(ModBlocks.SALT_ORE);
    }

    public static final ResourceLocation TOP = new ResourceLocation(ClassiCraft.MODID, "block/top");

    void simpleSideBlock(RegistryObject<? extends Block> block) {
        String name = block.getId().getPath();
        ResourceLocation texture = blockTexture(block.get());
        BlockModelBuilder model = models().cubeBottomTop(name, texture, TOP, TOP);
        getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(model));
    }

}