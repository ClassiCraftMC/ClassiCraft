package nameless.classicraft.event;

import com.mojang.datafixers.util.Pair;
import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.ClassiCraftHooks;
import nameless.classicraft.api.event.ItemEntityTickEvent;
import nameless.classicraft.api.event.PlayerRightClickBlockEvent;
import nameless.classicraft.capability.ModCapabilities;
import nameless.classicraft.entity.RanchuEntity;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class ClassiCraftSubcriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ClassiCraftSubcriber::onPlayerUsingItem);
        bus.addListener(ClassiCraftSubcriber::onPlayerEatingFoods);
        bus.addListener(ClassiCraftSubcriber::addFuelBurn);
        bus.addListener(ClassiCraftSubcriber::stopBlockPlace);
        bus.addListener(ClassiCraftSubcriber::onScreenLoad);
        bus.addListener(ClassiCraftSubcriber::onRanchuBreed);
        bus.addListener(ClassiCraftSubcriber::onCraftTorch);
        bus.addListener(ClassiCraftSubcriber::onItemInWater);
        bus.addListener(ClassiCraftSubcriber::onItemInRaining);
        bus.addListener(ClassiCraftSubcriber::onRightClickWater);
        bus.addListener(ClassiCraftSubcriber::onDamageSquid);
        bus.addListener(ClassiCraftSubcriber::onItemTicking);
        bus.addListener(ClassiCraftSubcriber::addTooltip);
    }

    public static void addTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.isEdible()) {
            ClassiCraftHooks.addFoodComponentEffectTooltip(itemStack, event.getToolTip());
        }
    }

    public static void onDamageSquid(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity attackedEntity = entity.getLastHurtByMob();
        if (entity instanceof Squid
                && attackedEntity!= null
                && entity.isInWater()
                && attackedEntity.isInWater()
                && entity.getLastDamageSource() != null
                && !entity.getLastDamageSource().isProjectile()
                && ClassiCraftConfiguration.enableSquidBlind.get()) {
            attackedEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 70, 1));
        }
    }

    public static void onRightClickWater(PlayerRightClickBlockEvent event) {
        Level level = event.getLevel();
        ItemStack itemStack = event.getEntity().getItemInHand(event.getHand());
        Holder<Biome> biome = level.getBiome(event.getPos());
        Block block = event.getBlock();
        if (event.getEntity() == null) {
            return;
        }
        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }
        if (event.getEntity().swinging) {
            return;
        }

        if (biome.is(Biomes.OCEAN)
                || biome.is(Biomes.COLD_OCEAN)
                || biome.is(Biomes.DEEP_COLD_OCEAN)
                || biome.is(Biomes.DEEP_OCEAN)
                || biome.is(Biomes.FROZEN_OCEAN)
                || biome.is(Biomes.DEEP_FROZEN_OCEAN)
                || biome.is(Biomes.LUKEWARM_OCEAN)
                || biome.is(Biomes.DEEP_LUKEWARM_OCEAN)
                || biome.is(Biomes.WARM_OCEAN)
                && block.defaultBlockState().is(Blocks.WATER)) {
            if (itemStack.is(Items.BUCKET)) {
                ItemStack newItemStack = new ItemStack(ModItems.SALT_WATER_BOTTLE.get());
                event.getEntity().getInventory().add(newItemStack);
                newItemStack.grow(1);
            }
        }
    }

    public static void onItemTicking(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getItem();
        if (ClassiCraftConfiguration.enableEntityTorchBurnOut.get()) {
        if (itemStack.is(ModItems.LIT_TORCH.get())
                && itemEntity.getAge()
                == ClassiCraftConfiguration.torchEntityBurnOutTime.get() * 1200) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        Items.STICK.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                itemEntity.getLevel().addFreshEntity(newItem);
            }
            if (itemStack.is(ModItems.LIT_SOUL_TORCH.get())
                    && itemEntity.getAge()
                    == ClassiCraftConfiguration.torchEntityBurnOutTime.get() * 1200) {
                int oldCount = itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.KILLED);
                ItemEntity newItem = new ItemEntity(
                        itemEntity.getLevel(),
                        itemEntity.getX(), itemEntity.getY(),
                        itemEntity.getZ(),
                        Items.STICK.getDefaultInstance());
                newItem.getItem().setCount(oldCount);
                itemEntity.getLevel().addFreshEntity(newItem);
            }
        }
    }

    public static void onItemInRaining(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        Level level = itemEntity.getLevel();
        if (itemEntity.getItem().is(ModItems.LIT_TORCH.get()) && level.isRainingAt(itemEntity.getOnPos().above())) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
        if (itemEntity.getItem().is(ModItems.LIT_SOUL_TORCH.get()) && level.isRainingAt(itemEntity.getOnPos().above())) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
    }

    public static void onItemInWater(ItemEntityTickEvent event) {
        ItemEntity itemEntity = event.getEntity();
        if (itemEntity.getItem().is(ModItems.LIT_TORCH.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
        if (itemEntity.getItem().is(ModItems.LIT_SOUL_TORCH.get()) && itemEntity.isInWater()) {
            int oldCount = itemEntity.getItem().getCount();
            itemEntity.remove(Entity.RemovalReason.KILLED);
            ItemEntity newItem = new ItemEntity(
                    itemEntity.getLevel(),
                    itemEntity.getX(), itemEntity.getY(),
                    itemEntity.getZ(),
                    Items.STICK.getDefaultInstance());
            newItem.getItem().setCount(oldCount);
            itemEntity.getLevel().addFreshEntity(newItem);
        }
    }

    public static void onCraftTorch(PlayerEvent.ItemCraftedEvent event) {
        ItemStack itemStack = event.getCrafting();
        if (itemStack.is(ModItems.LIT_TORCH.get()) || itemStack.is(ModItems.LIT_SOUL_TORCH.get())) {
            ItemStack returnStack = new ItemStack(Items.FLINT_AND_STEEL);
            int origin = returnStack.getDamageValue();
            returnStack.setDamageValue(origin + 1);
            event.getEntity().getInventory().add(returnStack);
            returnStack.grow(1);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void onScreenLoad(ScreenEvent.Init.Post e) {
        if (!(e.getScreen() instanceof PauseScreen pauseScreen) || !pauseScreen.showPauseMenu) return;

        if (ModList.get().isLoaded("bettergamemenu")) return;
        if (ClassiCraftConfiguration.removeSendFeedbackAndReportBugs.get()) {
            Button feedback = discoverButton(e.getListenersList(), "menu.sendFeedback");
            Button reportbugs = discoverButton(e.getListenersList(), "menu.reportBugs");

            if (feedback != null)
                e.removeListener(feedback);
            if (reportbugs != null)
                e.removeListener(reportbugs);
        }
    }

    private static void onRanchuBreed(BabyEntitySpawnEvent event) {
        if (event.getParentA() instanceof RanchuEntity && event.getParentB() instanceof RanchuEntity) {
            RanchuEntity ranchuA = (RanchuEntity) event.getParentA();
            RanchuEntity ranchuB = (RanchuEntity) event.getParentB();
            RanchuEntity child = (RanchuEntity) event.getChild();
            RandomSource rand = ranchuA.getRandom();

            // Feral + Feral
            if (ranchuA.getVariant() <= 2 && ranchuB.getVariant() <= 2) {
                if (rand.nextFloat() < 0.15) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                } else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            // Fancy + Fancy
            else if (ranchuA.getVariant() > 2 && ranchuB.getVariant() > 2) {
                child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
            }

            // Feral + Fancy
            else if (ranchuA.getVariant() <= 2 || ranchuB.getVariant() <= 2 && ranchuA.getVariant() > 2 || ranchuB.getVariant() > 2) {
                if (rand.nextBoolean()) {
                    child.setVariant(rand.nextInt(RanchuEntity.MAX_VARIANTS - 3) + 3);
                } else {
                    child.setVariant(rand.nextInt(3) + 1);
                }
            }

            child.copyPosition(ranchuA);
            child.setBaby(true);
            ranchuA.getCommandSenderWorld().addFreshEntity(child);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static Button discoverButton(List<GuiEventListener> listeners, String name) {
        for (GuiEventListener listener : listeners) {
            if (listener instanceof Button button && button.getMessage() instanceof MutableComponent mutableComponent
                    && mutableComponent.getContents() instanceof TranslatableContents translatableContents) {
                if (translatableContents.getKey().equals(name))
                    return button;
            }
        }
        return null;
    }

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
        if (entity instanceof Player
                && block instanceof LanternBlock
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

    public static void addFuelBurn(FurnaceFuelBurnTimeEvent event) {
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.OAK_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.ACACIA_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.AZALEA_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.BIRCH_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.DARK_OAK_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.JUNGLE_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.SPRUCE_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.MANGROVE_LEAVES);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.OAK_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.ACACIA_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.AZALEA);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.BIRCH_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.DARK_OAK_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.JUNGLE_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.SPRUCE_SAPLING);
        ClassiCraftHooks.handleWoodenItemBurnTime(event, Items.MANGROVE_ROOTS);
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
