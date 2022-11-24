package nameless.classicraft.util;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

public class RenderUtils {

    public static Quaternion rotateDegreesX(float degrees)
    {
        return Vector3f.XP.rotationDegrees(degrees);
    }

    public static Quaternion rotateDegreesY(float degrees)
    {
        return Vector3f.YP.rotationDegrees(degrees);
    }

    public static Quaternion rotateDegreesZ(float degrees)
    {
        return Vector3f.ZP.rotationDegrees(degrees);
    }
}
