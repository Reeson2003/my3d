package ru.reeson2003.my3d.client.control;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.ticker.Ticker;
import ru.reeson2003.my3d.client.ticker.TickerImpl;
import ru.reeson2003.my3d.client.ticker.TickerListener;

/**
 * Created by Pavel Gavrilov on 17.10.2017.
 */
public class FlatKeyboardMouseControl implements Control, TickerListener {
    private float speed = 0.1f;
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f yawPitchRoll = new Vector3f(0, 0, 0);

    public FlatKeyboardMouseControl(float speed, Vector3f position, Vector3f yawPitchRoll, Ticker ticker) {
        this(speed, ticker);
        this.position = position;
        this.yawPitchRoll = yawPitchRoll;
    }

    public FlatKeyboardMouseControl(float speed, Ticker ticker) {
        if (ticker != null)
            ticker.addListener(this);
        this.speed = speed;
    }

    @Override
    public void tick() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= speed * Math.cos(Math.toRadians(yawPitchRoll.getX()));
            position.x += speed * Math.sin(Math.toRadians(yawPitchRoll.getX()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += speed * Math.cos(Math.toRadians(yawPitchRoll.getX()));
            position.x -= speed * Math.sin(Math.toRadians(yawPitchRoll.getX()));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.z -= speed * (Math.cos(Math.toRadians(yawPitchRoll.getX()) - Math.PI / 2d));
            position.x += speed * (Math.sin(Math.toRadians(yawPitchRoll.getX()) - Math.PI / 2d));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.z += speed * (Math.cos(Math.toRadians(yawPitchRoll.getX()) - Math.PI / 2d));
            position.x -= speed * (Math.sin(Math.toRadians(yawPitchRoll.getX()) - Math.PI / 2d));
        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
//            position.y += speed;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
//            position.y -= speed;
//        }
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
            increasePitch(dy);
            int dz = Mouse.getDWheel();
            yawPitchRoll.z += dz * speed;
        }
    }

    private void increasePitch(int delta) {
        float res = yawPitchRoll.y + delta * speed;
        if (res > 90)
            res = 90;
        else if (res < -90)
            res = -90;
        yawPitchRoll.y = res;
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
    public float getRotX() {
        return yawPitchRoll.x;
    }

    @Override
    public float getRotY() {
        return yawPitchRoll.y;
    }

    @Override
    public float getRotZ() {
        return yawPitchRoll.z;
    }

    private void jump() {

    }
}
