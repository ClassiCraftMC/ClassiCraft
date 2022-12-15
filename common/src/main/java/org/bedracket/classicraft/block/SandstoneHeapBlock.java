package org.bedracket.classicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.bedracket.classicraft.init.ModItems;

public class SandstoneHeapBlock extends AbstractPickableBlock {

    public SandstoneHeapBlock() {
        super(Properties.of(Material.DIRT).sound(SoundType.STONE).strength(0.15f).noCollission().noOcclusion());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        giveItemToPlayer(level, player, ModItems.SANDSTONE_PEBBLE.get());
        level.removeBlock(pos, false);
        return InteractionResult.SUCCESS;
    }
}
