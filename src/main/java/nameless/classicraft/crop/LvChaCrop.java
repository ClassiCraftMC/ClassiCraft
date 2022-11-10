package nameless.classicraft.crop;

import nameless.classicraft.init.ModItems;
import net.minecraft.world.level.ItemLike;
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

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.LVCHA_SEED.get();
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}
