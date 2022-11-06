package nameless.classicraft.item;

import nameless.classicraft.block.entity.RotAbleBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * @author DustW
 */
public class BlockRotViewerItem extends Item {
    public BlockRotViewerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        if (!level.isClientSide && level.getBlockEntity(pContext.getClickedPos()) instanceof RotAbleBlockEntity rae) {
            Player player = pContext.getPlayer();
            if (player != null)
                player.sendSystemMessage(Component.translatable("%.1f/%.1f(%.2f天)".formatted(
                        rae.getHolder().getCurrent(),
                        rae.getHolder().getMax(),
                        rae.getHolder().getCurrent() / 24000)));
        }
        return super.useOn(pContext);
    }
}
