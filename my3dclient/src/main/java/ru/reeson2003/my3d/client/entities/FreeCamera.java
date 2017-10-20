package ru.reeson2003.my3d.client.entities;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.CameraControl;
import ru.reeson2003.my3d.client.control.FreeKeyboardMouseControl;

/**
 * Created by Pavel Gavrilov on 13.10.2017.
 */
public class FreeCamera implements Camera {

    private CameraControl control;

    public FreeCamera(float speed) {
        this.control = new CameraControl(new FreeKeyboardMouseControl(speed));
    }

    public FreeCamera(float speed, Vector3f position, Vector3f yawPitchRoll) {
        this.control = new CameraControl(new FreeKeyboardMouseControl(speed, position, yawPitchRoll));
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
