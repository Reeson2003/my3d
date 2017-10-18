package ru.reeson2003.my3d.entities;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.control.Control;
import ru.reeson2003.my3d.models.TexturedModel;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public class ControlableEntity implements Entity{
    private Entity origin;
    private Control control;

    public ControlableEntity(Entity origin, Control control) {
        this.origin = origin;
        this.control = control;
    }


    @Override
    public TexturedModel getModel() {
        return origin.getModel();
    }

    @Override
    public Vector3f getPosition() {
        return control.getPosition();
    }

    @Override
    public float getRotX() {
        return control.getRotX();
    }

    @Override
    public float getRotY() {
        return control.getRotY();
    }

    @Override
    public float getRotZ() {
        return control.getRotZ();
    }

    @Override
    public float getScale() {
        return origin.getScale();
    }
}
