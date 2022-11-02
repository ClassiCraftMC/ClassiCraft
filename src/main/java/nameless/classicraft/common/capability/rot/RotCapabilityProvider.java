package nameless.classicraft.common.capability.rot;

import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class RotCapabilityProvider extends CapabilityProvider<RotCapabilityProvider> implements INBTSerializable<CompoundTag> {

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
    public CompoundTag serializeNBT() {
        return cap.map(abstractRot -> {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putFloat("rot", abstractRot.getRotValue());
            compoundTag.putFloat("fs", abstractRot.getFinalSpeed());
            return compoundTag;
        }).orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cap.ifPresent(abstractRot -> {
            abstractRot.setRotValue(nbt.getFloat("rot"));
            abstractRot.setFinalSpeed(nbt.getFloat("fs"));
        });
    }
}
