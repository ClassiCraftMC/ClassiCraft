package nameless.classicraft.event;

import nameless.classicraft.ClassiCraft;
import nameless.classicraft.client.model.DeerModel;
import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.client.renderer.CCGenericMobRenderer;
import nameless.classicraft.common.entity.passive.DeerEntity;
import nameless.classicraft.common.entity.ModEntities;
import nameless.classicraft.client.ModEntityModelLayers;
import nameless.classicraft.common.menu.ModScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ClassiCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassicCraftClientSubcriber {

    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void renderRegister(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_SOUL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_UNLIT_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_UNLIT_SOUL_TORCH.get(), RenderType.cutout());
        for (RegistryObject<Block> UNLIT_CANDLEHOLDER : ModBlocks.UNLIT_CANDLEHOLDERS)
            ItemBlockRenderTypes.setRenderLayer(UNLIT_CANDLEHOLDER.get(), RenderType.cutout());
        for (RegistryObject<Block> UNLIT_LARGE_CANDLEHOLDER : ModBlocks.UNLIT_LARGE_CANDLEHOLDERS)
            ItemBlockRenderTypes.setRenderLayer(UNLIT_LARGE_CANDLEHOLDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_SOUL_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LARGE_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LARGE_SOUL_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONE_MORTAR_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP_PANICLES.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DEER_ENEITY.get(), DeerEntity.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DEER_ENEITY.get(),
                m -> new CCGenericMobRenderer<>(m, new DeerModel(m.bakeLayer(ModEntityModelLayers.DEER)), 0.7F, "wilddeer.png"));
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.DEER, DeerModel::create);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLClientSetupEvent event) {
        ModScreen.registerScreen();
    }
}
