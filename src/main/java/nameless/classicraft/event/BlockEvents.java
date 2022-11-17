package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import nameless.classicraft.api.event.ProjectileHitEvent;
import nameless.classicraft.api.light.LightAPI;
import nameless.classicraft.block.realistic.RealisticLanternBlock;
import nameless.classicraft.block.realistic.RealisticSoulLanternBlock;
import nameless.classicraft.block.realistic.RealisticSoulTorchBlock;
import nameless.classicraft.block.realistic.RealisticTorchBlock;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void extinguishTorchByPotion(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();
        if ((block != null
                && projectile instanceof ThrownPotion
                && block.defaultBlockState().is(ModBlocks.TORCH.get()))) {
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    Blocks.AIR.defaultBlockState());
            ItemEntity newItem = new ItemEntity(
                    projectile.getLevel(),
                    projectile.getX(), projectile.getY(),
                    projectile.getZ(),
                    Items.STICK.getDefaultInstance());
            projectile.getLevel().addFreshEntity(newItem);
        }
        if ((block != null
                && projectile instanceof ThrownPotion
                && block.defaultBlockState().is(ModBlocks.SOUL_TORCH.get()))) {
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    Blocks.AIR.defaultBlockState());
            ItemEntity newItem = new ItemEntity(
                    projectile.getLevel(),
                    projectile.getX(), projectile.getY(),
                    projectile.getZ(),
                    Items.STICK.getDefaultInstance());
            projectile.getLevel().addFreshEntity(newItem);
        }
    }

    @SubscribeEvent
    public static void extinguishLanternByPotion(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();
        if ((block != null
                && projectile instanceof ThrownPotion
                && block.defaultBlockState().is(ModBlocks.LANTERN.get()))) {
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(RealisticLanternBlock.LANTERN_BURNTIME,
                                    RealisticLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticLanternBlock.HANGING,
                                    block.defaultBlockState().getValue(RealisticLanternBlock.HANGING))
                            .setValue(RealisticLanternBlock.WATERLOGGED,
                                    block.defaultBlockState().getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
        if ((block != null
                && projectile instanceof ThrownPotion
                && block.defaultBlockState().is(ModBlocks.SOUL_LANTERN.get()))) {
            ModBlockProperties.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.SOUL_LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(RealisticLanternBlock.LANTERN_BURNTIME,
                                    RealisticLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticLanternBlock.HANGING,
                                    block.defaultBlockState().getValue(RealisticLanternBlock.HANGING))
                            .setValue(RealisticLanternBlock.WATERLOGGED,
                                    block.defaultBlockState().getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
    }

    @SubscribeEvent
    public static void projetileFireOnLantern(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile entity = event.getEntity();
        if (block != null
                && block.defaultBlockState().is(ModBlocks.LANTERN.get())
                && entity.isOnFire()
                && block.defaultBlockState().getValue(LightAPI.getLitState())
                != RealisticLanternBlock.LIT
                && block.defaultBlockState().getValue(LightAPI.OIL) != 0
                && entity.getLevel().isRainingAt(event.getEntity().getOnPos().above())) {
            ModBlockProperties.playLightingSound(entity.getLevel(), entity.getOnPos());
            entity.getLevel().setBlockAndUpdate(entity.getOnPos(),
                    ModBlocks.LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),1)
                            .setValue(RealisticLanternBlock.LANTERN_BURNTIME,
                                    RealisticLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticLanternBlock.HANGING,
                                    block.defaultBlockState().getValue(RealisticLanternBlock.HANGING))
                            .setValue(RealisticLanternBlock.WATERLOGGED,
                                    block.defaultBlockState().getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            entity.getLevel().updateNeighborsAt(entity.getOnPos(), block);
        }
        if (block != null
                && block.defaultBlockState().is(ModBlocks.SOUL_LANTERN.get())
                && entity.isOnFire()
                && block.defaultBlockState().getValue(LightAPI.getLitState())
                != RealisticSoulLanternBlock.LIT
                && block.defaultBlockState().getValue(LightAPI.OIL) != 0
                && entity.getLevel().isRainingAt(event.getEntity().getOnPos().above())) {
            ModBlockProperties.playLightingSound(entity.getLevel(), entity.getOnPos());
            entity.getLevel().setBlockAndUpdate(entity.getOnPos(),
                    ModBlocks.SOUL_LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(), 1)
                            .setValue(RealisticSoulLanternBlock.LANTERN_BURNTIME,
                                    RealisticSoulLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticSoulLanternBlock.HANGING,
                                    block.defaultBlockState().getValue(RealisticSoulLanternBlock.HANGING))
                            .setValue(RealisticSoulLanternBlock.WATERLOGGED,
                                    block.defaultBlockState().getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            entity.getLevel().updateNeighborsAt(entity.getOnPos(), block);
        }
    }

    @SubscribeEvent
    public static void projectileFireOnTorch(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Entity entity = event.getEntity();
        if (block != null
                && block.defaultBlockState().is(ModBlocks.TORCH.get())
                && entity.isOnFire()
                && block.defaultBlockState().getValue(RealisticTorchBlock.getLitState())
                != RealisticTorchBlock.LIT) {
            ModBlockProperties.playLightingSound(entity.getLevel(), entity.getOnPos());
            entity.getLevel().setBlockAndUpdate(entity.getOnPos(),
                    ModBlocks.TORCH.get().defaultBlockState()
                            .setValue(RealisticTorchBlock.getLitState(),2)
                            .setValue(RealisticTorchBlock.TORCH_BURNTIME,
                                    RealisticTorchBlock.getInitialBurnTime()));
        }
        if (block != null
                && block.defaultBlockState().is(ModBlocks.SOUL_TORCH.get())
                && entity.isOnFire()
                && block.defaultBlockState().getValue(RealisticSoulTorchBlock.getLitState())
                != RealisticSoulTorchBlock.LIT) {
            ModBlockProperties.playLightingSound(entity.getLevel(), entity.getOnPos());
            entity.getLevel().setBlockAndUpdate(entity.getOnPos(),
                    ModBlocks.SOUL_TORCH.get().defaultBlockState()
                            .setValue(RealisticSoulTorchBlock.getLitState(),2)
                            .setValue(RealisticSoulTorchBlock.TORCH_BURNTIME,
                                    RealisticSoulTorchBlock.getInitialBurnTime()));
        }
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
            }
        }
    }

    @SubscribeEvent
    public static void rightClickLantern(PlayerRightClickBlockEvent event) {
        Block block = event.getBlock();
        if (event.getEntity().isShiftKeyDown()) {
            if (block instanceof RealisticLanternBlock) {
                event.getLevel().setBlockAndUpdate(event.getPos(), ModBlocks.LANTERN.get().defaultBlockState());
                event.getLevel().updateNeighborsAt(event.getPos(), block);
            }
            if (block instanceof RealisticSoulLanternBlock) {
                event.getLevel().setBlockAndUpdate(event.getPos(), ModBlocks.SOUL_LANTERN.get().defaultBlockState());
                event.getLevel().updateNeighborsAt(event.getPos(), block);
            }
        }
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
                && block.defaultBlockState().is(ModBlocks.SOUL_TORCH.get())
                && !item.getDefaultInstance().is(ModItems.SOUL_TORCH.get())
                && !item.getDefaultInstance().is(ModItems.LIT_SOUL_TORCH.get())
                && ClassiCraftConfiguration.noVanillaTorchPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.TORCH.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_torch"));
            }
        }
        if (entity instanceof Player && block instanceof TorchBlock
                && block.defaultBlockState().is(ModBlocks.TORCH.get())
                && !item.getDefaultInstance().is(Items.REDSTONE_TORCH)
                && !item.getDefaultInstance().is(Items.TORCH)
                && !item.getDefaultInstance().is(ModItems.TORCH.get())
                && !item.getDefaultInstance().is(ModItems.LIT_TORCH.get())
                && ClassiCraftConfiguration.noVanillaTorchPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.SOUL_TORCH.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_torch"));
            }
        }
        if (entity instanceof Player
                && block instanceof LanternBlock
                && block.defaultBlockState().is(ModBlocks.SOUL_LANTERN.get())
                && !item.getDefaultInstance().is(Items.SOUL_LANTERN)
                && !item.getDefaultInstance().is(ModItems.SOUL_LANTERN.get().asItem())
                && !item.getDefaultInstance().is(ModItems.LIT_SOUL_LANTERN.get())
                && ClassiCraftConfiguration.noVanillaLanternPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.LANTERN.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_lantern"));
            }
        }
        if (entity instanceof Player
                && block instanceof LanternBlock
                && block.defaultBlockState().is(ModBlocks.LANTERN.get())
                && !item.getDefaultInstance().is(Items.LANTERN)
                && !item.getDefaultInstance().is(ModItems.LANTERN.get().asItem())
                && !item.getDefaultInstance().is(ModItems.LIT_LANTERN.get())
                && ClassiCraftConfiguration.noVanillaLanternPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), ModBlocks.SOUL_LANTERN.get().defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_lantern"));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCauldronInteract(PlayerRightClickBlockEvent event) {
        Block block = event.getBlock();
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        if (block.defaultBlockState().is(Blocks.CAULDRON)) {
            ClassiCraftMod.LOGGER.info("Test!");
            if (itemStack.is(Items.BLACK_WOOL)
                    && block.defaultBlockState().getValue(LayeredCauldronBlock.LEVEL) == 3) {
                itemStack.split(1);
                ItemStack newItem = new ItemStack(Items.WHITE_WOOL);
                player.getInventory().add(newItem);
                event.getLevel().setBlockAndUpdate(
                        event.getPos(),
                        block.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 2)
                );
                event.getLevel().playSound(null, event.getPos(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, event.getLevel().getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}
