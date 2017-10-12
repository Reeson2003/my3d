package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import ru.reeson2003.my3d.renderEngine.DisplayManager;
import ru.reeson2003.my3d.renderEngine.Loader;
import ru.reeson2003.my3d.renderEngine.RawModel;
import ru.reeson2003.my3d.renderEngine.Renderer;
import ru.reeson2003.my3d.shaders.StaticShader;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 60;
    public static final String TITLE = "AWESOME";

    private static final float[] VERTICES = {
            -0.5f, 0.5f, 0.4f, /*V0*/
            -0.5f, -0.5f, 0f, /*V1*/
            0.5f, -0.5f, 0f, /*V2*/
            0.5f, 0.5f, 0f, /*V3*/
    };

    private static final int[] INDICES = {
            0, 1, 3, /*TOP LEFT TRIANGLE (V0, V1, V3)*/
            3, 1, 2 /*TOP RIGHT TRIANGLE (V3, V1, V2)*/
    };

    public static void main(String[] args) {
        Loader loader = null;
        Renderer renderer = null;
        StaticShader shader = null;
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            loader = new Loader();
            renderer = new Renderer();
            shader = new StaticShader();
            RawModel model = loader.loadToVAO(VERTICES, INDICES);
            while (!Display.isCloseRequested()) {
                renderer.prepare();
                shader.start();
                renderer.render(model);
                shader.stop();
                DisplayManager.updateDisplay();
            }
        } catch (LWJGLException e) {
            e.printStackTrace();
        } finally {
            shader.cleanUp();
            loader.cleanUp();
            DisplayManager.closeDisplay();
        }
    }
}
