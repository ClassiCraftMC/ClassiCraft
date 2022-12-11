package nameless.classicraft.capability.rot;

import nameless.classicraft.init.ModCapabilities;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class RotCapabilityProvider extends CapabilityProvider<RotCapabilityProvider> implements INBTSerializable<NbtCompound> {

    LazyOptional<AbstractRot> cap;

    public RotCapabilityProvider(LazyOptional<AbstractRot> cap) {
        super(RotCapabilityProvider.class);

        this.cap = cap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.ROT.orEmpty(cap, this.cap.cast());
    }

    @Override
    public NbtCompound serializeNBT() {
        return cap.map(abstractRot -> {
            NbtCompound compoundTag = new NbtCompound();
            compoundTag.putFloat("rot", abstractRot.getRotValue());
            compoundTag.putFloat("fs", abstractRot.getFinalSpeed());
            return compoundTag;
        }).orElse(new NbtCompound());
    }

    @Override
    public void deserializeNBT(NbtCompound nbt) {
        cap.ifPresent(abstractRot -> {
            abstractRot.setRotValue(nbt.getFloat("rot"));
            abstractRot.setFinalSpeed(nbt.getFloat("fs"));
        });
    }
}
