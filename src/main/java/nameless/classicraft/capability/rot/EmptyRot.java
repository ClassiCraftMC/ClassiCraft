package nameless.classicraft.capability.rot;

import nameless.classicraft.rot.RotHolder;

import java.util.List;

/**
 * @author DustW
 */
public class EmptyRot extends AbstractRot {
    public EmptyRot() {
        super(new RotHolder(0, 1), false, r -> List.of(), 0);
    }
}
