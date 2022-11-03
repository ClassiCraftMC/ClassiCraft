package nameless.classicraft.util.sync.primitive;

import nameless.classicraft.util.sync.SyncData;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

/**
 * @author DustW
 **/
public class IntSyncData extends SyncData<Integer> {
    public IntSyncData(String name, Integer defaultValue, boolean needSave) {
        super(name, defaultValue, needSave);
    }

    @Override
    protected IntTag toTag() {
        return IntTag.valueOf(get());
    }

    @Override
    protected Integer fromTag(Tag tag) {
        return ((IntTag) tag).getAsInt();
    }

    public int reduce(int value) {
        this.set(get() - value);
        return get();
    }

    public int reduce(int value, int min) {
        this.set(Math.max(get() - value, min));
        return get();
    }

    public int plus(int value) {
        this.set(get() + value);
        return get();
    }

    public int plus(int value, int max) {
        this.set(Math.min(get() + value, max));
        return get();
    }
}