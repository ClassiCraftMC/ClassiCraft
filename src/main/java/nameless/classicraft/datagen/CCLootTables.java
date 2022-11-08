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
        LootPool.Builder builder = LootPool.lootPool()
                .name(ModBlocks.CACTUS_FRUIT.getId().toString())
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModBlocks.CACTUS_FRUIT.get().asItem()))
                .add(LootItem.lootTableItem(Items.CACTUS));

        for (RegistryObject<Block> UNLIT_CANDLEHOLDER: ModBlocks.UNLIT_CANDLEHOLDERS)
            simple(UNLIT_CANDLEHOLDER);
        for (RegistryObject<Block> UNLIT_LARGE_CANDLEHOLDER: ModBlocks.UNLIT_LARGE_CANDLEHOLDERS)
            simple(UNLIT_LARGE_CANDLEHOLDER);

        lootTables.put(ModBlocks.CACTUS_FRUIT.get(), LootTable.lootTable().withPool(builder));
    }

    void simple(RegistryObject<? extends Block> block) {
        lootTables.put(block.get(), createSimpleTable(block.getId().getPath(), block.get()));
    }
}