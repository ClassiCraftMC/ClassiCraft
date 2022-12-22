package nameless.classicraft.entity.npc;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractNpc extends AgeableMob implements InventoryCarrier, Npc {

    private static final EntityDataAccessor<Integer> DATA_UNHAPPY_COUNTER = SynchedEntityData.defineId(AbstractNpc.class, EntityDataSerializers.INT);
    private final SimpleContainer inventory = new SimpleContainer(8);

    protected AbstractNpc(EntityType<? extends AgeableMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (pSpawnData == null) {
            pSpawnData = new AgeableMob.AgeableMobGroupData(false);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? 0.81F : 1.62F;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_UNHAPPY_COUNTER, 0);
    }

    public void overrideXp(int pXp) {
    }

    public boolean showProgressBar() {
        return true;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.inventory.fromTag(pCompound.getList("Inventory", 10));
    }

    @Nullable
    public Entity changeDimension(ServerLevel pServer, net.minecraftforge.common.util.ITeleporter teleporter) {
        return super.changeDimension(pServer, teleporter);
    }

    protected void addParticlesAroundSelf(ParticleOptions pParticleOption) {
        for(int i = 0; i < 5; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(pParticleOption, this.getRandomX(1.0D), this.getRandomY() + 1.0D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public SlotAccess getSlot(int pSlot) {
        int i = pSlot - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(pSlot);
    }

    public boolean isClientSide() {
        return this.level.isClientSide;
    }
}
