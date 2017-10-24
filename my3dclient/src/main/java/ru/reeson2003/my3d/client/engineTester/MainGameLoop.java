package ru.reeson2003.my3d.client.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.CameraControl;
import ru.reeson2003.my3d.client.control.Control;
import ru.reeson2003.my3d.client.control.FreeKeyboardMouseControl;
import ru.reeson2003.my3d.client.entities.*;
import ru.reeson2003.my3d.client.models.RawModel;
import ru.reeson2003.my3d.client.models.TexturedModel;
import ru.reeson2003.my3d.client.renderEngine.DisplayManager;
import ru.reeson2003.my3d.client.renderEngine.Loader;
import ru.reeson2003.my3d.client.renderEngine.MasterRenderer;
import ru.reeson2003.my3d.client.renderEngine.OBJLoader;
import ru.reeson2003.my3d.client.terrains.Terrain;
import ru.reeson2003.my3d.client.textures.ModelTexture;
import ru.reeson2003.my3d.client.textures.TerrainTexture;
import ru.reeson2003.my3d.client.textures.TerrainTexturePack;
import ru.reeson2003.my3d.client.ticker.Ticker;
import ru.reeson2003.my3d.client.ticker.TickerImpl;

import java.util.ArrayList;
import java.util.List;

//import ru.reeson2003.my3d.transport.rest.RestLoader;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int FPS = 60;
    public static final String TITLE = "AWESOME";
    public static final float CAMERA_SPEED = 0.5f;

    public static final String SERVER_URL = "http://192.168.1.50:8080";


    public static void main(String[] args) {
//        RestLoader restLoader = new RestLoader(SERVER_URL);
        Vector3f playerPosition = new Vector3f(100, 0, 100);
        Vector3f playerYapPitchRoll = new Vector3f(0, 0, 0);
        float playerScale = 1f;
//        long playerId = restLoader.registerEntity(new Geometry(playerPosition.x, playerPosition.y, playerPosition.z,
//                playerYapPitchRoll.x, playerYapPitchRoll.y, playerYapPitchRoll.z,playerScale));
        try {
            Ticker ticker = new TickerImpl(20);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();

//            List<Entity> entities = generateEntities(loader);
//            Entity controlled = loadPlayer(loader);
            List<Entity> entities = new ArrayList<>();
            RawModel model = OBJLoader.loadModel("models/arc/owkg2.obj", loader);
            ModelTexture texture = new ModelTexture(loader.loadTexture(ModelTexture.GRAY));
            texture.setHasTransparency(false);
            texture.setReflectivity(50f);
            texture.setShineDamper(50);
            TexturedModel staticModel = new TexturedModel(model, texture);
            Entity entity = new StaticEntity(staticModel, new Vector3f(100, -7 , 100),0,0,0,0.3f);
            entities.add(entity);

//            Control entityControl = new RestFlatControl(SERVER_URL, playerId, 1f, playerPosition, playerYapPitchRoll, ticker);

            Control entityControl = new FreeKeyboardMouseControl(1, new Vector3f(0,10,0), new Vector3f(180,0,0), ticker);
            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(-2000, 2000, 200), new Vector3f(0.9f, 0.9f, 0.9f));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);
            CameraControl cameraControl = new CameraControl(entityControl);
            Camera camera = new ControlledCamera(new Vector3f(0, 10, 0), cameraControl);
//            Camera camera = new StaticCamera(new Vector3f(0, 100, 0), 130, 20, 0);

            MasterRenderer renderer = new MasterRenderer();
            TexturedModel playerModel = loadPlayer(loader);
            while (!Display.isCloseRequested()) {
                ticker.tick();

                renderer.processTerrain(terrain);

                entities.forEach(renderer::processEntity);
//                List<Entity> players = getEntitiesExcludeId(playerId, playerModel, restLoader);
//                players.forEach(renderer::processEntity);
                renderer.render(light, camera);
                DisplayManager.updateDisplay();
            }

            renderer.cleanUp();
            loader.cleanUp();
            DisplayManager.closeDisplay();
        } catch (LWJGLException e) {
            e.printStackTrace();
        } finally {
//            restLoader.deleteEntity(playerId);
        }
    }

