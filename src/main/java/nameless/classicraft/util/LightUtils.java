package nameless.classicraft.util;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import nameless.classicraft.block.AbstractLightBlock;
import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;

public class LightUtils {

    protected static final int TICK_INTERVAL = ModBlockProperties.TICK_INTERVAL;
    public static final Object2FloatMap<ItemLike> FUEL_LIST = new Object2FloatOpenHashMap<>();

    public static void bootstrapFuel() {
        FUEL_LIST.defaultReturnValue(-1.0F);
        add(0.3F, Items.JUNGLE_LEAVES);
        add(0.3F, Items.OAK_LEAVES);
        add(0.3F, Items.SPRUCE_LEAVES);
        add(0.3F, Items.DARK_OAK_LEAVES);
        add(0.3F, Items.ACACIA_LEAVES);
        add(0.3F, Items.BIRCH_LEAVES);
        add(0.3F, Items.AZALEA_LEAVES);
        add(0.3F, Items.MANGROVE_LEAVES);
        add(0.3F, Items.OAK_SAPLING);
        add(0.3F, Items.SPRUCE_SAPLING);
        add(0.3F, Items.BIRCH_SAPLING);
        add(0.3F, Items.JUNGLE_SAPLING);
        add(0.3F, Items.ACACIA_SAPLING);
        add(0.3F, Items.DARK_OAK_SAPLING);
        add(0.3F, Items.MANGROVE_PROPAGULE);
        add(0.3F, Items.BEETROOT_SEEDS);
        add(0.3F, Items.DRIED_KELP);
        add(0.3F, Items.GRASS);
        add(0.3F, Items.KELP);
        add(0.3F, Items.MELON_SEEDS);
        add(0.3F, Items.PUMPKIN_SEEDS);
        add(0.3F, Items.SEAGRASS);
        add(0.3F, Items.SWEET_BERRIES);
        add(0.3F, Items.GLOW_BERRIES);
        add(0.3F, Items.WHEAT_SEEDS);
        add(0.3F, Items.MOSS_CARPET);
        add(0.3F, Items.SMALL_DRIPLEAF);
        add(0.3F, Items.HANGING_ROOTS);
        add(0.3F, Items.MANGROVE_ROOTS);
        add(0.5F, Items.DRIED_KELP_BLOCK);
        add(0.5F, Items.TALL_GRASS);
        add(0.5F, Items.FLOWERING_AZALEA_LEAVES);
        add(0.5F, Items.CACTUS);
        add(0.5F, Items.SUGAR_CANE);
        add(0.5F, Items.VINE);
        add(0.5F, Items.NETHER_SPROUTS);
        add(0.5F, Items.WEEPING_VINES);
        add(0.5F, Items.TWISTING_VINES);
        add(0.5F, Items.MELON_SLICE);
        add(0.5F, Items.GLOW_LICHEN);
        add(0.65F, Items.SEA_PICKLE);
        add(0.65F, Items.LILY_PAD);
        add(0.65F, Items.PUMPKIN);
        add(0.65F, Items.CARVED_PUMPKIN);
        add(0.65F, Items.MELON);
        add(0.65F, Items.APPLE);
        add(0.65F, Items.BEETROOT);
        add(0.65F, Items.CARROT);
        add(0.65F, Items.COCOA_BEANS);
        add(0.65F, Items.POTATO);
        add(0.65F, Items.WHEAT);
        add(0.65F, Items.BROWN_MUSHROOM);
        add(0.65F, Items.RED_MUSHROOM);
        add(0.65F, Items.MUSHROOM_STEM);
        add(0.65F, Items.CRIMSON_FUNGUS);
        add(0.65F, Items.WARPED_FUNGUS);
        add(0.65F, Items.NETHER_WART);
        add(0.65F, Items.CRIMSON_ROOTS);
        add(0.65F, Items.WARPED_ROOTS);
        add(0.65F, Items.SHROOMLIGHT);
        add(0.65F, Items.DANDELION);
        add(0.65F, Items.POPPY);
        add(0.65F, Items.BLUE_ORCHID);
        add(0.65F, Items.ALLIUM);
        add(0.65F, Items.AZURE_BLUET);
        add(0.65F, Items.RED_TULIP);
        add(0.65F, Items.ORANGE_TULIP);
        add(0.65F, Items.WHITE_TULIP);
        add(0.65F, Items.PINK_TULIP);
        add(0.65F, Items.OXEYE_DAISY);
        add(0.65F, Items.CORNFLOWER);
        add(0.65F, Items.LILY_OF_THE_VALLEY);
        add(0.65F, Items.WITHER_ROSE);
        add(0.65F, Items.FERN);
        add(0.65F, Items.SUNFLOWER);
        add(0.65F, Items.LILAC);
        add(0.65F, Items.ROSE_BUSH);
        add(0.65F, Items.PEONY);
        add(0.65F, Items.LARGE_FERN);
        add(0.65F, Items.SPORE_BLOSSOM);
        add(0.65F, Items.AZALEA);
        add(0.65F, Items.MOSS_BLOCK);
        add(0.65F, Items.BIG_DRIPLEAF);
        add(0.85F, Items.HAY_BLOCK);
        add(0.85F, Items.BROWN_MUSHROOM_BLOCK);
        add(0.85F, Items.RED_MUSHROOM_BLOCK);
        add(0.85F, Items.NETHER_WART_BLOCK);
        add(0.85F, Items.WARPED_WART_BLOCK);
        add(0.85F, Items.FLOWERING_AZALEA);
        add(0.85F, Items.BREAD);
        add(0.85F, Items.BAKED_POTATO);
        add(0.85F, Items.COOKIE);
        add(1.0F, Items.CAKE);
        add(1.0F, Items.PUMPKIN_PIE);
    }

