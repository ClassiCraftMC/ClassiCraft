package nameless.classicraft.util.sync.primitive;

import nameless.classicraft.util.sync.SyncData;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;

/**
 * @author DustW
 **/
public class FloatSyncData extends SyncData<Float> {
    public FloatSyncData(String name, Float defaultValue, boolean needSave) {
        super(name, defaultValue, needSave);
    }

    @Override
    protected Tag toTag() {
        return FloatTag.valueOf(get());
    }

    @Override
    protected Float fromTag(Tag tag) {
        return ((FloatTag) tag).getAsFloat();
    }
}

