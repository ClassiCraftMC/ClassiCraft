package nameless.classicraft.capability.rot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import nameless.classicraft.rot.RotHolder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

/**
 * @author DustW
 */
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractRot {

    private final RotHolder holder;
    private boolean hasExMsg;
    private Function<AbstractRot, List<Text>> exMsg;
    private float finalSpeed;

    @NotNull
    public List<Text> getMsg() {
        if (hasExMsg)
            return exMsg.apply(this);
        return List.of();
    }

    public RotHolder.RotLevel getLevel() {
        return getHolder().getLevel();
    }

    public MutableText getLevelName() {
        return switch (getLevel()) {
            case FRESH ->   Text.translatable("level.classicraft.fresh");
            case STALE ->   Text.translatable("level.classicraft.stale");
            case SPOILED -> Text.translatable("level.classicraft.spoiled");
            case ROT ->     Text.translatable("level.classicraft.rotten");
        };
    }

    public Formatting getLevelNameColor() {
        return switch (getLevel()) {
            case FRESH ->   Formatting.GREEN;
            case STALE ->   Formatting.YELLOW;
            case SPOILED -> Formatting.GOLD;
            case ROT ->     Formatting.RED;
        };
    }

    public float getRotValue() {
        return holder.getCurrent();
    }

    public void setRotValue(float v) {
        holder.setCurrent(v);
    }

}