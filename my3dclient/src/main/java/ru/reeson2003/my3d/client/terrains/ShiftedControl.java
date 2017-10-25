package ru.reeson2003.my3d.client.terrains;

import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.Control;

/**
 * Created by Pavel Gavrilov on 25.10.2017.
 */
public class ShiftedControl implements Control {
    private Control origin;
    private Vector3f shift;

    public ShiftedControl(Control origin, Vector3f shift) {
        this.origin = origin;
        this.shift = shift;
    }

    @Override
    public float getSpeed() {
        return origin.getSpeed();
    }

    @Override
    public Vector3f getPosition() {
        float z = origin.getPosition().z + (float) (shift.z * Math.sin(Math.toRadians(origin.getRotZ())));
        float x = origin.getPosition().x + (float) (shift.x * Math.sin(Math.toRadians(origin.getRotX())));
        float y = origin.getPosition().y + (float) (shift.y * Math.sin(Math.toRadians(origin.getRotY())));
        System.out.println(x + " " + y + " " + z);
        return new Vector3f(x, y, z);
    }

    @Override
    public float getRotX() {
        return 0;
    }

    @Override
    public float getRotY() {
        return 0;
    }

    @Override
    public float getRotZ() {
        return 0;
    }
}
