package org.bedracket.classicraft.init;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ModBlockRenderTypes {

    public static void registerBlockRenderTypes() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REAL_TORCH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REAL_WALL_TORCH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REAL_SOUL_TORCH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REAL_SOUL_WALL_TORCH.get(), RenderType.cutout());
    }
}
