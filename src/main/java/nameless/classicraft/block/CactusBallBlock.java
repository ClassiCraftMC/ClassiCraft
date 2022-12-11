package nameless.classicraft.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class CactusBallBlock extends FlowerBlock {

    public CactusBallBlock() {
        super(StatusEffects.SPEED, 2, AbstractBlock.Settings.copy(Blocks.DANDELION));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.CACTUS, 1.0F);
    }

    @Override
    public PlantType getPlantType(BlockView level, BlockPos pos) {
        return net.minecraftforge.common.PlantType.DESERT;
    }

}
