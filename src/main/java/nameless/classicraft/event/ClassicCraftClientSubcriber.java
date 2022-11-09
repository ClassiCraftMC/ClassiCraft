package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.*;
import nameless.classicraft.client.renderer.*;
import nameless.classicraft.entity.*;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.init.ModScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassicCraftClientSubcriber {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DEER_ENEITY.get(), DeerEntity.registerAttributes().build());
        event.put(ModEntities.BOAR_ENTITY.get(), BoarEntity.registerAttributes().build());
        event.put(ModEntities.LIONFISH_ENTITY.get(), LionfishEntity.registerAttributes().build());
        event.put(ModEntities.PERCH_ENTITY.get(), PerchEntity.registerAttributes().build());
        event.put(ModEntities.RANCHU_ENTITY.get(), RanchuEntity.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BOAR_ENTITY.get(),
                m -> new BoarRenderer(m, new BoarModel<>(m.bakeLayer(ModEntityModelLayers.BOAR))));
        event.registerEntityRenderer(ModEntities.DEER_ENEITY.get(),
                m -> new CCGenericMobRenderer<>(m, new DeerModel(m.bakeLayer(ModEntityModelLayers.DEER)), 0.7F, "wilddeer.png"));
        event.registerEntityRenderer(ModEntities.LIONFISH_ENTITY.get(),
                LionfishRenderer::new);
        event.registerEntityRenderer(ModEntities.PERCH_ENTITY.get(),
                PerchRenderer::new);
        event.registerEntityRenderer(ModEntities.RANCHU_ENTITY.get(),
                RanchuRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.BOAR, BoarModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.DEER, DeerModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.LIONFISH, LionfishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.PERCH, PerchModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.RANCHU, RanchuModel::create);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ModScreens.registerScreen();
    }

}
