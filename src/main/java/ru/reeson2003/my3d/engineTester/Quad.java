package ru.reeson2003.my3d.engineTester;

/**
 * Created by Toshiba on 15.10.2017.
 */
public class Quad implements Shape {
    private static final float[] VERTICES = {
            -0.5f, 0.5f, 0f, /*V0*/
            -0.5f, -0.5f, 0f, /*V1*/
            0.5f, -0.5f, 0f, /*V2*/
            0.5f, 0.5f, 0f, /*V3*/
    };

    private static final int[] INDICES = {
            0, 1, 3, /*TOP LEFT TRIANGLE (V0, V1, V3)*/
            3, 1, 2 /*TOP RIGHT TRIANGLE (V3, V1, V2)*/
    };

    private static final float[] TEXTURE_COORDINATES = {
            0, 0, /*V0*/
            0, 1, /*V1*/
            1, 1, /*V2*/
            1, 0 /*V3*/
    };

    @Override
    public float[] getVreticles() {
        return VERTICES;
    }

    @Override
    public int[] getIndices() {
        return INDICES;
    }

    @Override
    public float[] getTextureCoordinates() {
        return TEXTURE_COORDINATES;
    }
}
