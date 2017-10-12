package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import ru.reeson2003.my3d.renderEngine.DisplayManager;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 60;

    public static void main(String[] args) {
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS);
            while (!Display.isCloseRequested()) {
                DisplayManager.updateDisplay();
            }
        } catch (LWJGLException e) {
            e.printStackTrace();
        }finally {
            DisplayManager.closeDisplay();
        }
    }
}
