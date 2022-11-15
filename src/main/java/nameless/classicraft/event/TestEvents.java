package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ProjectileHitEvent;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestEvents {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(TestEvents::testEvent);
    }

    public static void testEvent(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();
        if ((block != null
                && projectile.isInFluidType()
                && block.defaultBlockState().is(ModBlocks.TORCH.get())
                /**&& block.defaultBlockState().getValue(RealisticTorchBlock.getLitState())
                != RealisticTorchBlock.UNLIT*/)) {
            ClassiCraftMod.LOGGER.info("Test 1");
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.TORCH.get().defaultBlockState());
        }
        if ((block != null
                && projectile instanceof ThrownPotion
                && block.defaultBlockState().is(ModBlocks.TORCH.get())
                /**&& block.defaultBlockState().getValue(RealisticTorchBlock.getLitState())
                 != RealisticTorchBlock.UNLIT*/)) {
            ClassiCraftMod.LOGGER.info("Test 2");
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.TORCH.get().defaultBlockState());
        }

    }

}
