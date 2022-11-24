package nameless.classicraft.event;

import nameless.classicraft.client.menu.PolishMenu;
import nameless.classicraft.entity.SoulEmptyEntity;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        int count = stack.getCount();
        Level level = event.getLevel();
        if (stack.is(ModBlocks.ANDESITE_LOOSE_ROCK.get().asItem())
                || stack.is(ModBlocks.DIORITE_LOOSE_ROCK.get().asItem())
                || stack.is(ModBlocks.GRANITE_LOOSE_ROCK.get().asItem())
                || stack.is(ModBlocks.STONE_LOOSE_ROCK.get().asItem())
                || stack.is(ModBlocks.SANDSTONE_LOOSE_ROCK.get().asItem())
                || stack.is(ModBlocks.RED_SANDSTONE_LOOSE_ROCK.get().asItem())) {
            if (count >= 2 && !level.isClientSide) {
                player.openMenu(new MenuProvider() {

                    @Override
                    public Component getDisplayName() {
                        return Component.translatable("gui.classicraft.polish");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new PolishMenu(id, inventory);
                    }
                });
                player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event)
    {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        BlockState blockState = event.getTargetBlock();
        if (blockState.is(Tags.Blocks.NEEDS_WOOD_TOOL)
                || blockState.is(Tags.Blocks.NEEDS_GOLD_TOOL)
                || blockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                || blockState.is(BlockTags.NEEDS_STONE_TOOL)
                || blockState.is(BlockTags.NEEDS_IRON_TOOL)
                || blockState.is(BlockTags.NEEDS_DIAMOND_TOOL)
                || blockState.is(BlockTags.LOGS)
                || blockState.is(BlockTags.PLANKS)
                || blockState.is(BlockTags.ACACIA_LOGS)
                || blockState.is(BlockTags.BIRCH_LOGS)
                || blockState.is(BlockTags.JUNGLE_LOGS)
                || blockState.is(BlockTags.DARK_OAK_LOGS)
                || blockState.is(BlockTags.MANGROVE_LOGS)
                || blockState.is(BlockTags.OAK_LOGS)
                || blockState.is(BlockTags.ACACIA_LOGS)) {
            if (stack.is(Tags.Items.TOOLS)) {
                event.setCanHarvest(true);
            }else {
                event.setCanHarvest(false);
            }
        }
    }
}
