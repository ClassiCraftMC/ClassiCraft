package org.bedracket.classicraft.block.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class CharcoalBlockImpl {


    public static int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    public static boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }
}