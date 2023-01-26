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
package nameless.classicraft.init;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

    public static final int TICK_INTERVAL = 1200;
    public static final int TORCH_INITIAL_BURN_TIME = 17;
    public static final int SOUL_TORCH_INITIAL_BURN_TIME = 16;
    public static final IntegerProperty TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, TORCH_INITIAL_BURN_TIME);
    public static final IntegerProperty SOUL_TORCH_BURN_TIME = IntegerProperty.create("burntime", 0, 16);
    public static final IntegerProperty LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 9);
    public static final IntegerProperty SOUL_LANTERN_BURN_TIME = IntegerProperty.create("burntime", 0, 8);
    public static final IntegerProperty FULE_LEVEL = IntegerProperty.create("fuel_level", 0, 4);
}
