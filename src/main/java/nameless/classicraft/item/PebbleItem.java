package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.api.item.MetaItem;
import nameless.classicraft.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

/**
 * @author DustW
 */
public class PebbleItem extends MetaItem {

    public static String getPebbleType(ItemStack stack) {
        String meta = getMeta(stack);
        return meta.substring(0, meta.length() - 7);
    }

    public PebbleItem() {
        super(new Item.Properties(), List.of(
                "andesite_pebble",
                "basalt_pebble",
                "blackstone_pebble",
                "cobblestone_pebble",
                "deepslate_pebble",
                "diorite_pebble",
                "granite_pebble",
                "red_sandstone_pebble",
                "sandstone_pebble",
                "quartz_sandstone_pebble",
                "soul_sandstone_pebble",
                "netherrack_pebble",
                "end_stone_pebble",
                "flint"
        ));
    }

    public static String metaFromBlock(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    public static ItemStack fromBlock(Block block) {
        ItemStack result = new ItemStack(ModItems.PEBBLE.get());
        setMeta(result, metaFromBlock(block));
        return result;
    }

    public static CompoundTag getTagFrom(Block block) {
        CompoundTag result = new CompoundTag();
        result.put(ClassiCraftMod.MOD_ID, new CompoundTag());
        result.getCompound(ClassiCraftMod.MOD_ID).putString("Meta", metaFromBlock(block));
        return result;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        ItemStack item = pContext.getItemInHand();
        Player player = pContext.getPlayer();

        if (player != null && level.getBlockState(pContext.getClickedPos()).getMaterial() == Material.STONE) {
            if (addItem(player, item))
                item.shrink(1);

            return InteractionResult.SUCCESS;
        } else
            return Objects.requireNonNull(ForgeRegistries.BLOCKS
                            .getValue(new ResourceLocation(ClassiCraftMod.MOD_ID, getMeta(item))))
                    .asItem()
                    .useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pUsedHand == InteractionHand.MAIN_HAND) {
            ItemStack main = pPlayer.getMainHandItem();
            ItemStack off = pPlayer.getOffhandItem();

            if (main.getItem() == off.getItem() && addItem(pPlayer, main)) {
                main.shrink(1);
                off.shrink(1);
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    /**
     * 添加随机工具到玩家身上并返回是否成功
     *
     * @param player 玩家
     * @param item   手持（左手或者右手）
     * @return 在需要减少物品数量时返回 true（工具存在、在服务端以及玩家不是创造模式）
     */
    boolean addItem(Player player, ItemStack item) {
        Level level = player.level;

        if (!level.isClientSide) {
            ItemStack tool = PebbleToolItem.randomFrom(item, level.random);

            if (!tool.isEmpty() && level.random.nextInt(100) < 15)
                player.addItem(tool);

            return !player.isCreative();
        } else
            player.playSound(SoundEvents.FLINTANDSTEEL_USE);

        return false;
    }
}
