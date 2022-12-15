package org.bedracket.classicraft.init;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.renderer.RenderType;

public class ModBlockRenderLayers {

    public static void registerBlockRenderLayers() {
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.REAL_TORCH.get());
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.REAL_WALL_TORCH.get());
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.REAL_SOUL_TORCH.get());
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.REAL_SOUL_WALL_TORCH.get());
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.ROSE.get());
        RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.MOSSY_BRICKS_STAIRS.get());
    }
}