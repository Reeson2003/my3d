package ru.reeson2003.my3d.client.tools;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 25.10.2017.
 */
public class ColourVector extends Vector3f {
    public ColourVector(int red, int green, int blue) {
        this.x = (float) red / 255f;
        this.y = (float) green / 255f;
        this.z = (float) blue / 255f;
    }
}
