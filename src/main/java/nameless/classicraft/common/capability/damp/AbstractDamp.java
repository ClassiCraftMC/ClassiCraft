package nameless.classicraft.common.capability.damp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import nameless.classicraft.common.damp.DampHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractDamp {

    private final DampHolder holder;
    private boolean hasExMsg;
    private Function<AbstractDamp, List<Component>> exMsg;
    private float finalSpeed;

    @NotNull
    public List<Component> getMsg() {
        if (hasExMsg)
            return exMsg.apply(this);
        return List.of();
    }

    public DampHolder.DampLevel getLevel() {
        return getHolder().getLevel();
    }

    public MutableComponent getLevelName() {
        return switch (getLevel()) {
            case DRY ->   Component.translatable("level.classicraft.dry");
            case WET ->     Component.translatable("level.classicraft.wet");
        };
    }

    public ChatFormatting getLevelNameColor() {
        return switch (getLevel()) {
            case DRY ->   ChatFormatting.RED;
            case WET ->     ChatFormatting.BLUE;
        };
    }

    public float getDampValue() {
        return holder.getCurrent();
    }

    public void setDampValue(float v) {
        holder.setCurrent(v);
    }

}