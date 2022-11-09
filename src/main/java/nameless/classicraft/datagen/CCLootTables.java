package nameless.classicraft.datagen;

import nameless.classicraft.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class CCLootTables extends BaseLootTableProvider {

    public CCLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        simple(ModBlocks.FRIDGE);
        simple(ModBlocks.GLISTERING_MELON);
        simple(ModBlocks.SALT_ORE);
        simple(ModBlocks.TORCH);
        simple(ModBlocks.SOUL_TORCH);
        simple(ModBlocks.LANTERN);
        simple(ModBlocks.SOUL_LANTERN);
        simple(ModBlocks.FIRE_BOWL);
        simple(ModBlocks.SOUL_FIRE_BOWL);
        simple(ModBlocks.LARGE_FIRE_BOWL);
        simple(ModBlocks.LARGE_SOUL_FIRE_BOWL);
        simple(ModBlocks.SALT_BLOCK);
        simple(ModBlocks.SALT_ROCK_BLOCK);
        simple(ModBlocks.TORCH);
        simple(ModBlocks.WALL_TORCH);
        simple(ModBlocks.SOUL_TORCH);
        simple(ModBlocks.SOUL_WALL_TORCH);
        simple(ModBlocks.LANTERN);
        simple(ModBlocks.SOUL_LANTERN);
        LootPool.Builder builder = LootPool.lootPool()
                .name(ModBlocks.CACTUS_FRUIT.getId().toString())
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModBlocks.CACTUS_FRUIT.get().asItem()))
                .add(LootItem.lootTableItem(Items.CACTUS));

        lootTables.put(ModBlocks.CACTUS_FRUIT.get(), LootTable.lootTable().withPool(builder));
    }

    void simple(RegistryObject<? extends Block> block) {
        lootTables.put(block.get(), createSimpleTable(block.getId().getPath(), block.get()));
    }
}