package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRenderTypeSetup {

    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void onEvent(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CACTUS_FRUIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_RICE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_WALL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SALT_STALACTITE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_SALT_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_SALT_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_SALT_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FIRE_BOWL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_SOUL_FIRE_BOWL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONE_MORTAR_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP_PANICLES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SALT_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AZALEA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHRYSANTHEMUM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEONY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAMPFIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANDESITE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DIORITE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRANITE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SANDSTONE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_SANDSTONE_LOOSE_ROCK.get(), RenderType.cutout());
        ItemProperties.register(ModItems.STONE_JAVELIN.get(), new ResourceLocation(ClassiCraftMod.MODID, "throwing_stone_javelin"), (stack, level, entity, unused) ->
                entity != null && ((entity.isUsingItem() && entity.getUseItem() == stack) || (entity instanceof Monster monster && monster.isAggressive())) ? 1.0F : 0.0F
        );
    }
}
