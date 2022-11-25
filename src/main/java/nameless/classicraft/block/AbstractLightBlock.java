package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

/**
 * Abstract Light Block
 * A mod light block should be extended from it
 */
public abstract class AbstractLightBlock extends Block {

    protected static final BooleanProperty LIT = BlockStateProperties.LIT;

    public AbstractLightBlock(Properties pProperties) {
        super(pProperties);
    }

    /**
     * A method to get Lit State
     * @return lit state
     */
    public static BooleanProperty getLitState() {
        return LIT;
    }
}
