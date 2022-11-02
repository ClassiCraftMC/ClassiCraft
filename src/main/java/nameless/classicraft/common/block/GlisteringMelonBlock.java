package nameless.classicraft.common.block;

import nameless.classicraft.api.common.block.entity.RotAbleEntityBlock;
import nameless.classicraft.common.block.entity.ModBlockEntities;
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
