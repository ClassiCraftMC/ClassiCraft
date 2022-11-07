package nameless.classicraft.event;

import com.mojang.datafixers.util.Pair;
import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.capability.ModCapabilities;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClassiCraftSubcriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ClassiCraftSubcriber::onPlayerUsingItem);
        bus.addListener(ClassiCraftSubcriber::onPlayerEatingFoods);
        bus.addListener(ClassiCraftSubcriber::addFuelBurn);
        bus.addListener(ClassiCraftSubcriber::stopTorchBlockPlace);
    }

    public static void stopTorchBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        Block block = event.getPlacedBlock().getBlock();
        ItemStack heldStack = block.asItem().getDefaultInstance();
        LevelAccessor level = event.getLevel();
        if (entity instanceof Player && block instanceof TorchBlock && ClassiCraftConfiguration.noVanillaTorchPlace.get()) {
            if (!((Player) entity).isCreative()) {
                level.playSound(null, event.getPos(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1, 1);
                level.setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 1);
                entity.sendSystemMessage(Component.translatable("info.classicraft.stop_use_torch"));
                heldStack.split(1);
            }
        }
    }

    public static void addFuelBurn(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == Items.OAK_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.ACACIA_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.AZALEA_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.BIRCH_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.DARK_OAK_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.JUNGLE_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.SPRUCE_LEAVES)
            event.setBurnTime(1600);
        if (itemstack.getItem() == Items.MANGROVE_LEAVES)
            event.setBurnTime(1600);
    }

    public static void onPlayerUsingItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.getLevel();
        ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
        if (itemStack.is(Items.EGG)) {
            if (player.isShiftKeyDown()) {
                float pitch = 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, pitch);
                if (!level.isClientSide) {
                    ThrownEgg thrownEgg = new ThrownEgg(level, player);
                    thrownEgg.setItem(itemStack);
                    thrownEgg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                    level.addFreshEntity(thrownEgg);
                }

                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                player.swing(player.getUsedItemHand());
                event.setCanceled(true);
            } else {
                if (player.getFoodData().needsFood()) {
                    player.startUsingItem(player.getUsedItemHand());
                }
                event.setCanceled(true);
            }
        }
        if (itemStack.is(Items.CAKE)) {
            event.setCanceled(true);
        }
        if (itemStack.is(Items.TURTLE_EGG) && player.isShiftKeyDown()) {
            float pitch = 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, pitch);
            if (!level.isClientSide) {
                ThrownEgg thrownEgg = new ThrownEgg(level, player) {
                    @Override
                    protected void onHit(HitResult result) {
                        HitResult.Type hitResult$type = result.getType();
                        if (hitResult$type == HitResult.Type.ENTITY) {
                            this.onHitEntity((EntityHitResult)result);
                        } else if (hitResult$type == HitResult.Type.BLOCK) {
                            this.onHitBlock((BlockHitResult)result);
                        }

                        if (hitResult$type != HitResult.Type.MISS) {
                            this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
                        }

                        if (!this.level.isClientSide) {
                            if (this.random.nextInt(8) == 0) {
                                int i = 1;
                                if (this.random.nextInt(32) == 0) {
                                    i = 4;
                                }

                                for(int j = 0; j < i; ++j) {
                                    Turtle turtle = EntityType.TURTLE.create(this.level);
                                    if (turtle != null) {
                                        turtle.setAge(-24000);
                                        turtle.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                                        this.level.addFreshEntity(turtle);
                                    }
                                }
                            }

                            this.level.broadcastEntityEvent(this, (byte)3);
                            this.discard();
                        }
                    }
                };
                thrownEgg.setItem(itemStack);
                thrownEgg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(thrownEgg);
            }

            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            player.swing(player.getUsedItemHand());
            event.setCanceled(true);
        }
    }

    public static void onPlayerEatingFoods(LivingEntityUseItemEvent.Finish event) {
        ItemStack itemStack = event.getResultStack();
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (itemStack.is(Items.GLISTERING_MELON_SLICE)) {
                player.heal(4.0F);
            } else if (itemStack.is(ModBlocks.GLISTERING_MELON.get().asItem())) {
                player.heal(8.0F);
            } else if (itemStack.is(Items.GOLDEN_CARROT)) {
                entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 80, 0));
            }
        }

        itemStack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            FoodProperties properties = itemStack.getFoodProperties(entity);
            if (properties != null) {
                if (!entity.level.isClientSide) {
                    for (Pair<MobEffectInstance, Float> pair : properties.getEffects()) {
                        if (pair.getFirst() != null && entity.level.random.nextFloat() < pair.getSecond()){
                            entity.addEffect(new MobEffectInstance(pair.getFirst()));
                        }
                    }

                    int foodLevelModifier;
                    float saturationLevelModifier;
                    if (itemStack.getItem() == Items.ROTTEN_FLESH || itemStack.getItem() == ModItems.ROTTEN_FOOD.get()) {
                        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 45 * 20, 2));
                        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 45 * 20, 1));
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 45 * 20, 0));
                        foodLevelModifier = 0;
                        saturationLevelModifier = 0;
                    } else {
                        switch (rot.getLevel()) {
                            case FRESH -> {
                                foodLevelModifier = properties.getNutrition();
                                saturationLevelModifier = properties.getSaturationModifier();
                            }
                            case STALE -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .75);
                                saturationLevelModifier = properties.getSaturationModifier() * .75F;
                            }
                            case SPOILED -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 30 * 20, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 30 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .5);
                                saturationLevelModifier = properties.getSaturationModifier() * .5F;
                            }
                            case ROT -> {
                                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 45 * 20, 2));
                                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 45 * 20, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 45 * 20));
                                foodLevelModifier = (int) (properties.getNutrition() * .25);
                                saturationLevelModifier = properties.getSaturationModifier() * .25F;
                            }
                            default -> {
                                foodLevelModifier = 0;
                                saturationLevelModifier = 0;
                            }
                        }
                    }

                    if (entity instanceof Player player && foodLevelModifier != 0 && saturationLevelModifier != 0) {
                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - properties.getNutrition());
                        player.getFoodData().eat(foodLevelModifier, saturationLevelModifier);
                    }
                }

                entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F,
                        entity.level.random.nextFloat() * 0.1F + 0.9F);

                entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        entity.getEatingSound(itemStack), SoundSource.NEUTRAL,
                        1.0F, 1.0F + (entity.level.random.nextFloat()
                                - entity.level.random.nextFloat()) * 0.4F);
            }
        });
    }
}
