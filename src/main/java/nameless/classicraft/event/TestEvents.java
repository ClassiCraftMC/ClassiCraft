package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.ProjectileHitEvent;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.entity.Entity;
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
        Entity entity = event.getEntity();
        ClassiCraftMod.LOGGER.info("Test Event +1");
        if (block != null
                && block.defaultBlockState().is(ModBlocks.TORCH.get())
                && entity.isOnFire()) {
            entity.getLevel().setBlockAndUpdate(entity.getOnPos(),
                    ModBlocks.TORCH.get().defaultBlockState()
                            .setValue(RealisticTorchBlock.getLitState(),2)
                            .setValue(RealisticTorchBlock.BURNTIME,
                                    RealisticTorchBlock.getInitialBurnTime()));
            ClassiCraftMod.LOGGER.info("Test Events");
        }
    }

}
