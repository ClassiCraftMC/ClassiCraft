package nameless.classicraft.item;

import nameless.classicraft.block.RealSoulTorchBlock;
import nameless.classicraft.block.RealTorchBlock;
import nameless.classicraft.init.ModCreativeModeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DebugBugTimeStick extends Item {

    public DebugBugTimeStick() {
        super(new Properties().tab(ModCreativeModeTabs.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        Player player = pContext.getPlayer();
        if (!level.isClientSide) {
            if (player != null) {
                if (block instanceof RealTorchBlock) {
                    player.displayClientMessage(Component.translatable("info.classicraft.burntime")
                            .append(blockState.getValue(RealTorchBlock.getBurnTime()).toString())
                            .append(Component.translatable("info.classicraft.minutes")), true);
                }
                if (block instanceof RealSoulTorchBlock) {
                    player.displayClientMessage(Component.translatable("info.classicraft.burntime")
                            .append(blockState.getValue(RealSoulTorchBlock.getBurnTime()).toString())
                            .append(Component.translatable("info.classicraft.minutes")), true);
                }
            }
        }
        return super.useOn(pContext);
    }
}
