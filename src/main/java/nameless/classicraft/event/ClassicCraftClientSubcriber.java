package nameless.classicraft.event;

import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

public class ClassicCraftClientSubcriber {

    @SuppressWarnings("removal")
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
    }
}
