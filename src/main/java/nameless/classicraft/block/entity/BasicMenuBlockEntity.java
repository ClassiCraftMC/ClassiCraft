package nameless.classicraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 **/
public abstract class BasicMenuBlockEntity extends BasicBlockEntity implements MenuProvider {

    protected BasicMenuBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.classicraft.fridge");
    }

}
