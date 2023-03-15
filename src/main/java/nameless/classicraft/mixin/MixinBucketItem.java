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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem extends Item implements MetaItem {

    public MixinBucketItem(Properties pProperties) {
        super(pProperties);
        // do nothing
    }

    @Inject(method = "<init>(Ljava/util/function/Supplier;Lnet/minecraft/world/item/Item$Properties;)V",
            at = @At("RETURN"))
    private void inita(Supplier<?> supplier, Item.Properties builder, CallbackInfo ci) {
        META_ITEMS.add(this);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/world/item/Item$Properties;)V",
            at = @At("RETURN"))
    private void initb(Fluid pContent, Item.Properties pProperties, CallbackInfo ci) {
        META_ITEMS.add(this);
    }

    @Inject(method = "getEmptySuccessItem", at = @At("HEAD"), cancellable = true)
    private static void getEmptySuccessItemCC(ItemStack pBucketStack, Player pPlayer, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = new ItemStack(Items.BUCKET);
        MetaItem.setMeta(itemStack, MetaItem.getMeta(pBucketStack));
        cir.setReturnValue(!pPlayer.getAbilities().instabuild ? itemStack : pBucketStack);
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

    @Override
    public List<String> getMetas() {
        if (((BucketItem) (Object) this).getFluid() == Fluids.WATER
                ||((BucketItem) (Object) this).getFluid() == Fluids.LAVA
                ||((BucketItem) (Object) this).getFluid() == Fluids.EMPTY) {
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

