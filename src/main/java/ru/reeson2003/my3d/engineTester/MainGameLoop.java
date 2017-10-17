package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;
import ru.reeson2003.my3d.entities.Camera;
import ru.reeson2003.my3d.entities.Entity;
import ru.reeson2003.my3d.entities.Light;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.renderEngine.DisplayManager;
import ru.reeson2003.my3d.renderEngine.Loader;
import ru.reeson2003.my3d.models.RawModel;
import ru.reeson2003.my3d.renderEngine.OBJLoader;
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
    public static final int FPS = 60;
    public static final String TITLE = "AWESOME";
    public static final float CAMERA_SPEED = 0.5f;


    public static void main(String[] args) {
        Loader loader = null;
        Renderer renderer = null;
        StaticShader shader = null;
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            loader = new Loader();
            renderer = new Renderer();
            shader = new StaticShader();
            Shape shape = new Cube();
            RawModel model = OBJLoader.loadModel("models/dragon/dragon.obj", loader);
            ModelTexture texture = new ModelTexture(loader.loadTexture("models/dragon/dragon.png"));
            texture.setReflectivity(10f);
            texture.setShineDamper(50f);
            TexturedModel texturedModel = new TexturedModel(model, texture);
            Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);
            Light cold = new Light(new Vector3f(-25, 25, -25), new Vector3f(1f, 0.3f, 0.1f));
            Camera camera = new Camera(CAMERA_SPEED);
            while (!Display.isCloseRequested()) {
                camera.move();
                renderer.prepare();
                shader.start();
                shader.loadLight(cold);
                shader.loadViewMatrix(camera);
                renderer.render(entity, shader);
                entity.increaseRotation(0, 0.5f, 0);
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
