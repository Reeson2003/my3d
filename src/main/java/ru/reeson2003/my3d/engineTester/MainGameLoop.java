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
        try {
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();


            RawModel model = OBJLoader.loadModel("models/tree/tree.obj", loader);

            TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/tree/tree.png")));

            List<Entity> entities = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 500; i++) {
                entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 800, 0, random.nextFloat() * 600), 0, 0, 0, 3));
            }

            Light light = new Light(new Vector3f(-20000, 20000, 2000), new Vector3f(1, 1, 1));

            Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("textures/grass.png")));
//            Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("textures/grass.png")));

            Camera camera = new Camera(CAMERA_SPEED, new Vector3f(0,1,0), new Vector3f(125, 0, 0));

            MasterRenderer renderer = new MasterRenderer();

            while (!Display.isCloseRequested()) {
                camera.move();

                renderer.processTerrain(terrain);
//                renderer.processTerrain(terrain2);
                for (Entity entity : entities) {
                    renderer.processEntity(entity);
                }
                renderer.render(light, camera);
                DisplayManager.updateDisplay();
            }

            renderer.cleanUp();
            loader.cleanUp();
            DisplayManager.closeDisplay();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
}
