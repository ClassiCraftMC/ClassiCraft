package nameless.classicraft.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 更加安全的 NBT，封装以简化 nbt 判空等操作。<p>
 * 例子：
 * <pre>{@code
 *  CompoundTag a;
 *  ot = new SafeTag(a, mainKey);
 *  ot.getCompound("sub");
 *
 *  // 相当于:
 *
 *  if (a != null) {
 *      var mt = tag.get(mainKey);
 *
 *      if (mt.contains("sub", Tag.TAG_COMPOUND)) {
 *          mt.getCompound("sub");
 *      }
 *  }
 * }</pre>
 * @author DustW
 */
@SuppressWarnings("unchecked")
public class SafeTag implements Supplier<CompoundTag> {
    Supplier<CompoundTag> tagSupplier;
    String mainKey;

    public SafeTag(Supplier<CompoundTag> tagSupplier, String mainKey) {
        this.tagSupplier = tagSupplier;
        this.mainKey = mainKey;
    }

    public SafeTag(CompoundTag tag, String mainKey) {
        this(() -> tag, mainKey);
    }

    public SafeTag(ItemStack stack, String mainKey, boolean create) {
        this(create ? stack::getOrCreateTag : stack::getTag, mainKey);
    }

    public <T extends Tag> void put(String key, T value) {
        getOrCreateMainTag().ifPresent(t -> t.put(key, value));
    }

    public void putString(String key, String value) {
        put(key, StringTag.valueOf(value));
    }

    public <T extends Tag> Optional<T> getOrCreate(String key, int tagType, T defaultValue) {
        return getOrCreateMainTag().map(t -> {
            if (!t.contains(key, tagType))
                t.put(key, defaultValue);
            return (T) t.get(key);
        });
    }

    public Optional<ListTag> getOrCreateList(String key) {
        return getOrCreate(key, Tag.TAG_LIST, new ListTag());
    }

    public Optional<String> getOrCreateString(String key) {
        return getOrCreate(key, Tag.TAG_STRING, StringTag.valueOf("")).map(StringTag::getAsString);
    }

    public <T extends Tag> Optional<T> get(String key, int tagType) {
        return getMainTag().map(t -> t.contains(key, tagType) ? (T) t.get(key) : null);
    }

    public Optional<ListTag> getList(String key) {
        return get(key, Tag.TAG_LIST);
    }

    public Optional<String> getString(String key) {
        return this.<StringTag>get(key, Tag.TAG_STRING).map(StringTag::getAsString);
    }

    public Optional<CompoundTag> getMainTag() {
        CompoundTag tag = this.tagSupplier.get();

        if (tag != null && tag.contains(mainKey, Tag.TAG_COMPOUND)) {
            return Optional.of(tag.getCompound(mainKey));
        } else {
            return Optional.empty();
        }
    }

    @Nullable
    public CompoundTag createMainTag() {
        CompoundTag tag = tagSupplier.get();

        if (tag != null) {
            if (tag.contains(mainKey, Tag.TAG_COMPOUND))
                return tag.getCompound(mainKey);

            CompoundTag result = new CompoundTag();
            tag.put(mainKey, result);
            return result;
        }

        return null;
    }

    public Optional<CompoundTag> getOrCreateMainTag() {
        return Optional.ofNullable(getMainTag().orElse(createMainTag()));
    }

    @Override
    public CompoundTag get() {
        return getMainTag().orElse(null);
    }
}
