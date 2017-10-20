package ru.reeson2003.my3d.client.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.*;
import ru.reeson2003.my3d.client.entities.*;
import ru.reeson2003.my3d.client.models.RawModel;
import ru.reeson2003.my3d.client.models.TexturedModel;
import ru.reeson2003.my3d.client.renderEngine.DisplayManager;
import ru.reeson2003.my3d.client.renderEngine.Loader;
import ru.reeson2003.my3d.client.renderEngine.MasterRenderer;
import ru.reeson2003.my3d.client.renderEngine.OBJLoader;
import ru.reeson2003.my3d.client.rest.RestLoader;
import ru.reeson2003.my3d.client.terrains.Terrain;
import ru.reeson2003.my3d.client.textures.ModelTexture;
import ru.reeson2003.my3d.client.textures.TerrainTexture;
import ru.reeson2003.my3d.client.textures.TerrainTexturePack;
import ru.reeson2003.my3d.client.ticker.Ticker;
import ru.reeson2003.my3d.client.ticker.TickerImpl;
import ru.reeson2003.my3d.common.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            Ticker ticker = new TickerImpl(1000);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();

            List<Entity> entities = generateEntities(loader);
            Entity controlable = loadPlayer(loader);
            Control entityControl = new RestFlatControl(10L, 1f, new Vector3f(100, 0, 100), new Vector3f(0, 0, 0), ticker);
            entities.add(new ControlableEntity(controlable, entityControl));

            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(-2000, 2000, 200), new Vector3f(0.9f, 0.9f, 0.9f));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);
//            Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("textures/grass.png")));
//            CameraControl cameraControl = new CameraControl(entityControl);
//            Camera camera = new ControlledCamera(CAMERA_SPEED, new Vector3f(0, 10, 0), new Vector3f(125, 0, 0), cameraControl);
            Camera camera = new StaticCamera(new Vector3f(0, 100, 0), 130, 20, 0);

            MasterRenderer renderer = new MasterRenderer();

            entities.add(loadPlayer(loader));
            int index = entities.size() - 1;

            while (!Display.isCloseRequested()) {
                ticker.tick();

                renderer.processTerrain(terrain);
//                Geometry g = getGeometry(10L);
//                entities.get(index).getPosition().set(g.getPosX() + 5, g.getPosY(), g.getPosZ());
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
        RestLoader restLoader = new RestLoader();
        Map<Long, List<Geometry>> geometries = restLoader.loadTerrainObjects();
        RawModel model = OBJLoader.loadModel("models/lowPolyTree/lowPolyTree.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/lowPolyTree/lowPolyTree.png")));
        List<Geometry> geometryList = geometries.get(1L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        model = OBJLoader.loadModel("models/tree/tree.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/tree/tree.png")));
        geometryList = geometries.get(2L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        model = OBJLoader.loadModel("models/grass/grassModel.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/grassTexture.png")));
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        geometryList = geometries.get(3L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/flower.png")));
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        geometryList = geometries.get(4L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        model = OBJLoader.loadModel("models/fern/fern.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/fern/fern.png")));
        staticModel.getTexture().setHasTransparency(true);
        geometryList = geometries.get(5L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
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

    private static Entity loadPlayer(Loader loader) {
        RawModel model = OBJLoader.loadModel("models/player/person.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/player/playerTexture.png")));
        return new StaticEntity(staticModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
    }

    private static Geometry getGeometry(Long id) {
        return new RestLoader().loadEntityObjects().get(id);
    }
}
