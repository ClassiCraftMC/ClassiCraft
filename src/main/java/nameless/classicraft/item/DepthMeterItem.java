package nameless.classicraft.item;

import nameless.classicraft.init.ModCreativeModeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DepthMeterItem extends CompassItem {

    public DepthMeterItem() {
        super(new Properties().tab(ModCreativeModeTabs.COMMON).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        BlockPos pos = pPlayer.getOnPos();
        pPlayer.sendSystemMessage(Component.translatable("info.classicraft.depth" + ":" + pos.getZ()));
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return isLodestoneCompass(pStack) ? "item.classicraft.depth_meter" : super.getDescriptionId(pStack);
    }
}
