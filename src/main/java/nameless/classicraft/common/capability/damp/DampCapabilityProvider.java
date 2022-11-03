package nameless.classicraft.common.capability.damp;

import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DampCapabilityProvider extends CapabilityProvider<DampCapabilityProvider> implements INBTSerializable<CompoundTag> {

    LazyOptional<AbstractDamp> cap;

    public DampCapabilityProvider(LazyOptional<AbstractDamp> cap) {
        super(DampCapabilityProvider.class);

        this.cap = cap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.DAMP.orEmpty(cap, this.cap.cast());
    }

    @Override
    public CompoundTag serializeNBT() {
        return cap.map(abstractDamp -> {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putFloat("damp", abstractDamp.getDampValue());
            compoundTag.putFloat("fs", abstractDamp.getFinalSpeed());
            return compoundTag;
        }).orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cap.ifPresent(abstractRot -> {
            abstractRot.setDampValue(nbt.getFloat("damp"));
            abstractRot.setFinalSpeed(nbt.getFloat("fs"));
        });
    }
}
