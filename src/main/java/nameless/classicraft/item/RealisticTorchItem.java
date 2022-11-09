package nameless.classicraft.item;

import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class RealisticTorchItem extends StandingAndWallBlockItem {

    public static final int UNLIT = RealisticTorchBlock.UNLIT;
    public static final boolean SHOULD_BURN_OUT = RealisticTorchBlock.SHOULD_BURN_OUT;
    public static final IntegerProperty LITSTATE = RealisticTorchBlock.LITSTATE;

    public RealisticTorchItem(Properties pProperties) {
        super(ModBlocks.TORCH.get().defaultBlockState().setValue(LITSTATE, UNLIT).getBlock(),
                ModBlocks.WALL_TORCH.get().defaultBlockState().setValue(LITSTATE, UNLIT).getBlock(), pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pLevel.isRainingAt(pEntity.getOnPos())) {
            playExtinguishSound(pLevel, pEntity.getOnPos());
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    public void playExtinguishSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }
}
