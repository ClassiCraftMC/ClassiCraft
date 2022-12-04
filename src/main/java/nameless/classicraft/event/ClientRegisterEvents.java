package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.OceanSharkModel;
import nameless.classicraft.client.renderer.LivingDeadRenderer;
import nameless.classicraft.client.renderer.OceanSharkRenderer;
import nameless.classicraft.client.renderer.TroutRenderer;
import nameless.classicraft.entity.LivingDeadEntity;
import nameless.classicraft.entity.OceanSharkEntity;
import nameless.classicraft.entity.TroutEntity;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.item.DepthMeterItem;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@SuppressWarnings("removal")
@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegisterEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TROUT_ENTITY.get(), TroutEntity.registerAttributes().build());
        event.put(ModEntities.OCEAN_SHARK_ENTITY.get(), OceanSharkEntity.registerAttributes().build());
        event.put(ModEntities.LIVING_DEAD.get(), LivingDeadEntity.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TROUT_ENTITY.get(),
                TroutRenderer::new);
        event.registerEntityRenderer(ModEntities.OCEAN_SHARK_ENTITY.get(),
                OceanSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.LIVING_DEAD.get(),
                LivingDeadRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.TROUT, CodModel::createBodyLayer);
        event.registerLayerDefinition(ModEntityModelLayers.OCEAN_SHARK, OceanSharkModel::create);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerBlockRenders(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.REAL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.REAL_WALL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.REAL_SOUL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.REAL_SOUL_WALL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CACTUS_BALL.get(), RenderType.cutout());
        ItemProperties.register(ModItems.DEPTH_METER.get(), new ResourceLocation("angle"), new CompassItemPropertyFunction((level, stack, pEntity) -> {
            return DepthMeterItem.isLodestoneCompass(stack) ? DepthMeterItem.getLodestonePosition(stack.getOrCreateTag()) : DepthMeterItem.getSpawnPosition(level);
        }));
    }
}
