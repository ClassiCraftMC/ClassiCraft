package nameless.classicraft.glm;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import nameless.classicraft.api.item.MetaItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 */
public class ReplaceDropModifier extends LootModifier {
    public static Codec<ReplaceDropModifier> createCodec() {
        return RecordCodecBuilder.create(inst -> codecStart(inst).and(
                inst.group(
                        ForgeRegistries.ITEMS.getCodec().fieldOf("drop").forGetter(m -> m.drop),
                        Codec.STRING.fieldOf("meta").forGetter(m -> m.meta),
                        Codec.INT.fieldOf("probability").forGetter(m -> m.probability),
                        Codec.INT.fieldOf("min").forGetter(m -> m.min),
                        Codec.INT.fieldOf("max").forGetter(m -> m.max)
                )).apply(inst, ReplaceDropModifier::new));
    }

    Item drop;
    String meta;
    int probability;
    int min;
    int max;

    public ReplaceDropModifier(LootItemCondition[] conditionsIn, Item drop, String meta, int probability, int min, int max) {
        super(conditionsIn);
        this.drop = drop;
        this.meta = meta;
        this.probability = probability;
        this.min = min;
        this.max = max;
    }

    public ReplaceDropModifier(LootItemCondition[] conditionsIn, Item drop, String meta, int min, int max) {
        this(conditionsIn, drop, meta, 100, min, max);
    }

    public ReplaceDropModifier(LootItemCondition[] conditionsIn, Item drop, String meta, int count) {
        this(conditionsIn, drop, meta, 100, count, count);
    }

    @NotNull
    @Override
    public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextInt(100) < probability) {
            generatedLoot.clear();
            ItemStack item = new ItemStack(drop, max - min > 0 ? context.getRandom().nextInt(max - min + 1) + min : min);

            if (meta != null && !meta.isEmpty())
                MetaItem.setMeta(item, meta);

            generatedLoot.add(item);
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return ModLootModifiers.REPLACE_DROP.get();
    }
}
