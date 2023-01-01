package nameless.classicraft.mixin;

import nameless.classicraft.api.item.MetaItem;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static nameless.classicraft.client.meta.MetaModelRegistry.fromMetaItem;
import static nameless.classicraft.client.meta.MetaModelRegistry.model;

/**
 * @author DustW
 */
@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {
    @Shadow @Final public Map<ResourceLocation, UnbakedModel> unbakedCache;

    @Shadow @Final private Map<ResourceLocation, UnbakedModel> topLevelModels;

    @Shadow public abstract UnbakedModel getModel(ResourceLocation p_119342_);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initCC(BlockColors pBlockColors, ProfilerFiller pProfilerFiller, Map pModelResources, Map pBlockStateResources, CallbackInfo ci) {
        MetaItem.getMetaItems().forEach(metaItem ->
                fromMetaItem(metaItem).forEach((meta, model) -> {
                    ResourceLocation location = metaItem.metaResLoc(meta);
                    BlockModel unbakedModel = model(location);

                    this.unbakedCache.put(location, unbakedModel);
                    this.topLevelModels.put(location, unbakedModel);
                    unbakedModel.resolveParents(this::getModel);
                }));
    }
}
