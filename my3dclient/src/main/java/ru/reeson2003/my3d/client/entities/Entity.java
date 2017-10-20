package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.models.TexturedModel;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public interface Entity {
    TexturedModel getModel();

    Vector3f getPosition();

    float getRotX();

    float getRotY();

    float getRotZ();

    float getScale();
}
