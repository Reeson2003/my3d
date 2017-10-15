package ru.reeson2003.my3d.entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Toshiba on 15.10.2017.
 */
public interface CameraControl {
    void move();

    float getSpeed();

    Vector3f getPosition();

    float getYaw();

    float getPitch();

    float getRoll();
}
