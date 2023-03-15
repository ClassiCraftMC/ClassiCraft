/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItemImpl;
import nameless.classicraft.item.*;
import nameless.classicraft.tier.DaggerTiers;
import nameless.classicraft.tier.HammerTiers;
import nameless.classicraft.tier.JavelinTiers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    ClassiCraftMod.MOD_ID);

    public static final RegistryObject<Item> TORCH_UNLIT =
            register("torch_unlit", UnlitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_UNLIT =
            register("soul_torch_unlit", UnlitSoulTorchItem::new);

    public static final RegistryObject<Item> TORCH_LIT =
            register("torch_lit", LitTorchItem::new);

    public static final RegistryObject<Item> SOUL_TORCH_LIT =
            register("soul_torch_lit", LitSoulTorchItem::new);

    public static final RegistryObject<Item> CLASSIC_CRAFT =
            register("classic_craft", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MATERIAL =
            register("material", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBUG_BURN_TIME_STICK =
            register("debug_burn_time_stick", DebugBurnTimeStick::new);

    public static final RegistryObject<Item> DEPTH_METER =
            register("depth_meter", DepthMeterItem::new);

    public static final RegistryObject<Item> TROUT =
            food("trout", Foods.COD);

    public static final RegistryObject<Item> COOKED_TROUT =
            food("cooked_trout", Foods.COOKED_COD);

    public static final RegistryObject<Item> TROUT_BUCKET =
            registerMobBuckteItem(ModEntities.TROUT_ENTITY);

    public static final RegistryObject<Item> OCEAN_SHARK_SPAWN_EGG =
            registerSpawnEgg(ModEntities.OCEAN_SHARK_ENTITY, 0x302521, 0xe4edf2);

    public static final RegistryObject<Item> TROUT_SPAWN_EGG =
            registerSpawnEgg(ModEntities.TROUT_ENTITY, 0x5a867c, 0x6b9f93);

    public static final RegistryObject<Item> GLARE_SPAWN_EGG =
            registerSpawnEgg(ModEntities.GLARE, 0x5a867c, 0x6b9f93);

    public static final RegistryObject<Item> TALLOW =
            register("tallow", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COOKED_EGG =
            food("cooked_egg", Foods.COOKED_COD);

    public static final RegistryObject<Item> NETHER_MUSHROOM_STEW =
            food("nether_mushroom_stew", Foods.MUSHROOM_STEW);

    public static final RegistryObject<Item> ROTTEN_FOOD =
            food("rotten_food", p -> p.food(new FoodProperties.Builder().build()));

    public static final RegistryObject<Item> SULFUR =
            register("sulfur", () -> new Item(new Item.Properties()));

    public static final RegistryObject<MetaItemImpl> PEBBLE = register("pebble", PebbleItem::new);

    public static final RegistryObject<MetaItemImpl> POINT =
            register("point", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_point",
                    "deepslate_point",
                    "flint_point",
                    "blackstone_point",
                    "quartz_point"
            )));

    public static final RegistryObject<MetaItemImpl> ADZE =
            register("adze", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_adze",
                    "deepslate_adze",
                    "flint_adze",
                    "blackstone_adze",
                    "quartz_adze"
            )));

    public static final RegistryObject<MetaItemImpl> SCRAPER =
            register("scraper", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_scraper",
                    "deepslate_scraper",
                    "flint_scraper",
                    "blackstone_scraper",
                    "quartz_scraper"
            )));

    public static final RegistryObject<MetaItemImpl> AWL =
            register("awl", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_awl",
                    "deepslate_awl",
                    "flint_awl",
                    "blackstone_awl",
                    "quartz_awl"
            )));

    public static final RegistryObject<MetaItemImpl> CHOPPER =
            register("chopper", () -> new PebbleToolItem(new Item.Properties(), List.of(
                    "cobblestone_chopper",
                    "deepslate_chopper",
                    "flint_chopper",
                    "blackstone_chopper",
                    "quartz_chopper"
            )));

    public static final RegistryObject<Item> CERAMIC_BOWL =
            register("ceramic_bowl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNFIRED_CLAY_BRICK =
            register("unfired_clay_brick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNFIRED_CLAY_BOWL =
            register("unfired_clay_bowl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNFIRED_CLAY_FLOWER_POT =
            register("unfired_clay_flower_pot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PUMPKIN_SLICE =
            food("pumpkin_slice", Foods.MELON_SLICE);

    public static final RegistryObject<Item> PHOBOS_OUTPOST_DISC =
            register("phobos_outpost_disc", () ->
                    new RecordItem(11, ModSounds.PHOBOS_OUTPOST, new Item.Properties().stacksTo(1), 238));

    public static final RegistryObject<Item> DRAGON_FISH_DISC =
            register("dragon_fish_disc", () ->
                    new RecordItem(11, ModSounds.DRAGON_FISH, new Item.Properties().stacksTo(1), 238));

    public static final RegistryObject<Item> MOSS_CLUMP =
            register("moss_clump", () -> new Item(new Item.Properties()));

    private static final List<Fluid> DENIED_FLUIDS_WOODEN =
            List.of(Fluids.LAVA);

    public static final RegistryObject<Item> CACTUS_FRUIT =
            register("cactus_fruit", () ->
                    new BlockItem(ModBlocks.CACTUS_FRUIT.get(),
                            new Item.Properties().food(Foods.SWEET_BERRIES)));

    public static final RegistryObject<Item> PLAINTIVE_SOUL =
            register("plaintive_soul", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> WITHER_BONE =
            register("wither_bone", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIVER_BONE =
            register("shiver_bone", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> FURIOUS_SOUL =
            register("furious_soul", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAREFREE_SOUL =
            register("carefree_soul", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> TERRIFIED_SOUL =
            register("terrified_soul", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITER =
            register("niter", () ->
                    new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOODEN_HAMMER =
            register("wooden_hammer", () ->
                    new HammerItem(HammerTiers.WOOD, 1, -3.2f,new Item.Properties()));

    public static final RegistryObject<Item> STONE_HAMMER =
            register("stone_hammer", () ->
                    new HammerItem(HammerTiers.STONE, 1, -3.2f,new Item.Properties()));

    public static final RegistryObject<Item> IRON_HAMMER =
            register("iron_hammer", () ->
                    new HammerItem(HammerTiers.IRON, 1, -3.2f,new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_HAMMER =
            register("golden_hammer", () ->
                    new HammerItem(HammerTiers.GOLD, 1, -3.2f,new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_HAMMER =
            register("diamond_hammer", () ->
                    new HammerItem(HammerTiers.DIAMOND, 1, -3.2f,new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_HAMMER =
            register("netherite_hammer", () ->
                    new HammerItem(HammerTiers.NETHERITE, 1, -3.2f,new
                            Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_DAGGER =
            register("stone_dagger", () ->
                    new DaggerItem(DaggerTiers.STONE, 1, -1.5f, new Item.Properties()));

    public static final RegistryObject<Item> WOODEN_DAGGER =
            register("wooden_dagger", () ->
                    new DaggerItem(DaggerTiers.WOOD, 1, -1.5f, new Item.Properties()));

    public static final RegistryObject<Item> IRON_DAGGER =
            register("iron_dagger", () ->
                    new DaggerItem(DaggerTiers.IRON, 1, -1.5f, new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_DAGGER =
            register("golden_dagger", () ->
                    new DaggerItem(DaggerTiers.GOLD, 1, -1.5f, new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_DAGGER =
            register("diamond_dagger", () ->
                    new DaggerItem(DaggerTiers.DIAMOND, 1, -1.5f, new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_DAGGER =
            register("netherite_dagger", () ->
                    new DaggerItem(DaggerTiers.NETHERITE, 1, -1.5f,
                            new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_JAVELIN =
           register("stone_javelin", () -> new JavelinItem(JavelinTiers.STONE, 4, -2.4F,
                   new Item.Properties(), "stone"));

    public static final RegistryObject<Item> WOODEN_JAVELIN =
            register("wooden_javelin", () -> new JavelinItem(JavelinTiers.WOOD, 4, -2.4F,
                    new Item.Properties(), "wooden"));

    public static final RegistryObject<Item> IRON_JAVELIN =
            register("iron_javelin", () -> new JavelinItem(JavelinTiers.IRON, 4, -2.4F,
                    new Item.Properties(), "iron"));

    public static final RegistryObject<Item> GOLDEN_JAVELIN =
            register("golden_javelin", () -> new JavelinItem(JavelinTiers.GOLD, 4, -2.4F,
                    new Item.Properties(), "golden"));

    public static final RegistryObject<Item> DIAMOND_JAVELIN =
            register("diamond_javelin", () -> new JavelinItem(JavelinTiers.DIAMOND, 4, -2.4F,
                    new Item.Properties(), "diamond"));

    public static final RegistryObject<Item> NETHERITE_JAVELIN =
            register("netherite_javelin", () -> new JavelinItem(JavelinTiers.NETHERITE, 4, -2.4F,
                    new Item.Properties().fireResistant(), "netherite"));


    private static RegistryObject<Item> food(String name, FoodProperties foodData) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(foodData)));
    }

    private static RegistryObject<Item> food(String name, Function<Item.Properties, Item.Properties> func) {
        return ITEMS.register(name, () -> new Item(func.apply(new Item.Properties())));
    }

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerSpawnEgg(RegistryObject<T> entity, int color1, int color2) {
        return register("spawn_egg/" + entity.getId().getPath(), () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
    }

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerMobBuckteItem(RegistryObject<T> entity) {
        return register(entity.getId().getPath() + "_bucket", () ->
                new MobBucketItem(entity,
                        () -> Fluids.WATER,
                        () -> SoundEvents.BUCKET_EMPTY_FISH,
                        new Item.Properties().stacksTo(1)));
    }

    /**
     * Used for registry items
     *
     * @param name Items' registry name
     * @param item Item Instance
     * @param <T>  sth extends Item
     * @return new RegistryObject<Item>
     */
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
