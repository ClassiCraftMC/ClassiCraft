package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.block.realistic.RealisticSoulTorchBlock;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.util.EventUtils;
import nameless.classicraft.util.LightUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void onBlockOverlay (RenderBlockScreenEffectEvent event) {
        if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.FIRE && ClassiCraftConfiguration.fireOverlay.get() && (event.getPlayer().fireImmune() || event.getPlayer().hasEffect(MobEffects.FIRE_RESISTANCE))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void breakeLeaves(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        Player player = event.getPlayer();
        Level level = player.getLevel();
        if (block instanceof LeavesBlock) {
            ItemEntity itemEntity = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), Items.STICK.getDefaultInstance());
            level.addFreshEntity(itemEntity);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak (BlockEvent.BreakEvent event) {

        if (event.getPlayer() != null && !(event.getPlayer() instanceof FakePlayer)) {

            final Block block = event.getState().getBlock();

            final int burnTime = block == Blocks.FIRE ? ClassiCraftConfiguration.fireHitBurnTime.get() : block == Blocks.SOUL_FIRE ? ClassiCraftConfiguration.soulfireHitBurnTime.get() : 0;

            if (burnTime > 0) {

                event.getPlayer().setSecondsOnFire(burnTime);
            }

            if (block instanceof FireBlock && !ClassiCraftConfiguration.punchOutFlames.get()) {

                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void rightClickBlockWithItem (PlayerInteractEvent.RightClickBlock event) {
        if (ClassiCraftConfiguration.extinguishWithBottle.get()
                && event.getEntity() != null
                && !(event.getEntity() instanceof FakePlayer)) {

            final BlockState state = event.getLevel().getBlockState(event.getPos());
            final Block block = state.getBlock();
            final ItemStack stack = event.getItemStack();
            final Potion potion = PotionUtils.getPotion(stack);

            if (block == Blocks.FIRE || block == Blocks.SOUL_FIRE) {

                if (stack.getItem() == Items.POTION && Potions.WATER == potion && !event.getLevel().isClientSide) {

                    event.getLevel().setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
                    event.getLevel().levelEvent((Player) null, 1009, event.getPos(), 0);
                    ItemHandlerHelper.giveItemToPlayer(event.getEntity(), new ItemStack(Items.GLASS_BOTTLE));
                    stack.shrink(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void extinguishItemByPotion(ProjectileImpactEvent event) {
        EventUtils.extinguishTorchItemByPotion(event, ModBlocks.TORCH.get());
        EventUtils.extinguishTorchItemByPotion(event, ModBlocks.SOUL_TORCH.get());
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LANTERN.get(), LightUtils.LANTERN_BURNTIME, LightUtils.LANTERN_TOTAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.SOUL_LANTERN.get(), LightUtils.LANTERN_BURNTIME, LightUtils.LANTERN_TOTAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.FIRE_BOWL.get(), LightUtils.FIRE_BOWL_BURNTIME, LightUtils.FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.SOUL_FIRE_BOWL.get(), LightUtils.FIRE_BOWL_BURNTIME, LightUtils.FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LARGE_FIRE_BOWL.get(), LightUtils.LARGE_FIRE_BOWL_BURNTIME, LightUtils.LARGE_FIRE_BOWL_INITIAL_BURN_TIME);
        EventUtils.extinguishBlockNeedFuelByPotion(event, ModBlocks.LARGE_SOUL_FIRE_BOWL.get(), LightUtils.LARGE_FIRE_BOWL_BURNTIME, LightUtils.LARGE_FIRE_BOWL_INITIAL_BURN_TIME);
    }

    @SubscribeEvent
    public static void projectileFireOnTorch(ProjectileImpactEvent event) {
        EventUtils.projectileTorchToFire(event, ModBlocks.TORCH.get());
        EventUtils.projectileTorchToFire(event, ModBlocks.SOUL_TORCH.get());
    }

    @SubscribeEvent
    public static void rightClickTorch(PlayerInteractEvent.RightClickBlock event) {
        Block block = event.getLevel().getBlockState(event.getPos()).getBlock();
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
                LightUtils.playExtinguishSound(event.getLevel(), event.getPos());
            }
        }
    }

    @SubscribeEvent
    public static void rightClickLantern(PlayerInteractEvent.RightClickBlock event) {
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
