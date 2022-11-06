package nameless.classicraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import nameless.classicraft.util.sync.SyncDataManager;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class BasicBlockEntity extends BlockEntity {

    SyncDataManager syncDataManager = new SyncDataManager();
    boolean syncDataRegistered;

    protected BasicBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    /** 如果你有一些需要自动同步的内容，请放到这里面 */
    protected void registerSyncData(SyncDataManager manager) {
    }

    @Override
    public void onLoad() {
        super.onLoad();
        forceOnce();
    }

    public void tick() {
        if (level != null)
            sync(level);
    }

    private static final String SYNC_KEY = "sync";

    protected boolean isSyncTag(CompoundTag tag) {
        return tag.contains(SYNC_KEY);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        if (!syncDataRegistered) {
            registerSyncData(syncDataManager);
            syncDataRegistered = true;
        }

        syncDataManager.load(pTag, isSyncTag(pTag));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        syncDataManager.save(pTag, false, true);
    }

    boolean forceOnce;

    public void forceOnce() {
        forceOnce = true;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag result = new CompoundTag();
        syncDataManager.save(result, true, forceOnce);

        if (!result.isEmpty()) {
            result.putBoolean(SYNC_KEY, true);
            setChanged();
        }

        if (forceOnce) {
            forceOnce = false;
        }

        return result;
    }

    public abstract List<ItemStack> drops();

    public void sync(Level level) {
        if (!level.isClientSide) {
            ClientboundBlockEntityDataPacket p = ClientboundBlockEntityDataPacket.create(this);
            ((ServerLevel)this.level).getChunkSource().chunkMap.getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(k -> k.connection.send(p));
        }
    }

    public static <T extends BasicBlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        t.tick();
    }
}
