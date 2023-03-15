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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(SolidBucketItem.class)
public class MixinSolidBucketItem extends BlockItem implements MetaItem {

    public MixinSolidBucketItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initCC(Block pBlock, SoundEvent pPlaceSound, Properties pProperties, CallbackInfo ci) {
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

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void useOnCC(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> cir) {
        InteractionResult cc_result = super.useOn(pContext);
        Player cc_player = pContext.getPlayer();
        ItemStack cc_stack = new ItemStack(Items.BUCKET);
        if (cc_result.consumesAction() && cc_player != null && !cc_player.isCreative()) {
            InteractionHand interactionhand = pContext.getHand();
            MetaItem.setMeta(cc_stack, MetaItem.getMeta(pContext.getItemInHand()));
            cc_player.setItemInHand(interactionhand, cc_stack);
        }
        cir.setReturnValue(cc_result);
    }

    @Override
    public List<String> getMetas() {
        if (getBlock().defaultBlockState().is(Blocks.POWDER_SNOW)) {
            return List.of(
                    "",
                    "_wooden"
            );
        }
        return List.of("");
    }

    @Override
    public ResourceLocation metaResLoc(String meta) {
        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(asItem()));
        return new ResourceLocation(key.getNamespace(), key.getPath() + (meta == null ? getMetas().get(0) : meta));
    }

}
