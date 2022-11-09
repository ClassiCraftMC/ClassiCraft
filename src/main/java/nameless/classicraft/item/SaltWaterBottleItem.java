package nameless.classicraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import toughasnails.api.potion.TANEffects;

public class SaltWaterBottleItem extends DrinkItem {

    public SaltWaterBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (ModList.get().isLoaded("toughasnails")) {
            entityLiving.addEffect(
                    new MobEffectInstance(TANEffects.THIRST.get(), 300, 2), entityLiving);
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }
}
