package nameless.classicraft.api.event;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class ProjectileHitEvent extends Event {

    private final Entity hitEntity;
    private final Block hitBlock;
    private final Direction hitFace;
    private final Projectile projectile;

    public ProjectileHitEvent(@NotNull final Projectile projectile) {
        this(projectile, null, null);
    }

    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity) {
        this(projectile, hitEntity, null);
    }

    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Block hitBlock) {
        this(projectile, null, hitBlock);
    }

    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock) {
        this(projectile, hitEntity, hitBlock, null);
    }

    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable Direction hitFace) {
        super();
        this.projectile = projectile;
        this.hitEntity = hitEntity;
        this.hitBlock = hitBlock;
        this.hitFace = hitFace;
    }

    /**
     * Gets the block that was hit, if it was a block that was hit.
     *
     * @return hit block or else null
     */
    @Nullable
    public Block getHitBlock() {
        return hitBlock;
    }

    /**
     * Gets the block face that was hit, if it was a block that was hit and the
     * face was provided in the vent.
     *
     * @return hit face or else null
     */
    @Nullable
    public Direction getHitBlockFace() {
        return hitFace;
    }

    /**
     * Gets the entity that was hit, if it was an entity that was hit.
     *
     * @return hit entity or else null
     */
    @Nullable
    public Entity getHitEntity() {
        return hitEntity;
    }


    public Projectile getEntity() {
        return projectile;
    }
}
