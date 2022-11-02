package nameless.classicraft.common.sync.primitive;

import nameless.classicraft.common.sync.SyncData;
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

