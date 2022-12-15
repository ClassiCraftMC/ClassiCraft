package org.bedracket.classicraft.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import org.bedracket.classicraft.init.ModBlocks;
import org.bedracket.classicraft.util.EventUtils;

public class BlockEvents {

    public static void registerBlockEvents() {
        EventUtils.blockDropAtOnce(ModBlocks.REAL_TORCH.get(), Items.STICK);
        EventUtils.blockDropAtOnce(ModBlocks.REAL_WALL_TORCH.get(), Items.STICK);
        EventUtils.blockDropAtOnce(ModBlocks.REAL_SOUL_TORCH.get(), Items.STICK);
        EventUtils.blockDropAtOnce(ModBlocks.REAL_SOUL_WALL_TORCH.get(), Items.STICK);
        BlockEvent.BREAK.register((level, pos, state, player, xp) -> {
            Block block = state.getBlock();
            if (block instanceof LeavesBlock) {
                EventUtils.blockDropRandom(block, Items.STICK, 1, 2);
            }
            return EventResult.pass();
        });
    }
}
