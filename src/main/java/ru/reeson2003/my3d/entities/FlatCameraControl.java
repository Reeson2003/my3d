package ru.reeson2003.my3d.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Toshiba on 15.10.2017.
 */
public class FlatCameraControl implements CameraControl {
    private float speed = 0.1f;
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f yawPitchRoll = new Vector3f(0, 0, 0);

    public FlatCameraControl(float speed, Vector3f position, Vector3f yawPitchRoll) {
        this.speed = speed;
        this.position = position;
        this.yawPitchRoll = yawPitchRoll;
    }

    public FlatCameraControl(float speed) {
        this.speed = speed;
    }

    @Override
    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//            position.z -= speed;
            position.z -= speed * Math.cos(Math.toRadians(yawPitchRoll.getX()));
            position.x += speed * Math.sin(Math.toRadians(yawPitchRoll.getX()));
            position.y -= speed * Math.sin(Math.toRadians(yawPitchRoll.getY()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += speed * Math.cos(Math.toRadians(yawPitchRoll.getX()));
            position.x -= speed * Math.sin(Math.toRadians(yawPitchRoll.getX()));
            position.y += speed * Math.sin(Math.toRadians(yawPitchRoll.getY()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.z -= speed * (Math.cos(Math.toRadians(yawPitchRoll.getX()) - Math.PI/2d));
            position.x += speed * (Math.sin(Math.toRadians(yawPitchRoll.getX()) - Math.PI/2d));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.z += speed * (Math.cos(Math.toRadians(yawPitchRoll.getX()) - Math.PI/2d));
            position.x -= speed * (Math.sin(Math.toRadians(yawPitchRoll.getX()) - Math.PI/2d));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            position.y += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            position.y -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            yawPitchRoll.x += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            yawPitchRoll.x -= speed;
        }
        if (Mouse.isButtonDown(0)) {
            int dx = Mouse.getDX();
            yawPitchRoll.x += dx * speed;
            int dy = Mouse.getDY();
            yawPitchRoll.y += dy * speed;
            int dz = Mouse.getDWheel();
            yawPitchRoll.z += dz * speed;
        }
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public float getYaw() {
        return yawPitchRoll.x;
    }

    @Override
    public float getPitch() {
        return yawPitchRoll.y;
    }

    @Override
    public float getRoll() {
        return yawPitchRoll.z;
    }
}
