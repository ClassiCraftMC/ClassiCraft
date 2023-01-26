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
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static nameless.classicraft.client.meta.MetaModelRegistry.fromMetaItem;
import static nameless.classicraft.client.meta.MetaModelRegistry.model;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initCC(BlockColors pBlockColors, ProfilerFiller pProfilerFiller, Map pModelResources, Map pBlockStateResources, CallbackInfo ci) {
        MetaItem.getMetaItems().forEach(metaItem ->
                fromMetaItem(metaItem).forEach((meta, model) -> {
                    ResourceLocation location = metaItem.metaResLoc(meta);
                    BlockModel unbakedModel = model(location);

                    ((ModelBakery) (Object) this).unbakedCache.put(location, unbakedModel);
                    ((ModelBakery) (Object) this).topLevelModels.put(location, unbakedModel);
                    unbakedModel.resolveParents(((ModelBakery) (Object) this)::getModel);
                }));
    }
}
