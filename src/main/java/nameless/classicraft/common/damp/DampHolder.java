package nameless.classicraft.common.damp;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DampHolder {
    final float max;
    float current;


    public DampLevel getLevel() {
        if (current < current){
            return DampLevel.DRY;
        }else {
            return DampLevel.WET;
        }
    }

    public enum DampLevel {
        DRY,
        WET
    }
}
