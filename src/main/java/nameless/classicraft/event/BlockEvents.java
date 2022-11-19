package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import nameless.classicraft.api.light.LightAPI;
import nameless.classicraft.block.realistic.RealisticSoulTorchBlock;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void extinguishItemByPotion(ProjectileImpactEvent event) {
        EventUtils.extinguishTorchItemByPotion(event, ModBlocks.TORCH.get());
        EventUtils.extinguishTorchItemByPotion(event, ModBlocks.SOUL_TORCH.get());
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LANTERN.get(), LightAPI.LANTERN_BURNTIME, LightAPI.LANTERN_TOTAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.SOUL_LANTERN.get(), LightAPI.LANTERN_BURNTIME, LightAPI.LANTERN_TOTAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.FIRE_BOWL.get(), LightAPI.FIRE_BOWL_BURNTIME, LightAPI.FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.SOUL_FIRE_BOWL.get(), LightAPI.FIRE_BOWL_BURNTIME, LightAPI.FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LARGE_FIRE_BOWL.get(), LightAPI.LARGE_FIRE_BOWL_BURNTIME, LightAPI.LARGE_FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LARGE_SOUL_FIRE_BOWL.get(), LightAPI.LARGE_FIRE_BOWL_BURNTIME, LightAPI.LARGE_FIRE_BOWL_INITIAL_BURN_TIME);
    }

    @SubscribeEvent
    public static void projectileFireOnTorch(ProjectileImpactEvent event) {
        EventUtils.projectileTorchToFire(event, ModBlocks.TORCH.get());
        EventUtils.projectileTorchToFire(event, ModBlocks.SOUL_TORCH.get());
    }

    @SubscribeEvent
    public static void rightClickTorch(PlayerRightClickBlockEvent event) {
        Block block = event.getBlock();
        if (event.getEntity().isShiftKeyDown()) {
            if (block instanceof RealisticTorchBlock || block instanceof RealisticSoulTorchBlock) {
                event.getLevel().setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
                ItemEntity itemEntity = new ItemEntity(
                        event.getLevel(),
                        event.getPos().getX(),
                        event.getPos().getY(),
                        event.getPos().getZ(),
                        Items.STICK.getDefaultInstance());
                event.getLevel().addFreshEntity(itemEntity);
                LightAPI.playExtinguishSound(event.getLevel(), event.getPos());
            }
        }
    }

    @SubscribeEvent
    public static void rightClickLantern(PlayerRightClickBlockEvent event) {
        EventUtils.rightClickBlockNeedFuel(event, ModBlocks.LANTERN.get());
        EventUtils.rightClickBlockNeedFuel(event, ModBlocks.SOUL_LANTERN.get());
    }

    @SubscribeEvent
    public static void stopBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        Block block = event.getPlacedBlock().getBlock();
        LevelAccessor level = event.getLevel();
        Item item = block.asItem();
        if (entity instanceof Player && block instanceof TorchBlock
                && !item.getDefaultInstance().is(Items.REDSTONE_TORCH)
                && !item.getDefaultInstance().is(Items.SOUL_TORCH)
                && ClassiCraftConfiguration.noVanillaTorchPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.TORCH.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_torch"));
            }
        }
        if (entity instanceof Player && block instanceof TorchBlock
                && !item.getDefaultInstance().is(Items.REDSTONE_TORCH)
                && !item.getDefaultInstance().is(Items.TORCH)
                && ClassiCraftConfiguration.noVanillaTorchPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.SOUL_TORCH.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_torch"));
            }
        }
        /**
        if (entity instanceof Player
                && block instanceof LanternBlock
                && !item.getDefaultInstance().is(Items.SOUL_LANTERN)
                && !item.getDefaultInstance().is(ModItems.SOUL_LANTERN.get().asItem())
                && !item.getDefaultInstance().is(ModItems.LIT_SOUL_LANTERN.get())
                && ClassiCraftConfiguration.noVanillaLanternPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.LANTERN.get().defaultBlockState(), 1);
            }
        }
        if (entity instanceof Player
                && block instanceof LanternBlock
                && !item.getDefaultInstance().is(Items.LANTERN)
                && !item.getDefaultInstance().is(ModItems.LANTERN.get().asItem())
                && !item.getDefaultInstance().is(ModItems.LIT_LANTERN.get())
                && ClassiCraftConfiguration.noVanillaLanternPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.SOUL_LANTERN.get().defaultBlockState(), 1);
            }
        }*/
    }
}
