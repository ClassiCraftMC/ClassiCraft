package nameless.classicraft.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.level.material.Material;

import static net.minecraft.world.level.material.PushReaction.BLOCK;
import static net.minecraft.world.level.material.PushReaction.DESTROY;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockUtils {
    public static Material.Builder from(Material material) {
        var result =  new Material.Builder(material.getColor());

        if (material.isLiquid()) result.liquid();
        if (!material.isSolid()) result.nonSolid();
        if (material.blocksMotion()) result.noCollider();
        if (!material.isSolidBlocking()) result.notSolidBlocking();
        if (material.isFlammable()) result.flammable();
        if (material.isReplaceable()) result.replaceable();

        if (material.getPushReaction() == DESTROY)
            result.destroyOnPush();
        else if (material.getPushReaction() == BLOCK)
            result.notPushable();

        return result;
    }
}
