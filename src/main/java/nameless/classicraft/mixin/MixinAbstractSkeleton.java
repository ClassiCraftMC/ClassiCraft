package nameless.classicraft.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractSkeleton.class)
public abstract class MixinAbstractSkeleton extends Monster implements RangedAttackMob {

    protected MixinAbstractSkeleton(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    /**
     * @author wdog5
     * @reason 添加Attribute
     */
    @Overwrite
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D);
    }
}
