package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public class StaticCamera implements Camera {
    private Vector3f position;
    private float yaw;
    private float pitch;
    private float roll;

    public StaticCamera(Vector3f position, float yaw, float pitch, float roll) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public float getRoll() {
        return roll;
    }
}
