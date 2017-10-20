package ru.reeson2003.my3d.client.control;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Toshiba on 15.10.2017.
 */
public interface Control {

    float getSpeed();

    Vector3f getPosition();

    float getRotX();

    float getRotY();

    float getRotZ();
}
