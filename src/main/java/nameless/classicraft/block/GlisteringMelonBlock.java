package nameless.classicraft.block;

import nameless.classicraft.api.block.entity.RotAbleEntityBlock;
import nameless.classicraft.init.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * @author DustW
 */
public class GlisteringMelonBlock extends Block implements RotAbleEntityBlock {
    public GlisteringMelonBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.GLISTERING_MELON.get();
    }
}
