package nameless.classicraft.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BiomeCompass extends CompassItem {

    public BiomeCompass() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.displayClientMessage(Component.translatable("It's in the biome of ").append(pLevel.getBiomeManager().getBiome(pPlayer.getOnPos()).get().toString()), true);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return isLodestoneCompass(pStack) ? "item.classicraft.biome_compass" : super.getDescriptionId(pStack);
    }
}
