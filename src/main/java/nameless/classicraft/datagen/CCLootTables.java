package nameless.classicraft.datagen;

import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class CCLootTables extends BaseLootTableProvider {

    public CCLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        simple(ModBlocks.FRIDGE);
        //simple(ModBlocks.UNLIT_TORCH);
        simple(ModBlocks.UNLIT_LANTERN);
        simple(ModBlocks.UNLIT_SOUL_TORCH);
        simple(ModBlocks.UNLIT_SOUL_LANTERN);
        simple(ModBlocks.WALL_UNLIT_TORCH);
        simple(ModBlocks.WALL_UNLIT_SOUL_TORCH);
        simple(ModBlocks.GLISTERING_MELON);
        simple(ModBlocks.SALT_ORE);
        LootPool.Builder builder = LootPool.lootPool()
                .name(ModBlocks.CACTUS_FRUIT.getId().toString())
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(ModBlocks.CACTUS_FRUIT.get().asItem()))
                .add(LootItem.lootTableItem(Items.CACTUS));

        for (RegistryObject<Block> UNLIT_CANDLEHOLDER: ModBlocks.UNLIT_CANDLEHOLDERS)
            simple(UNLIT_CANDLEHOLDER);
        for (RegistryObject<Block> UNLIT_LARGE_CANDLEHOLDER: ModBlocks.UNLIT_LARGE_CANDLEHOLDERS)
            simple(UNLIT_LARGE_CANDLEHOLDER);
        simple(ModBlocks.UNLIT_FIRE_BOWL);
        simple(ModBlocks.UNLIT_SOUL_FIRE_BOWL);
        simple(ModBlocks.UNLIT_LARGE_FIRE_BOWL);
        simple(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL);

        lootTables.put(ModBlocks.CACTUS_FRUIT.get(), LootTable.lootTable().withPool(builder));
    }

    void simple(RegistryObject<? extends Block> block) {
        lootTables.put(block.get(), createSimpleTable(block.getId().getPath(), block.get()));
    }
}