package org.bedracket.classicraft.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

public class CharcoalBlock  extends Block {

    public CharcoalBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE,
                MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F));
    }

    @ExpectPlatform
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        throw new AssertionError();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
