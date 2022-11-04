package nameless.classicraft.common.item;

import toughasnails.item.DrinkItem;

public class SaltWaterBottleItem extends DrinkItem {

    public SaltWaterBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAlwaysDrink() {
        return false;
    }
}
