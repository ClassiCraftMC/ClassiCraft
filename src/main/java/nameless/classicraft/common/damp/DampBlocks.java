package nameless.classicraft.common.damp;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.level.block.Block;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DampBlocks {
    public static final Map<Block, Integer> MAP = ImmutableMap.<Block, Integer>builder()
            .build();
}
