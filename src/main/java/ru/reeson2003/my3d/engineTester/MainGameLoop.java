package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.entities.Camera;
import ru.reeson2003.my3d.entities.Entity;
import ru.reeson2003.my3d.entities.Light;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.renderEngine.*;
import ru.reeson2003.my3d.models.RawModel;
import ru.reeson2003.my3d.shaders.StaticShader;
import ru.reeson2003.my3d.terrains.Terrain;
import ru.reeson2003.my3d.textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
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
        EntityRenderer entityRenderer = null;
        StaticShader shader = null;
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            loader = new Loader();
            shader = new StaticShader();
            RawModel model = OBJLoader.loadModel("models/tree/tree.obj", loader);
            ModelTexture texture = new ModelTexture(loader.loadTexture("models/tree/tree.png"));
            texture.setReflectivity(1f);
            texture.setShineDamper(100f);
            TexturedModel texturedModel = new TexturedModel(model, texture);
//            Entity entity = new Entity(texturedModel, new Vector3f(100, 0, 100), 0, 0, 0, 1);
            Light cold = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1f, 1f, 1f));
            ModelTexture grass = new ModelTexture(loader.loadTexture("textures/grass.png"));
            Terrain terrain = new Terrain(0, 0, loader, grass);
            Terrain terrain2 = new Terrain(1, 0, loader, grass);
            Random random = new Random(System.nanoTime());
            List<Entity> entities = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                entities.add(generateEntity(texturedModel, random));
            }
            Camera camera = new Camera(CAMERA_SPEED, new Vector3f(120, 10, 150), new Vector3f(-30, 0, 0));

            MasterRenderer masterRenderer = new MasterRenderer();
            while (!Display.isCloseRequested()) {
                camera.move();
                for (Entity entity : entities) {
                    masterRenderer.processEntity(entity);
//                    entity.increasePosition(0,0,-10f);
//                    entity.increaseRotation(1f, 1f, 0);
                }
                masterRenderer.processTerrain(terrain);
                masterRenderer.processTerrain(terrain2);
//                masterRenderer.processEntity(entity);
                masterRenderer.render(cold, camera);
                DisplayManager.updateDisplay();
            }
            masterRenderer.cleanUp();
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
        final float tMax = 800f;
        final float tMin = 0f;
        float tx = getRandomFloat(random, tMin, tMax);
//        float ty = getRandomFloat(random, tMin, tMax);
        float ty = 0;
        float tz = getRandomFloat(random, tMin, tMax);
        Vector3f v = new Vector3f(tx, ty, tz);
        final float rMax = 100f;
        final float rMin = -100f;
//        float rx = getRandomFloat(random, rMin, rMax);
//        float ry = getRandomFloat(random, rMin, rMax);
//        float rz = getRandomFloat(random, rMin, rMax);
        float rx = 0;
        float ry = 0;
        float rz = 0;
        float sMax = 5f;
        float sMin = 5F;
        float s = getRandomFloat(random, sMin, sMax);
        return new Entity(model, v, rx, ry, rz, s);
    }

    private static float getRandomFloat(Random random, float min, float max) {
        return min + random.nextFloat() * (max - min);
    }
}