//    private static List<Entity> generateEntities(Loader loader) {
//        List<Entity> entities = new ArrayList<>();
//        RestLoader restLoader = new RestLoader(SERVER_URL);
//        Map<Long, List<Geometry>> geometries = restLoader.loadTerrainObjects();
//        RawModel model = OBJLoader.loadModel("models/lowPolyTree/lowPolyTree.obj", loader);
//        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/lowPolyTree/lowPolyTree.png")));
//        List<Geometry> geometryList = geometries.get(1L);
//        for (int i = 0; i < geometryList.size(); i++) {
//            Geometry g = geometryList.get(i);
//            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
//        }
//        model = OBJLoader.loadModel("models/palm/palm1.obj", loader);
//        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/palm/palm.png")));
//        geometryList = geometries.get(2L);
//        for (int i = 0; i < geometryList.size(); i++) {
//            Geometry g = geometryList.get(i);
//            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
//        }
//        model = OBJLoader.loadModel("models/grass/grassModel.obj", loader);
//        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/grassTexture.png")));
//        staticModel.getTexture().setHasTransparency(true);
//        staticModel.getTexture().setUseFakeLighting(true);
//        geometryList = geometries.get(3L);
//        for (int i = 0; i < geometryList.size(); i++) {
//            Geometry g = geometryList.get(i);
//            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
//        }
//        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/grass/flower.png")));
//        staticModel.getTexture().setHasTransparency(true);
//        staticModel.getTexture().setUseFakeLighting(true);
//        geometryList = geometries.get(4L);
//        for (int i = 0; i < geometryList.size(); i++) {
//            Geometry g = geometryList.get(i);
//            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
//        }
//        model = OBJLoader.loadModel("models/fern/fern.obj", loader);
//        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/fern/fern.png")));
//        staticModel.getTexture().setHasTransparency(true);
//        geometryList = geometries.get(5L);
//        for (int i = 0; i < geometryList.size(); i++) {
//            Geometry g = geometryList.get(i);
//            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
//        }
//        return entities;
//    }

    private static TerrainTexturePack getTexturePack(Loader loader) {
        TerrainTexture background = new TerrainTexture(loader.loadTexture("textures/grassy2.png"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("textures/mud.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textures/grassFlowers.png"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("textures/path.png"));
        return new TerrainTexturePack(background, rTexture, gTexture, bTexture);
    }

    private static TexturedModel loadPlayer(Loader loader) {
        RawModel model = OBJLoader.loadModel("models/player/person.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/player/playerTexture.png")));
        return staticModel;
    }

//    private static Geometry getGeometry(Long id) {
//        return new RestLoader(SERVER_URL).loadEntityObjects().get(id);
//    }

//    private static Map<Long, Entity> getEntities(TexturedModel model, RestLoader loader) {
//        Map<Long, Geometry> entityGeometries = loader.loadEntityObjects();
//        Map<Long, Entity> result = new HashMap<>(entityGeometries.size());
//        for (Map.Entry<Long, Geometry> entry : entityGeometries.entrySet()) {
//            Geometry g = entry.getValue();
//            Entity entity = new StaticEntity(model, new Vector3f(g.getPosX(),g.getPosY(), g.getPosZ()), 0, g.getRotX(), 0,g.getScale());
//            result.put(entry.getKey(), entity);
//        }
//        return result;
//    }

//    private static List<Entity> getEntitiesExcludeId(long id, TexturedModel model, RestLoader loader) {
//        Map<Long, Entity> entities = getEntities(model, loader);
//        entities.remove(id);
//        return new ArrayList<>(entities.values());
//    }
}
