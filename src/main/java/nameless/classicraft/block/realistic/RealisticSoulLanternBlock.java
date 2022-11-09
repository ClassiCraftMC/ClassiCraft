package nameless.classicraft.block.realistic;

import nameless.classicraft.api.item.ItemStackAPI;
import nameless.classicraft.init.ModBlockProperties;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class RealisticSoulLanternBlock extends RealisticLanternBlock {

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem() == Items.FLINT_AND_STEEL) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.hurtAndBreak(1, pPlayer, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(pHand);
                });
                changeToLit(pLevel, pPos, pState);
                ModBlockProperties.playLightingSound(pLevel, pPos);
                pLevel.updateNeighborsAt(pPos, this);
            }
            return InteractionResult.SUCCESS;
        }
        if (pPlayer.getItemInHand(pHand).getItem() == ModItems.MATCHBOX.get()) {
            ModBlockProperties.playLightingSound(pLevel, pPos);
            if (!pPlayer.isCreative()) {
                ItemStack heldStack = pPlayer.getItemInHand(pHand);
                heldStack.shrink(1);
                if (pLevel.isRainingAt(pPos.above())) {
                    changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                    ModBlockProperties.playExtinguishSound(pLevel, pPos);
                } else {
                    if(pLevel.isRainingAt(pPos.above()))
                    {
                        changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                        ModBlockProperties.playExtinguishSound(pLevel, pPos);
                    }
                    else
                    {
                        changeToLit(pLevel, pPos, pState);
                        ModBlockProperties.playLightingSound(pLevel,pPos);
                    }
                    pLevel.updateNeighborsAt(pPos,this);
                }
            }
            else
            {
                if(pLevel.isRainingAt(pPos.above()))
                {
                    changeToSmoldering(pLevel,pPos,pState,getInitialBurnTime());
                    ModBlockProperties.playExtinguishSound(pLevel,pPos);
                }
                else
                {
                    changeToLit(pLevel, pPos, pState);
                }
                pLevel.updateNeighborsAt(pPos,this);
            }
            return InteractionResult.SUCCESS;
        }
        else if( pState.getValue(LITSTATE) == 2 && pPlayer.getItemInHand(pHand).is(ModItems.SOUL_LANTERN.get()))
        {
            pPlayer.setItemInHand(pHand, ItemStackAPI.replaceItemWithCopyNBTTagAndCountButResetBurnTime(pPlayer.getItemInHand(pHand),ModItems.LIT_SOUL_LANTERN.get(),INITIAL_BURN_TIME));
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState state = Blocks.SOUL_LANTERN.getStateForPlacement(pContext);
        ItemStack placeStack = pContext.getPlayer().getItemInHand(pContext.getHand());
        if(!placeStack.is(ModItems.LIT_SOUL_LANTERN.get())) return state == null ? null:this.defaultBlockState();
        if(placeStack.getOrCreateTag().contains("burnTime"))
        {

            int burnTime = placeStack.getTag().getInt("burnTime");
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                if(burnTime > INITIAL_BURN_TIME)
                {
                    return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
                }
                else if(burnTime <= 0)
                {
                    return  state == null ? null:this.defaultBlockState();
                }
                else
                {
                    return state == null ? null:this.defaultBlockState().setValue(BURNTIME,burnTime).setValue(LITSTATE,1);
                }
            }
            if(burnTime > INITIAL_BURN_TIME)
            {
                return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
            }
            else if(burnTime <= 0)
            {
                return  state == null ? null:this.defaultBlockState();
            }
            else
            {
                return state == null ? null:this.defaultBlockState().setValue(BURNTIME,burnTime).setValue(LITSTATE,2);
            }

        }
        else
        {
            if(pContext.getLevel().isRainingAt(pContext.getClickedPos().above()))
            {
                return  state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,1);
            }
            return state == null ? null:this.defaultBlockState().setValue(BURNTIME,INITIAL_BURN_TIME).setValue(LITSTATE,2);
        }
    }

    public void changeToLit(Level pLevel, BlockPos pPos, BlockState pState)
    {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.SOUL_LANTERN.get().defaultBlockState().setValue(LITSTATE,2).setValue(BURNTIME,INITIAL_BURN_TIME));
        if(SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos,this, TICK_INTERVAL);
        }
    }

    public void changeToSmoldering(Level pLevel, BlockPos pPos, BlockState pState, int burnTime)
    {
        pLevel.setBlockAndUpdate(pPos,ModBlocks.SOUL_LANTERN.get().defaultBlockState().setValue(LITSTATE,1).setValue(BURNTIME,burnTime));
        if(SHOULD_BURN_OUT)
        {
            pLevel.scheduleTick(pPos, this, TICK_INTERVAL);
        }
    }

    public void changeToUnlit(Level pLevel,BlockPos pPos,BlockState pState)
    {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.SOUL_LANTERN.get().defaultBlockState());
        pLevel.scheduleTick(pPos,this, TICK_INTERVAL);
    }
}

