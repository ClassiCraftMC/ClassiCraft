package nameless.classicraft.common.capability.damp;

import nameless.classicraft.common.damp.DampHolder;

import java.util.List;

public class EmptyDamp extends AbstractDamp {

    protected EmptyDamp() {
        super(new DampHolder(0, 1), false, r -> List.of(), 0);
    }
}
