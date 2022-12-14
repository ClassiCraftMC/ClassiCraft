package nameless.classicraft.datagen.loot;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.item.PebbleItem;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DustW
 */
public class ModBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

    protected ModBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(ModBlocks.TWIGS.get(), createSingleItemTable(Items.STICK));

        addPebble(ModBlocks.ANDESITE_PEBBLE.get());
        addPebble(ModBlocks.COBBLESTONE_PEBBLE.get());
        addPebble(ModBlocks.DIORITE_PEBBLE.get());
        addPebble(ModBlocks.GRANITE_PEBBLE.get());
        addPebble(ModBlocks.RED_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.DEEPSLATE_PEBBLE.get());
        addPebble(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.SOUL_SANDSTONE_PEBBLE.get());
        addPebble(ModBlocks.NETHERRACK_PEBBLE.get());
        addPebble(ModBlocks.END_STONE_PEBBLE.get());
        addPebble(ModBlocks.BLACKSTONE_PEBBLE.get());
        addPebble(ModBlocks.BASALT_PEBBLE.get());
        addPebble(ModBlocks.QUARTZ_PEBBLE.get());
        addVanillaPebble(ModBlocks.FLINT.get(), Items.FLINT);
        addVanillaPebble(ModBlocks.PRISMARINE.get(), Items.PRISMARINE_SHARD);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_249322_) {
        generate();

        map.forEach(p_249322_);
    }

    void addPebble(Block block) {
        add(block, createSingleItemTable(ModItems.PEBBLE.get())
                .apply(SetNbtFunction.setTag(PebbleItem.getTagFrom(block))));
    }

    void addVanillaPebble(Block block,Item item) {
        add(block, createSingleItemTable(item));
    }
}
