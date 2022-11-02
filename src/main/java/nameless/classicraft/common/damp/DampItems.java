package nameless.classicraft.common.damp;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.item.Item;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DampItems {
    public static final Map<Item, Integer> MAP = ImmutableMap.<Item, Integer>builder()
            .build();
}
