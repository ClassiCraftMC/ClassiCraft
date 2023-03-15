/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.mixin;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(MilkBucketItem.class)
public class MixinMilkBucketItem extends Item implements MetaItem {

    public MixinMilkBucketItem(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initCC(Properties pProperties, CallbackInfo ci) {
        META_ITEMS.add(this);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack result = new ItemStack(asItem());
        MetaItem.setMeta(result, getMetas().get(0));
        return result;
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack pStack) {
        if (getMetas().get(0).equals(MetaItem.getMeta(pStack)))
            super.getDescriptionId(pStack);
        return asItem().getDescriptionId() + MetaItem.getMeta(pStack);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void finishUsingItemCC(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = new ItemStack(Items.BUCKET);
        MetaItem.setMeta(itemStack, MetaItem.getMeta(pStack));
        if (!pLevel.isClientSide) pEntityLiving.curePotionEffects(pStack); // FORGE - move up so stack.shrink does not turn stack into air
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild) {
            pStack.shrink(1);
        }
        cir.setReturnValue(pStack.isEmpty() ? itemStack : pStack);
    }

    @Override
    public List<String> getMetas() {
        return List.of(
                "",
                "_wooden"
        );
    }

    @Override
    public ResourceLocation metaResLoc(String meta) {
        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(asItem()));
        return new ResourceLocation(key.getNamespace(), key.getPath() + (meta == null ? getMetas().get(0) : meta));
    }
}
