package ru.reeson2003.my3d.client.tools;

/**
 * Created by Pavel Gavrilov on 19.10.2017.
 */
public final class FloatColour {
    public final float R;
    public final float G;
    public final float B;

    public FloatColour(int red, int green, int blue) {
        this.R = (float) red / 255f;
        this.G = (float) green / 255f;
        this.B = (float) blue / 255f;
    }
}
