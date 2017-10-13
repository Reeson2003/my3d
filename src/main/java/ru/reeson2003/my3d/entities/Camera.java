package ru.reeson2003.my3d.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 13.10.2017.
 */
public class Camera {
    private static final float speed = 0.1f;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;

    public Camera() {
    }

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x -= speed;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
