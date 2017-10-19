package ru.reeson2003.my3d.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.control.Control;
import ru.reeson2003.my3d.control.FlatKeyboardMouseControl;
import ru.reeson2003.my3d.entities.*;
import ru.reeson2003.my3d.models.RawModel;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.renderEngine.DisplayManager;
import ru.reeson2003.my3d.renderEngine.Loader;
import ru.reeson2003.my3d.renderEngine.MasterRenderer;
import ru.reeson2003.my3d.renderEngine.OBJLoader;
import ru.reeson2003.my3d.terrains.Terrain;
import ru.reeson2003.my3d.textures.ModelTexture;
import ru.reeson2003.my3d.textures.TerrainTexture;
import ru.reeson2003.my3d.textures.TerrainTexturePack;
import ru.reeson2003.my3d.ticker.Ticker;
import ru.reeson2003.my3d.ticker.TickerImpl;

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
            Ticker ticker = new TickerImpl(20);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();

            List<Entity> entities = generateEntities(loader);
//            Entity controlable = new StaticEntity(staticModel, new Vector3f(10, 0, 10), 0, 0, 0, 3 * 5);
//            Control entityControl = new FlatKeyboardMouseControl(1f, new Vector3f(100, 0, 100), new Vector3f(0, 0, 0));
//            entities.add(new ControlableEntity(controlable, entityControl));

            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(-2000, 2000, 200), new Vector3f(0.5f, 0.5f, 0.5f));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);
//            Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("textures/grass.png")));

            Camera camera = new FreeCamera(CAMERA_SPEED, new Vector3f(0,1,0), new Vector3f(125, 0, 0));
//            Camera camera = new StaticCamera(new Vector3f(0, 100, 0), 130, 20, 0);

            MasterRenderer renderer = new MasterRenderer();

            while (!Display.isCloseRequested()) {
                ticker.tick();

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

    private static List<Entity> generateEntities(Loader loader) {
        List<Entity> entities = new ArrayList<>();
        RawModel model = OBJLoader.loadModel("models/lowPolyTree/lowPolyTree.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/lowPolyTree/lowPolyTree.png")));
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            entities.add(new StaticEntity(staticModel, new Vector3f(25 + random.nextFloat() * 800, 0, 25 +random.nextFloat() * 600), 0, random.nextFloat()*360, 0, 1f));
        }
        model = OBJLoader.loadModel("models/tree/tree.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/tree/tree.png")));
        for (int i = 0; i < 100; i++) {
            entities.add(new StaticEntity(staticModel, new Vector3f(25 + random.nextFloat() * 800, 0, 25 +random.nextFloat() * 600), 0, 0, 0, 6f));
        }
        model = OBJLoader.loadModel("models/grass/grassModel.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/grassTexture.png")));
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        for (int i = 0; i < 50; i++) {
            entities.add(new StaticEntity(staticModel, new Vector3f(25 + random.nextFloat() * 800, 0, 25 +random.nextFloat() * 600), 0, 0, 0, 2));
        }
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/flower.png")));
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        for (int i = 0; i < 50; i++) {
            entities.add(new StaticEntity(staticModel, new Vector3f(25 + random.nextFloat() * 800, 0, 25 +random.nextFloat() * 600), 0, 0, 0, 2));
        }
        model = OBJLoader.loadModel("models/fern/fern.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/fern/fern.png")));
        staticModel.getTexture().setHasTransparency(true);
        for (int i = 0; i < 100; i++) {
            entities.add(new StaticEntity(staticModel, new Vector3f(25 + random.nextFloat() * 800, 0, 25 +random.nextFloat() * 600), 0, 0, 0, 1.2f));
        }
        return entities;
    }

    private static TerrainTexturePack getTexturePack(Loader loader) {
        TerrainTexture background = new TerrainTexture(loader.loadTexture("textures/grassy2.png"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("textures/mud.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textures/grassFlowers.png"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("textures/path.png"));
        return new TerrainTexturePack(background, rTexture, gTexture, bTexture);
    }
}
