/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.block.StonePebbleBlock;
import nameless.classicraft.client.model.OceanSharkModel;
import nameless.classicraft.client.renderer.LivingDeadRenderer;
import nameless.classicraft.client.renderer.OceanSharkRenderer;
import nameless.classicraft.client.renderer.TroutRenderer;
import nameless.classicraft.entity.LivingDead;
import nameless.classicraft.entity.OceanShark;
import nameless.classicraft.entity.Trout;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModEntityModelLayers;
import nameless.classicraft.init.ModItems;
import nameless.classicraft.item.DepthMeterItem;
import nameless.classicraft.util.Helpers;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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
        event.put(ModEntities.TROUT_ENTITY.get(), Trout.registerAttributes().build());
        event.put(ModEntities.OCEAN_SHARK_ENTITY.get(), OceanShark.registerAttributes().build());
        event.put(ModEntities.LIVING_DEAD.get(), LivingDead.registerAttributes().build());
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
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TWIGS.get(), RenderType.cutout());
        for (Block block : Helpers.getBlocks()) {
            if (block instanceof WallBlock
                    || block instanceof StairBlock
                    || block instanceof SlabBlock) {
                ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());
            }
            if (block instanceof StonePebbleBlock) {
                ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
            }
        }
        ItemProperties.register(ModItems.DEPTH_METER.get(), new ResourceLocation("angle"),
                new CompassItemPropertyFunction((level, stack, pEntity)
                        -> DepthMeterItem.isLodestoneCompass(stack) ?
                        DepthMeterItem.getLodestonePosition(stack.getOrCreateTag()) :
                        DepthMeterItem.getSpawnPosition(level)));
    }
}
