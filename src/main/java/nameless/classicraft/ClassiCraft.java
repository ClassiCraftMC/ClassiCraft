package nameless.classicraft;

import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.item.ModItems;
import com.mojang.logging.LogUtils;
import nameless.classicraft.common.menu.ModMenuTypes;
import nameless.classicraft.event.ClassiCraftSubcriber;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

/**
 * ClassicCraft 模组主类
 * modEventBus 用于注册
 * 使用例子:
 * ExampeModItems.ITEMS.register(modEventBus);
 * ExampeModBlocks.BLOCKS.register(modEventBus);
 */
@Mod(ClassiCraft.MODID)
public class ClassiCraft {

    public static final String MODID = "classicraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("deprecation")
    public ClassiCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (Items.DRIED_KELP.getFoodProperties() != null) {
            Items.DRIED_KELP.getFoodProperties().canAlwaysEat = true;
            Items.DRIED_KELP.getFoodProperties().nutrition = 0;
            Items.DRIED_KELP.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.CHORUS_FRUIT.getFoodProperties() != null) {
            Items.CHORUS_FRUIT.getFoodProperties().canAlwaysEat = true;
            Items.CHORUS_FRUIT.getFoodProperties().nutrition = 0;
            Items.CHORUS_FRUIT.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.ROTTEN_FLESH.getFoodProperties() != null) {
            Items.ROTTEN_FLESH.getFoodProperties().canAlwaysEat = true;
            Items.ROTTEN_FLESH.getFoodProperties().nutrition = 0;
            Items.ROTTEN_FLESH.getFoodProperties().saturationModifier = 0.0F;
        }
        modEventBus.addListener(this::renderRegister);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenuTypes.REGISTER.register(modEventBus);
        ModBlockEntities.REGISTER.register(modEventBus);
        ClassiCraftSubcriber.init();
    }

    @SuppressWarnings("removal")
    private void renderRegister(FMLClientSetupEvent event) {
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
