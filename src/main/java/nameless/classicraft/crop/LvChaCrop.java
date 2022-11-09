package nameless.classicraft.crop;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;

public class LvChaCrop extends CropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public LvChaCrop() {
        super(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}
