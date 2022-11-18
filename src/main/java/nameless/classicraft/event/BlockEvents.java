package nameless.classicraft.event;

import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import nameless.classicraft.api.light.LightAPI;
import nameless.classicraft.block.realistic.*;
import nameless.classicraft.init.ModBlocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void extinguishLargeFireBowlByPotion(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if ((projectile instanceof ThrownPotion && state.is(ModBlocks.LARGE_FIRE_BOWL.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.LARGE_FIRE_BOWL.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(LightAPI.LARGE_FIRE_BOWL_BURNTIME,
                                    LightAPI.LARGE_FIRE_BOWL_INITIAL_BURN_TIME)
                            .setValue(LightAPI.BE_HANGING,
                                    state.getValue(LightAPI.BE_HANGING))
                            .setValue(LightAPI.BE_WATERLOGGED,
                                    state.getValue(LightAPI.BE_WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
        if ((projectile instanceof ThrownPotion
                && state.is(ModBlocks.LARGE_SOUL_FIRE_BOWL.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.LARGE_SOUL_FIRE_BOWL.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(), 0)
                            .setValue(LightAPI.LARGE_FIRE_BOWL_BURNTIME,
                                    LightAPI.LARGE_FIRE_BOWL_INITIAL_BURN_TIME)
                            .setValue(LightAPI.BE_HANGING,
                                    state.getValue(LightAPI.BE_HANGING))
                            .setValue(LightAPI.BE_WATERLOGGED,
                                    state.getValue(LightAPI.BE_WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
    }

    @SubscribeEvent
    public static void extinguishFireBowlByPotion(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if ((projectile instanceof ThrownPotion && state.is(ModBlocks.FIRE_BOWL.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.FIRE_BOWL.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(LightAPI.FIRE_BOWL_BURNTIME,
                                    LightAPI.FIRE_BOWL_INITIAL_BURN_TIME)
                            .setValue(LightAPI.BE_HANGING,
                                    state.getValue(LightAPI.BE_HANGING))
                            .setValue(LightAPI.BE_WATERLOGGED,
                                    state.getValue(LightAPI.BE_WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
        if ((projectile instanceof ThrownPotion && state.is(ModBlocks.SOUL_FIRE_BOWL.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.SOUL_FIRE_BOWL.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(LightAPI.FIRE_BOWL_BURNTIME,
                                    LightAPI.FIRE_BOWL_INITIAL_BURN_TIME)
                            .setValue(LightAPI.BE_HANGING,
                                    state.getValue(LightAPI.BE_HANGING))
                            .setValue(LightAPI.BE_WATERLOGGED,
                                    state.getValue(LightAPI.BE_WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
    }

    @SubscribeEvent
    public static void extinguishLanternByPotion(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if ((projectile instanceof ThrownPotion && state.is(ModBlocks.LANTERN.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(RealisticLanternBlock.LANTERN_BURNTIME,
                                    RealisticLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticLanternBlock.HANGING,
                                    state.getValue(RealisticLanternBlock.HANGING))
                            .setValue(RealisticLanternBlock.WATERLOGGED,
                                    state.getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
        if ((projectile instanceof ThrownPotion
                && state.is(ModBlocks.SOUL_LANTERN.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.SOUL_LANTERN.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),0)
                            .setValue(RealisticLanternBlock.LANTERN_BURNTIME,
                                    RealisticLanternBlock.LANTERN_TOTAL_BURN_TIME)
                            .setValue(RealisticLanternBlock.HANGING,
                                    state.getValue(RealisticLanternBlock.HANGING))
                            .setValue(RealisticLanternBlock.WATERLOGGED,
                                    state.getValue(RealisticSoulLanternBlock.WATERLOGGED)));
            projectile.getLevel().updateNeighborsAt(projectile.getOnPos(), block);
        }
    }


    @SubscribeEvent
    public static void extinguishTorchByPotion(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        Level level = event.getProjectile().getLevel();
        if (projectile instanceof ThrownPotion
                && state.is(ModBlocks.TORCH.get())) {
                LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
                projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                        Blocks.AIR.defaultBlockState());
                ItemEntity newItem = new ItemEntity(
                        projectile.getLevel(),
                        projectile.getX(), projectile.getY(),
                        projectile.getZ(),
                        Items.STICK.getDefaultInstance());
                projectile.getLevel().addFreshEntity(newItem);
                level.updateNeighborsAt(event.getProjectile().getOnPos(), block);
                projectile.gameEvent(GameEvent.BLOCK_CHANGE);
        }
        if ((projectile instanceof ThrownPotion
                && state.is(ModBlocks.SOUL_TORCH.get()))) {
            LightAPI.playExtinguishSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    Blocks.AIR.defaultBlockState());
            ItemEntity newItem = new ItemEntity(
                    projectile.getLevel(),
                    projectile.getX(), projectile.getY(),
                    projectile.getZ(),
                    Items.STICK.getDefaultInstance());
            projectile.getLevel().addFreshEntity(newItem);
            level.updateNeighborsAt(event.getProjectile().getOnPos(), block);
            projectile.gameEvent(GameEvent.BLOCK_CHANGE);
        }
    }

    @SubscribeEvent
    public static void projectileFireOnTorch(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        HitResult result = event.getRayTraceResult();
        Block block = projectile.getBlockStateOn().getBlock();
        BlockState state = block.defaultBlockState();
        if (result.getType() == HitResult.Type.BLOCK
                && projectile.isOnFire()
                && state.is(ModBlocks.TORCH.get())
                && state.getValue(LightAPI.getLitState())
                != LightAPI.LIT) {
            LightAPI.playLightingSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.TORCH.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(),2)
                            .setValue(LightAPI.TORCH_BURNTIME,
                                    RealisticTorchBlock.getInitialBurnTime()));
        }
        if (result.getType() == HitResult.Type.BLOCK
                && projectile.isOnFire()
                && state.is(ModBlocks.SOUL_TORCH.get())
                && state.getValue(LightAPI.getLitState())
                != LightAPI.LIT) {
            LightAPI.playLightingSound(projectile.getLevel(), projectile.getOnPos());
            projectile.getLevel().setBlockAndUpdate(projectile.getOnPos(),
                    ModBlocks.SOUL_TORCH.get().defaultBlockState()
                            .setValue(LightAPI.getLitState(), 2)
                            .setValue(LightAPI.TORCH_BURNTIME,
                                    RealisticTorchBlock.getInitialBurnTime()));
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
                LightAPI.playExtinguishSound(event.getLevel(), event.getPos());
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
                LightAPI.playExtinguishSound(event.getLevel(), event.getPos());
            }
            if (block instanceof RealisticSoulLanternBlock) {
                event.getLevel().setBlockAndUpdate(event.getPos(), ModBlocks.SOUL_LANTERN.get().defaultBlockState());
                event.getLevel().updateNeighborsAt(event.getPos(), block);
                LightAPI.playExtinguishSound(event.getLevel(), event.getPos());
            }
        }
    }
}
