package nameless.classicraft.init;

import net.minecraft.state.property.IntProperty;

public class ModBlockProperties {

    public static final int TICK_INTERVAL = 1200;
    public static final int TORCH_INITIAL_BURN_TIME = 17;
    public static final int SOUL_TORCH_INITIAL_BURN_TIME = 16;
    public static final IntProperty TORCH_BURN_TIME = IntProperty.of("burntime", 0, TORCH_INITIAL_BURN_TIME);
    public static final IntProperty SOUL_TORCH_BURN_TIME = IntProperty.of("burntime", 0, 16);
    public static final IntProperty LANTERN_BURN_TIME = IntProperty.of("burntime", 0, 9);
    public static final IntProperty SOUL_LANTERN_BURN_TIME = IntProperty.of("burntime", 0, 8);
    public static final IntProperty FULE_LEVEL = IntProperty.of("fuel_level", 0, 4);
}
