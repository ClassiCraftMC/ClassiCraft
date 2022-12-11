package nameless.classicraft.block;

import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

/**
 * Abstract Light Block
 * A mod light block should be extended from it
 */
public abstract class AbstractLightBlock extends Block {

    protected static final BooleanProperty LIT = Properties.LIT;
    protected static final IntProperty FULE_LEVEL = ModBlockProperties.FULE_LEVEL;

    public AbstractLightBlock(Settings arg) {
        super(arg);
    }

    /**
     * A method to get Lit State
     * @return lit state
     */
    public static BooleanProperty getLitState() {
        return LIT;
    }

    /**
     * A method to get Fuel Level
     * @return level
     */
    public static IntProperty getLevel() {
        return FULE_LEVEL;
    }
}
