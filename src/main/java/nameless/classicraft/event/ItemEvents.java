package nameless.classicraft.event;

import com.mojang.datafixers.util.Pair;
import nameless.classicraft.ClassiCraftConfiguration;
import nameless.classicraft.ClassiCraftHooks;
import nameless.classicraft.capability.ModCapabilities;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ItemEvents {

    @SubscribeEvent
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

    @SubscribeEvent
    public static void shiftLantern(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        if (itemStack.is(ModItems.LIT_LANTERN.get())) {
            if (player.isShiftKeyDown()
                    && itemStack.getItem()!= player.getOffhandItem().getItem()) {
                event.getLevel().playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, event.getLevel().getRandom().nextFloat() * 0.1F + 0.9F);
                ItemStack newItem = new ItemStack(ModItems.LANTERN.get());
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
        }
        if (itemStack.is(ModItems.LIT_SOUL_LANTERN.get())) {
            if (player.isShiftKeyDown()
                    && itemStack.getItem() != player.getOffhandItem().getItem()) {
                event.getLevel().playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, event.getLevel().getRandom().nextFloat() * 0.1F + 0.9F);
                ItemStack newItem = new ItemStack(ModItems.SOUL_LANTERN.get());
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
        }
    }

    @SubscribeEvent
    public static void shiftTorch(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        if (itemStack.is(ModItems.LIT_TORCH.get()) || itemStack.is(ModItems.LIT_SOUL_TORCH.get())) {
            if (player.isShiftKeyDown()
                    && itemStack.getItem()!= player.getOffhandItem().getItem()) {
                event.getLevel().playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, event.getLevel().getRandom().nextFloat() * 0.1F + 0.9F);
                ItemStack newItem = new ItemStack(Items.STICK);
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
        }
    }

    @SubscribeEvent
    public static void changeTorch(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player != null) {
            ItemStack firstItem = player.getOffhandItem();
            ItemStack itemStack = event.getItemStack();
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            if (itemStack.is(Items.FLINT_AND_STEEL)
                    && firstItem.is(ModItems.TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamageValue(1);
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                int oldCount = firstItem.getCount();
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.TORCH.get())
                    && firstItem.is(Items.FLINT_AND_STEEL)
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamageValue(1);
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(Items.FLINT_AND_STEEL)
                    && firstItem.is(ModItems.SOUL_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamageValue(1);
                ItemStack newItem = new ItemStack(ModItems.LIT_SOUL_TORCH.get());
                int oldCount = firstItem.getCount();
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.SOUL_TORCH.get())
                    && firstItem.is(Items.FLINT_AND_STEEL)
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                itemStack.setDamageValue(1);
                ItemStack newItem = new ItemStack(ModItems.LIT_SOUL_TORCH.get());
                int oldCount = itemStack.getCount();
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.LIT_TORCH.get())
                    && firstItem.is(ModItems.TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = firstItem.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.LIT_TORCH.get())
                    && firstItem.is(ModItems.SOUL_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = firstItem.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.LIT_SOUL_TORCH.get())
                    && firstItem.is(ModItems.TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = firstItem.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.LIT_SOUL_TORCH.get())
                    && firstItem.is(ModItems.SOUL_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = firstItem.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(firstItem);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.TORCH.get())
                    && firstItem.is(ModItems.LIT_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = itemStack.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.SOUL_TORCH.get())
                    && firstItem.is(ModItems.LIT_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = itemStack.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.TORCH.get())
                    && firstItem.is(ModItems.LIT_SOUL_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = itemStack.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
            if (itemStack.is(ModItems.SOUL_TORCH.get())
                    && firstItem.is(ModItems.LIT_SOUL_TORCH.get())
                    && !event.getLevel().isRainingAt(event.getPos().above(2))) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                int oldCount = itemStack.getCount();
                ItemStack newItem = new ItemStack(ModItems.LIT_TORCH.get());
                player.getInventory().removeItem(itemStack);
                newItem.setCount(oldCount);
                player.getInventory().add(newItem);
            }
        }
    }

    @SubscribeEvent
    public static void addTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        Item food = itemStack.getItem();
        FoodProperties foodData = food.getFoodProperties();
        Player player = event.getEntity();
        if (itemStack.isEdible()) {
            toolTip.add(Component.translatable("按下shift键显示更多信息").withStyle(ChatFormatting.WHITE));
            if (player != null && player.isShiftKeyDown()) {
                if (ClassiCraftConfiguration.enableShowFoodEffect.get()) {
                    ClassiCraftHooks.addFoodComponentEffectTooltip(itemStack, event.getToolTip());
                }
                if (foodData != null) {
                    int nutrition = foodData.getNutrition();
                    float satur = foodData.getSaturationModifier();
                    toolTip.add(Component.translatable("营养价值:" + nutrition).withStyle(ChatFormatting.GREEN));
                    toolTip.add(Component.translatable("恢复价值:" + satur).withStyle(ChatFormatting.GOLD));
                }
            }
        }
    }

    @SubscribeEvent
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

    @SubscribeEvent
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
