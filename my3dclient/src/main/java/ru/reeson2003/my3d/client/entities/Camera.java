package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public interface Camera {
    Vector3f getPosition();

    float getYaw();

    float getPitch();

    float getRoll();
}
