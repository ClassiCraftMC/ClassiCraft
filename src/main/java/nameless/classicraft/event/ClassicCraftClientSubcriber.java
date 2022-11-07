package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.client.model.BoarModel;
import nameless.classicraft.client.model.DeerModel;
import nameless.classicraft.client.renderer.BoarRenderer;
import nameless.classicraft.entity.BoarEntity;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.client.renderer.CCGenericMobRenderer;
import nameless.classicraft.entity.DeerEntity;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.init.ModScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassicCraftClientSubcriber {

    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void renderRegister(FMLClientSetupEvent event) {
        for (RegistryObject<Block> UNLIT_CANDLEHOLDER : ModBlocks.UNLIT_CANDLEHOLDERS)
            ItemBlockRenderTypes.setRenderLayer(UNLIT_CANDLEHOLDER.get(), RenderType.cutout());
        for (RegistryObject<Block> UNLIT_LARGE_CANDLEHOLDER : ModBlocks.UNLIT_LARGE_CANDLEHOLDERS)
            ItemBlockRenderTypes.setRenderLayer(UNLIT_LARGE_CANDLEHOLDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LARGE_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONE_MORTAR_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP_PANICLES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_LANTERN.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DEER_ENEITY.get(), DeerEntity.registerAttributes().build());
        event.put(ModEntities.BOAR_ENTITY.get(), BoarEntity.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BOAR_ENTITY.get(),
                m -> new BoarRenderer(m, new BoarModel<>(m.bakeLayer(ModEntityModelLayers.BOAR))));
        event.registerEntityRenderer(ModEntities.DEER_ENEITY.get(),
                m -> new CCGenericMobRenderer<>(m, new DeerModel(m.bakeLayer(ModEntityModelLayers.DEER)), 0.7F, "wilddeer.png"));
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.BOAR, BoarModel::create);
        event.registerLayerDefinition(ModEntityModelLayers.DEER, DeerModel::create);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLClientSetupEvent event) {
        ModScreens.registerScreen();
    }
}
