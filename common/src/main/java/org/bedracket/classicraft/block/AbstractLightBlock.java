package org.bedracket.classicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bedracket.classicraft.init.ModBlockProperties;

/**
 * Abstract Light Block
 * A mod light block should be extended from it
 */
public abstract class AbstractLightBlock extends Block {

    protected static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected static final IntegerProperty FULE_LEVEL = ModBlockProperties.FULE_LEVEL;

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

    /**
     * A method to get Fuel Level
     * @return level
     */
    public static IntegerProperty getLevel() {
        return FULE_LEVEL;
    }
}
