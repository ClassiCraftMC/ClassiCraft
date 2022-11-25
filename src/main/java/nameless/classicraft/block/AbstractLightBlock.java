package nameless.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.ToIntFunction;

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
     * A method to switch light value
     * @return int value about light emmison
     */
    static ToIntFunction<BlockState> getLightFromState(){
        return null;
    }

    /**
     * A method to get burn time
     * @return burn time
     */
    abstract IntegerProperty getBurnTime();

    /**
     * A method to get initial burn time
     * @return initial burn time
     */
    abstract int getInitialBurnTime();

    /**
     * A method to get Lit State
     * @return lit state
     */
    public static BooleanProperty getLitState() {
        return LIT;
    }
}
