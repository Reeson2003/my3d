package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.CameraControl;
import ru.reeson2003.my3d.client.control.FreeKeyboardMouseControl;

/**
 * Created by Pavel Gavrilov on 13.10.2017.
 */
public class ControlledCamera implements Camera {

    private CameraControl control;

    public ControlledCamera(float speed, Vector3f position, Vector3f yawPitchRoll, CameraControl control) {
        this.control = control;
    }

    @Override
    public Vector3f getPosition() {
        return control.getPosition();
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
