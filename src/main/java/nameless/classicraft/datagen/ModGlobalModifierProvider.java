package nameless.classicraft.datagen;

import nameless.classicraft.glm.ReplaceDropModifier;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

/**
 * @author DustW
 */
public class ModGlobalModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        replaceBlock(Blocks.STONE, ModItems.PEBBLE.get(), "cobblestone_pebble", 1, 4);
        pebble(Blocks.ANDESITE);
        pebble(Blocks.DIORITE);
        pebble(Blocks.RED_SANDSTONE);
        pebble(Blocks.SANDSTONE);
        pebble(Blocks.DEEPSLATE);
        pebble(Blocks.COBBLED_DEEPSLATE);
        pebble(Blocks.BASALT);
        pebble(Blocks.GRANITE);
        pebble(Blocks.BLACKSTONE);

        replaceBlock(Blocks.COARSE_DIRT, Items.FLINT, 1);
    }

    void pebble(Block block) {
        replaceBlock(block, ModItems.PEBBLE.get(),
                ForgeRegistries.BLOCKS.getKey(block).getPath() + "_pebble", 1, 4);
    }

    void replaceBlock(Block block, Item item, String meta, int min, int max) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, meta, min, max));
    }

    void replaceBlock(Block block, Item item, String meta, int count) {
        add("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(),
                new ReplaceDropModifier(new LootItemCondition[]{
                        new LootItemBlockStatePropertyCondition.Builder(block).build()
                }, item, meta, count));
    }

    void replaceBlock(Block block, Item item, int count) {
        replaceBlock(block, item, "", count);
    }
}
