package nameless.classicraft.api.item;

import nameless.classicraft.init.ModCapabilities;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public interface CCItemStack extends IForgeItemStack {

    @Nullable
    @Override
    default NbtCompound getShareTag() {
        NbtCompound superValue = IForgeItemStack.super.getShareTag();

        if (superValue == null) {
            superValue = new NbtCompound();
        }

        NbtCompound returnValue = superValue;

        getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            returnValue.putFloat("final_speed", rot.getFinalSpeed());
            returnValue.putFloat("rot", rot.getRotValue());
        });

        return returnValue;
    }

    @Override
    default void readShareTag(@Nullable NbtCompound nbt) {
        IForgeItemStack.super.readShareTag(nbt);

        if (nbt != null && nbt.contains("rot")) {
            getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                rot.setFinalSpeed(nbt.getFloat("final_speed"));
                rot.setRotValue(nbt.getFloat("rot"));
            });
        }
    }
}
