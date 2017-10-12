package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import ru.reeson2003.my3d.renderEngine.DisplayManager;
import ru.reeson2003.my3d.renderEngine.Loader;
import ru.reeson2003.my3d.renderEngine.RawModel;
import ru.reeson2003.my3d.renderEngine.Renderer;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 60;

    private static final float[] VERTICLES = {
            /*left bottom triangle*/
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            /*right top triangle*/
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
    };

    public static void main(String[] args) {
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS);
            RawModel model = loader.loadToVAO(VERTICLES);
            while (!Display.isCloseRequested()) {
                renderer.prepare();
                renderer.render(model);
                DisplayManager.updateDisplay();
            }
        } catch (LWJGLException e) {
            e.printStackTrace();
        }finally {
            loader.cleanUp();
            DisplayManager.closeDisplay();
        }
    }
}
