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
package nameless.classicraft.rot;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RotHolder {
    final float max;
    float current;

    public float percent() {
        return current / max;
    }

    public RotLevel getLevel() {
        float percent = percent();

        if (percent >= .75) {
            return RotLevel.FRESH;
        }
        else if (percent >= .51) {
            return RotLevel.STALE;
        }
        else if (percent >= .26) {
            return RotLevel.SPOILED;
        }
        else {
            return RotLevel.ROT;
        }
    }

    public enum RotLevel {
        FRESH,
        STALE,
        SPOILED,
        ROT
    }
}
