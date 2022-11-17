package nameless.classicraft.block.entityblock;

import nameless.classicraft.block.entity.BasicBlockEntity;
import nameless.classicraft.block.entity.BasicMenuBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class AbstractGuiEntityBlock<T extends BasicBlockEntity> extends AbstractEntityBlock<T> {

    protected AbstractGuiEntityBlock(Properties properties, Supplier<BlockEntityType<T>> supplier) {
        super(properties, supplier);
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            var be = pLevel.getBlockEntity(pPos);

            if (be instanceof BasicMenuBlockEntity kk) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, kk, be.getBlockPos());
                kk.forceOnce();
            }
            return InteractionResult.CONSUME;
        }
    }
}
