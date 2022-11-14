package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.*;
import nameless.classicraft.client.renderer.*;
import nameless.classicraft.entity.*;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.init.ModScreens;
import net.minecraft.client.model.CodModel;
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
        event.put(ModEntities.ANGLEFISH_ENTITY.get(), AngleFishEntity.registerAttributes().build());
        event.put(ModEntities.ARAPAIMA_ENTITY.get(), ArapaimaEntity.registerAttributes().build());
        event.put(ModEntities.BELUGA_STURGEON_ENTITY.get(), BelugaSturgeonEntity.registerAttributes().build());
        event.put(ModEntities.BOXFISH_ENTITY.get(), BoxfishEntity.registerAttributes().build());
        event.put(ModEntities.FOOTBALLFISH_ENTITY.get(), FootballFishEntity.registerAttributes().build());
        event.put(ModEntities.FOXFACE_FISH_ENTITY.get(), FoxFaceFishEntity.registerAttributes().build());
        event.put(ModEntities.BASKING_SHARK_ENTITY.get(), BaskingSharkEntity.registerAttributes().build());
        event.put(ModEntities.OCEAN_SHARK_ENTITY.get(), OceanSharkEntity.registerAttributes().build());
        event.put(ModEntities.BULL_SHARK_ENTITY.get(), BullSharkEntity.registerAttributes().build());
        event.put(ModEntities.LEMON_SHARK_ENTITY.get(), LemonSharkEntity.registerAttributes().build());
        event.put(ModEntities.SLEEPER_SHARK_ENTITY.get(), SleeperSharkEntity.registerAttributes().build());
        event.put(ModEntities.TROUT_ENTITY.get(), TroutEntity.registerAttributes().build());
        event.put(ModEntities.BUTTERFLY_ENTITY.get(), ButterflyEntity.registerAttributes().build());
        event.put(ModEntities.SWINE_ENTITY.get(), SwineEntity.registerAttributes().build());
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
        event.registerEntityRenderer(ModEntities.ANGLEFISH_ENTITY.get(),
                AngleFishRenderer::new);
        event.registerEntityRenderer(ModEntities.ARAPAIMA_ENTITY.get(),
                ArapaimaRenderer::new);
        event.registerEntityRenderer(ModEntities.BELUGA_STURGEON_ENTITY.get(),
                BelugaSturgeonRenderer::new);
        event.registerEntityRenderer(ModEntities.BOXFISH_ENTITY.get(),
                BoxfishRenderer::new);
        event.registerEntityRenderer(ModEntities.FOOTBALLFISH_ENTITY.get(),
                FootbalFishRenderer::new);
        event.registerEntityRenderer(ModEntities.FOXFACE_FISH_ENTITY.get(),
                FoxFaceFishRenderer::new);
        event.registerEntityRenderer(ModEntities.BASKING_SHARK_ENTITY.get(),
                BaskingSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.OCEAN_SHARK_ENTITY.get(),
                OceanSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.BULL_SHARK_ENTITY.get(),
                BullSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.LEMON_SHARK_ENTITY.get(),
                LemonSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.SLEEPER_SHARK_ENTITY.get(),
                SleeperSharkRenderer::new);
        event.registerEntityRenderer(ModEntities.TROUT_ENTITY.get(),
                TroutRenderer::new);
        event.registerEntityRenderer(ModEntities.BUTTERFLY_ENTITY.get(),
                ButterflyRenderer::new);
        event.registerEntityRenderer(ModEntities.SWINE_ENTITY.get(),
                SwineRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.BOAR, BoarModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.DEER, DeerModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.LIONFISH, LionfishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.PERCH, PerchModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.RANCHU, RanchuModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.ANGELFISH, AngleFishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.ARAPAIMA, ArapaimaModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.BELUGA_STURGEON, BelugaSturgeonModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.BOXFISH, BoxfishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.FOOTBALLFISH, FootballFishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.FOXFACE_FISH, FoxFaceFishModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.BASKINGSHARK, BaskingSharkModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.OCEANSHARK, OceanSharkModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.BULLSHARK, BullSharkModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.LEMONSHARK, LemonSharkModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.SLEEPERSHARK, SleeperSharkModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.TROUT, CodModel::createBodyLayer);
        event.registerLayerDefinition(ModEntityModelLayers.BUTTERFLY, ButterflyModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.SWINE, SwineModel::create);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ModScreens.registerScreen();
    }

}
