package nameless.classicraft.init;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

    public static final int TICK_INTERVAL = 1200;
    public static final int TORCH_INITIAL_BURN_TIME = 17;
    public static final int SOUL_TORCH_INITIAL_BURN_TIME = 16;
    public static final IntegerProperty TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, TORCH_INITIAL_BURN_TIME);
    public static final IntegerProperty SOUL_TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, 16);
    public static final IntegerProperty LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 9);
    public static final IntegerProperty SOUL_LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 8);

}
