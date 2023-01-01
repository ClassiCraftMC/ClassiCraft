package nameless.classicraft.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * @author DustW
 */
public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), subProviderEntries());
    }

    static List<SubProviderEntry> subProviderEntries() {
        return List.of(
                new SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK)
        );
    }
}
