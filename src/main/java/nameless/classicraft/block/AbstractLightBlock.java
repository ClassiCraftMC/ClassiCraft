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
package nameless.classicraft.block;

import nameless.classicraft.init.ModBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

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
