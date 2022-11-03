package nameless.classicraft.mixin;

import nameless.classicraft.api.common.item.CCItemStack;
import nameless.classicraft.common.capability.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author DustW
 */
@Mixin(value = ItemStack.class)
public abstract class MixinItemStack implements CCItemStack {

    @Shadow public abstract boolean isEmpty();
    @Shadow public abstract Item getItem();
    @Shadow public abstract int getCount();
    @Shadow public abstract int getMaxStackSize();
    @Shadow public abstract void grow(int pIncrement);
    @Shadow @Nullable public abstract CompoundTag getTag();

    @Inject(method = "overrideOtherStackedOnMe", cancellable = true, at = @At("HEAD"))
    private void overrideOtherStackedOnMeCC(ItemStack other, Slot pSlot, ClickAction pAction,
                                            Player pPlayer, SlotAccess pAccess, CallbackInfoReturnable<Boolean> cir) {

        if (other.isEmpty() || isEmpty() || getItem() != other.getItem() || !Objects.equals(getTag(), other.getTag())) {
            cir.setReturnValue(false);
            return;
        }

        other.getCapability(ModCapabilities.ROT).ifPresent(rotOther -> {
            int count = other.getCount();

            getCapability(ModCapabilities.ROT).ifPresent(rotSelf -> {
                int selfCount = getCount();
                int finalCount = Math.min(count, getMaxStackSize() - selfCount);
                var finalRot = (rotOther.getRotValue() - rotSelf.getRotValue()) * finalCount / (selfCount + finalCount);

                other.shrink(finalCount);
                grow(finalCount);

                rotSelf.setRotValue(finalRot + rotSelf.getRotValue());

                cir.setReturnValue(true);
            });
        });

        other.getCapability(ModCapabilities.DAMP).ifPresent(dampOther -> {
            int count = other.getCount();

            getCapability(ModCapabilities.DAMP).ifPresent(dampSelf -> {
                int selfCount = getCount();
                int finalCount = Math.min(count, getMaxStackSize() - selfCount);

                var finalDamp = (dampOther.getRotValue() - dampSelf.getRotValue()) * finalCount / (selfCount + finalCount);

                other.shrink(finalCount);
                grow(finalCount);

                dampSelf.setRotValue(finalDamp + dampSelf.getRotValue());

                cir.setReturnValue(true);
            });
        });
    }

    @Inject(method = "getHoverName", at = @At("RETURN"), cancellable = true)
    private void getHoverNameCC(CallbackInfoReturnable<Component> cir) {
        getCapability(ModCapabilities.ROT).ifPresent(rot -> {
            if (rot.getHolder().getMax() > 0 && this.getItem() != Items.ROTTEN_FLESH) {
                Component origin = cir.getReturnValue();
                cir.setReturnValue(rot.getLevelName().append(origin)
                        .setStyle(Style.EMPTY.withItalic(false)
                                .withColor(rot.getLevelNameColor())));
            }
        });
        getCapability(ModCapabilities.DAMP).ifPresent(damp -> {
            if (damp.getHolder().getMax() > 0) {
                Component origin = cir.getReturnValue();
                cir.setReturnValue(damp.getLevelName().append(origin)
                        .setStyle(Style.EMPTY.withItalic(false)
                                .withColor(damp.getLevelNameColor())));
            }
        });
    }

}