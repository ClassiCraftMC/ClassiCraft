package nameless.classicraft.init;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public class Blocks{
        public static final TagKey<Block> TORCH_CAN_BE_BURNT_OUT = register("torch_can_be_burnt_out");

        private static TagKey<Block> register(String name)
        {
            return BlockTags.create(new ResourceLocation(ClassiCraftMod.MODID, name));
        }
    }
}
