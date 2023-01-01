package nameless.classicraft.client.meta;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.api.item.MetaItem;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaModelRegistry {
    @SubscribeEvent
    public static void onEvent(ModelEvent.ModifyBakingResult event) {
        ModelBakery bakery = event.getModelBakery();

        MetaItem.getMetaItems().forEach(metaItem -> {
            ResourceLocation regName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(metaItem));

            bakery.getBakedTopLevelModels().put(new ModelResourceLocation(
                    regName.getNamespace(),
                    regName.getPath(),
                    "inventory"
            ), new MetaBakedModel());
        });
    }

    /**
     * 从 meta item 返回 meta -> model 的 map
     *
     * @param metaItem meta item
     * @return meta -> model
     */
    public static Map<String, BlockModel> fromMetaItem(MetaItem metaItem) {
        Map<String, BlockModel> result = new HashMap<>();
        metaItem.metas.forEach(meta -> result.put(meta, model(metaItem.metaResLoc(meta))));
        return result;
    }

    public static BlockModel model(ResourceLocation rl) {
        return BlockModel.fromString("{\"parent\": \"minecraft:item/generated\", " +
                "\"textures\": {\"layer0\":\"" + rl.getNamespace() + ":item/" + rl.getPath() + "\"}}");
    }
}
