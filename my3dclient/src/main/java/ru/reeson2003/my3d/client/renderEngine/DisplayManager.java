package ru.reeson2003.my3d.client.renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class DisplayManager {
    private static int fpsCap = 60;

    public static void createDisplay(int width, int height) throws LWJGLException {
        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.create(new PixelFormat(), attribs);
        GL11.glViewport(0, 0, width, height);
    }

    public static void createDisplay(int width, int height, String title) throws LWJGLException {
        createDisplay(width, height);
        Display.setTitle(title);
    }

    public static void createDisplay(int width, int height, int fps) throws LWJGLException {
        fpsCap = fps;
        createDisplay(width, height);
    }

    public static void createDisplay(int width, int height, int fps, String title) throws LWJGLException {
        fpsCap = fps;
        createDisplay(width, height, title);
    }

    public static void updateDisplay() {
        Display.sync(fpsCap);
        Display.update();
    }

    public static void closeDisplay() {
        Display.destroy();
    }
}
