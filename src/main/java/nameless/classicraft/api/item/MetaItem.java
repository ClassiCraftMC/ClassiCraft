package nameless.classicraft.api.item;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.util.SafeTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 代表一类有重复性的物品<p></p>
 * 会自动注册模型 (MetaModelRegistry)<p></p>
 *
 * @author DustW
 * @see nameless.classicraft.client.meta.MetaModelRegistry
 */
public class MetaItem extends Item {
    private static final List<MetaItem> META_ITEMS = new ArrayList<>();

    public static List<MetaItem> getMetaItems() {
        return META_ITEMS;
    }

    public final List<String> metas;

    public MetaItem(Properties pProperties, List<String> metas) {
        super(pProperties);

        this.metas = metas;

        META_ITEMS.add(this);
    }

    public static String getMeta(ItemStack stack) {
        return new SafeTag(stack, ClassiCraftMod.MOD_ID, false).getString("Meta").orElse(null);
    }

    public static void setMeta(ItemStack stack, String meta) {
        new SafeTag(stack, ClassiCraftMod.MOD_ID, true).putString("Meta", meta);
    }

    public static boolean equals(ItemStack i1, ItemStack i2) {
        return i1.getItem() == i2.getItem() && Objects.equals(getMeta(i1), getMeta(i2));
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack pStack) {
        return this.getDescriptionId() + "." + getMeta(pStack);
    }

    /**
     * 将每个 meta 对应的物品注册到创造模式物品栏
     *
     * @param output output
     */
    public void acceptToCreativeModeTab(CreativeModeTab.Output output) {
        getMetaItemStacks().forEach(output::accept);
    }

    public List<ItemStack> getMetaItemStacks() {
        List<ItemStack> result = new ArrayList<>();
        ItemStack itemStack = new ItemStack(this);

        for (String meta : metas) {
            setMeta(itemStack, meta);
            result.add(itemStack.copy());
        }

        return result;
    }

    public ResourceLocation metaResLoc(ItemStack stack) {
        return metaResLoc(getMeta(stack));
    }

    public ResourceLocation metaResLoc(String meta) {
        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this));
        return new ResourceLocation(key.getNamespace(), key.getPath() + "/" + meta);
    }
}
