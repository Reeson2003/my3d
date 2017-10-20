package ru.reeson2003.my3d.common;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class Geometry {
    private float posX;
    private float posY;
    private float posZ;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    public Geometry(float posX, float posY, float posZ, float rotX, float rotY, float rotZ, float scale) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                ", rotX=" + rotX +
                ", rotY=" + rotY +
                ", rotZ=" + rotZ +
                ", scale=" + scale +
                '}';
    }
}
