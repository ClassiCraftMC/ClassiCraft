package nameless.classicraft.api.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * 在实体食用物品的时候触发
 */
@Cancelable
public class LivingEatEvent extends LivingEvent {

    private final Level level;
    private final ItemStack food;

    public LivingEatEvent(LivingEntity entity, Level level, ItemStack food) {
        super(entity);
        this.level = level;
        this.food = food;
    }

    public Level getLevel() {
        return level;
    }

    public ItemStack getFood() {
        return food;
    }
}
