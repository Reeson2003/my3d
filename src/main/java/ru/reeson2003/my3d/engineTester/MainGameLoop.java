package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;
import ru.reeson2003.my3d.entities.Camera;
import ru.reeson2003.my3d.entities.Entity;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.renderEngine.DisplayManager;
import ru.reeson2003.my3d.renderEngine.Loader;
import ru.reeson2003.my3d.models.RawModel;
import ru.reeson2003.my3d.renderEngine.Renderer;
import ru.reeson2003.my3d.shaders.StaticShader;
import ru.reeson2003.my3d.textures.ModelTexture;

import java.util.Random;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int FPS = 120;
    public static final String TITLE = "AWESOME";

    private static final float[] VERTICES = {
            -0.5f, 0.5f, 0f, /*V0*/
            -0.5f, -0.5f, 0f, /*V1*/
            0.5f, -0.5f, 0f, /*V2*/
            0.5f, 0.5f, 0f, /*V3*/
    };

    private static final int[] INDICES = {
            0, 1, 3, /*TOP LEFT TRIANGLE (V0, V1, V3)*/
            3, 1, 2 /*TOP RIGHT TRIANGLE (V3, V1, V2)*/
    };

    private static final float[] TEXTURE_COORDINATES = {
            0, 0, /*V0*/
            0, 1, /*V1*/
            1, 1, /*V2*/
            1, 0 /*V3*/
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
            RawModel model = loader.loadToVAO(VERTICES, TEXTURE_COORDINATES, INDICES);
            ModelTexture texture = new ModelTexture(loader.loadTexture("textures/mario.png"));
            TexturedModel texturedModel = new TexturedModel(model, texture);
            Random random = new Random(System.nanoTime());
//            Entity entity = new Entity(texturedModel, new Vector3f(0,0,-3), 0, 0, 0, 1);
            Camera camera = new Camera();
            Entity[] entities = new Entity[5000];
            float[] speeds = new float[entities.length];
            for (int i = 0; i < entities.length; i++) {
                entities[i] = generateEntity(texturedModel, random);
                speeds[i] = getRandomFloat(random, -0.05f, -0.001f);
            }
            while (!Display.isCloseRequested()) {
                camera.move();
                renderer.prepare();
                shader.start();
                shader.loadViewMatrix(camera);
//                renderer.render(entity, shader);
                for (int i = 0; i < entities.length; i++) {
                    renderer.render(entities[i], shader);
//                    entities[i].increaseRotation(1f, 1f, 1f);
//                    entities[i].increasePosition(0, 0, speeds[i]);
                }
                shader.stop();
                DisplayManager.updateDisplay();
            }

        } catch (LWJGLException e) {
            e.printStackTrace();
        } finally {
            if (shader != null)
                shader.cleanUp();
            if (loader != null)
                loader.cleanUp();
            DisplayManager.closeDisplay();
        }
    }

    private static Entity generateEntity(TexturedModel model, Random random) {
        final float tMax = 100f;
        final float tMin = -100f;
        float tx = getRandomFloat(random, tMin, tMax);
        float ty = getRandomFloat(random, tMin, tMax);
        float tz = getRandomFloat(random, tMin, tMax);
        Vector3f v = new Vector3f(tx, ty, tz);
        final float rMax = 100f;
        final float rMin = -100f;
        float rx = getRandomFloat(random, rMin, rMax);
        float ry = getRandomFloat(random, rMin, rMax);
        float rz = getRandomFloat(random, rMin, rMax);
        float sMax = 5f;
        float sMin = 5F;
        float s = getRandomFloat(random, sMin, sMax);
        return new Entity(model, v, rx, ry, rz, s);
    }

    private static float getRandomFloat(Random random, float min, float max) {
        return min + random.nextFloat() * (max - min);
    }
}
