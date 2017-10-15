package ru.reeson2003.my3d.entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 13.10.2017.
 */
public class Camera {

    private CameraControl control;

    public Camera(float speed) {
        this.control = new FlatCameraControl(speed);
    }

    public Camera(float speed, Vector3f position, Vector3f yawPitchRoll) {
        this.control = new FlatCameraControl(speed, position, yawPitchRoll);
    }

    public void move() {
        this.control.move();
    }

    public Vector3f getPosition() {
        return control.getPosition();
    }

    public float getSpeed() {
        return control.getSpeed();
    }

    public float getYaw() {
        return control.getYaw();
    }

    public float getPitch() {
        return control.getPitch();
    }

    public float getRoll() {
        return control.getRoll();
    }
}
