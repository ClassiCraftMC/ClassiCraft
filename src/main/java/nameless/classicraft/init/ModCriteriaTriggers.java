package nameless.classicraft.init;

import nameless.classicraft.trigger.WaterLevelRestoredTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers {

    public static final WaterLevelRestoredTrigger WATER_LEVEL_RESTORED_TRIGGER =
            CriteriaTriggers.register(new WaterLevelRestoredTrigger());
}
