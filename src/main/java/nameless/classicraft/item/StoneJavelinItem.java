package nameless.classicraft.item;


import java.util.function.Consumer;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.entity.projectile.JavelinThrownEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.util.NonNullLazy;

public class StoneJavelinItem extends SwordItem {
    private final ResourceLocation textureLocation;

    public StoneJavelinItem(Tier tier, float attackDamage, float attackSpeed, Properties properties, String name)
    {
        this(tier, (int) attackDamage, attackSpeed, properties, new ResourceLocation(ClassiCraftMod.MODID, "textures/entity/projectiles/stone_javelin.png"));
    }

    public StoneJavelinItem(Tier tier, float attackDamage, float attackSpeed, Properties properties, ResourceLocation name)
    {
        super(tier, (int) attackDamage, attackSpeed, properties);
        this.textureLocation = name;
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player)
    {
        return !player.isCreative();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int ticksLeft)
    {
        if (entity instanceof Player player)
        {
            int i = this.getUseDuration(stack) - ticksLeft;
            if (i >= 10)
            {
                if (!level.isClientSide)
                {
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(entity.getUsedItemHand()));

                    JavelinThrownEntity javelin = new JavelinThrownEntity(level, player, stack);
                    javelin.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (player.getAbilities().instabuild)
                    {
                        javelin.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(javelin);
                    level.playSound(null, javelin, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild)
                    {
                        player.getInventory().removeItem(stack);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack held = player.getItemInHand(hand);
        if (held.getDamageValue() >= held.getMaxDamage() - 1)
        {
            return InteractionResultHolder.fail(held);
        }
        else
        {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(held);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction)
    {
        return super.canPerformAction(stack, toolAction) && toolAction != ToolActions.SWORD_SWEEP;
    }

    /**
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(new ItemBlockRenderTypes() {
            private final NonNullLazy<JavelinItemRenderer> renderer = NonNullLazy.of(() -> new JavelinItemRenderer(getTextureLocation()));
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
            {
                return renderer.get();
            }
        });
    }*/

    public ResourceLocation getTextureLocation()
    {
        return textureLocation;
    }
}
