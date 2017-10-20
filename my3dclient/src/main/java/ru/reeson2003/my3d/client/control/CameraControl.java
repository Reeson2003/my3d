package ru.reeson2003.my3d.client.control;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public class CameraControl {
    private Control origin;

    public CameraControl(Control origin) {
        this.origin = origin;
    }

    public float getSpeed() {
        return origin.getSpeed();
    }

    public Vector3f getPosition() {
        return origin.getPosition();
    }

    public float getYaw() {
        return origin.getRotX();
    }

    public float getPitch() {
        return origin.getRotY();
    }

    public float getRoll() {
        return origin.getRotZ();
    }
}