    public static void handleFill(Level pLevel, BlockPos pPos, boolean pSuccess) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        pLevel.playLocalSound((double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ(), pSuccess ? SoundEvents.COMPOSTER_FILL_SUCCESS : SoundEvents.COMPOSTER_FILL, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        double d0 = blockstate.getShape(pLevel, pPos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
        double d1 = (double)0.13125F;
        double d2 = (double)0.7375F;
        RandomSource randomsource = pLevel.getRandom();

        for(int i = 0; i < 10; ++i) {
            double d3 = randomsource.nextGaussian() * 0.02D;
            double d4 = randomsource.nextGaussian() * 0.02D;
            double d5 = randomsource.nextGaussian() * 0.02D;
            pLevel.addParticle(ParticleTypes.ASH, (double)pPos.getX() + (double)0.13125F + (double)0.7375F * (double)randomsource.nextFloat(), (double)pPos.getY() + d0 + (double)randomsource.nextFloat() * (1.0D - d0), (double)pPos.getZ() + (double)0.13125F + (double)0.7375F * (double)randomsource.nextFloat(), d3, d4, d5);
        }

    }

    private static void add(float pChance, ItemLike pItem) {
        FUEL_LIST.put(pItem.asItem(), pChance);
    }

    public static void torchPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving, Block pBlock) {
        if(!pIsMoving && pState.getBlock() != pOldState.getBlock()) {
            pBlock.defaultBlockState().updateIndirectNeighbourShapes(pLevel, pPos, 3);
        }
        if(pOldState.is(pBlock) && pState.getValue(AbstractLightBlock.getLitState())) {
            pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
        }
    }

    public static void tickTorch(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, Block pBlock, IntegerProperty burnTime) {
        if (!pLevel.isClientSide && pState.getValue(AbstractLightBlock.getLitState())) {
            int newBurnTime = pState.getValue(burnTime) - 1;
            if (pLevel.isRainingAt(pPos.above())) {
                playExtinguishSound(pLevel, pPos);
                newBurnTime -= pRandom.nextInt(20,35);
                if (newBurnTime <= 0) {
                    changeToStick(pLevel, pPos);
                } else {
                    changeToUnlit(pLevel, pPos, pBlock);
                }
                pLevel.updateNeighborsAt(pPos, pBlock);
            }
            if (newBurnTime <= 0) {
                playExtinguishSound(pLevel, pPos);
                changeToStick(pLevel, pPos);
                pLevel.updateNeighborsAt(pPos, pBlock);
            } else {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(burnTime, pState.getValue(burnTime) -1 ));
                pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
            }
        }
    }

    private static void changeToStick(Level pLevel, BlockPos pPos) {
        ItemEntity itemEntity =
                new ItemEntity(pLevel,
                        pPos.getX(),
                        pPos.getY(),
                        pPos.getZ(),
                        Items.STICK.getDefaultInstance());
        pLevel.addFreshEntity(itemEntity);
        pLevel.gameEvent(itemEntity, GameEvent.ENTITY_PLACE, pPos);
    }

    public static InteractionResult interactTorch(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, Block pBlock, Item fireStarter, IntegerProperty burnTime, int initialBurnTime) {
        ItemStack heldStack = pPlayer.getMainHandItem();
        if (heldStack.is(fireStarter) && !pBlock.defaultBlockState().getValue(AbstractLightBlock.getLitState())) {
            playLightingSound(pLevel, pPos);
            pPlayer.swing(pHand);
            if (!pPlayer.isCreative()) {
                heldStack.hurtAndBreak(1, pPlayer, (player) -> {
                    player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }
            if (pLevel.isRainingAt(pPos.above())) {
                playExtinguishSound(pLevel, pPos);
                changeToUnlit(pLevel, pPos, pBlock);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_PLACE, pPos);
            } else {
                playLightingSound(pLevel, pPos);
                changeToLit(pLevel, pPos, pBlock, burnTime, initialBurnTime);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_PLACE, pPos);
            }
            pLevel.updateNeighborsAt(pPos, pBlock);
        }
        return InteractionResult.SUCCESS;
    }

    public static void changeToLit(Level pLevel, BlockPos pPos, Block pBlock, IntegerProperty burnTime, int initialBurnTime)
    {
        pLevel.setBlockAndUpdate(pPos, pBlock.defaultBlockState().setValue(AbstractLightBlock.getLitState(),true).setValue(burnTime, initialBurnTime));
        pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
    }

    public static void changeToUnlit(Level pLevel, BlockPos pPos, Block pBlock)
    {
        pLevel.setBlockAndUpdate(pPos, pBlock.defaultBlockState());
        pLevel.scheduleTick(pPos, pBlock, TICK_INTERVAL);
    }

    public static void playExtinguishSound(Level pLevel,BlockPos pPos)
    {
        pLevel.playSound(null,pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,1, pLevel.random.nextFloat() * 0.1F + 0.9F);
    }

    public static void playLightingSound(Level pLevel,BlockPos pPos)
    {
        pLevel.playSound(null,pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS,1, pLevel.random.nextFloat() * 0.1F + 0.9F);
    }
}
