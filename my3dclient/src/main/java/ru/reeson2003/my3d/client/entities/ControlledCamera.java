package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.CameraControl;
import ru.reeson2003.my3d.client.control.FreeKeyboardMouseControl;

/**
 * Created by Pavel Gavrilov on 13.10.2017.
 */
public class ControlledCamera implements Camera {

    private CameraControl control;
    private Vector3f offset;

    public ControlledCamera(Vector3f offset, CameraControl control) {
        this.control = control;
        this.offset = offset;
    }

    @Override
    public Vector3f getPosition() {
        Vector3f pos = control.getPosition();
        Vector3f result = new Vector3f(pos.x + offset.x, pos.y + offset.y, pos.z + offset.z);
        return result;
    }

    @Override
    public float getYaw() {
        return control.getYaw();
    }

    @Override
    public float getPitch() {
        return control.getPitch();
    }

    @Override
    public float getRoll() {
        return control.getRoll();
    }
}
