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
import ru.reeson2003.my3d.client.tools.ColourVector;
import ru.reeson2003.my3d.common.Geometry;
import ru.reeson2003.my3d.common.TerrainObjectGeometries;
import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static void main(String[] args) {
        try {
            Ticker ticker = new TickerImpl(20);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();

            BaseLoaderFactory.setFactory(new InternalLoaderFactory());
            List<Entity> entities = null;
            try {
                entities = generateEntities(loader);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RawModel model = OBJLoader.loadModel("models/arc/Arc.obj", loader);
            ModelTexture texture = new ModelTexture(loader.loadTexture(ModelTexture.GRAY));
//            texture.setHasTransparency(false);
            texture.setReflectivity(50f);
            texture.setShineDamper(50);
            TexturedModel staticModel = new TexturedModel(model, texture);
            Entity entity = new StaticEntity(staticModel, new Vector3f(100, 0 , 100),0,0,0,1f);
            entities.add(entity);
            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(2000, 2000, 2000), new ColourVector(255, 235, 230));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);

            Control entityControl = new FreeKeyboardMouseControl(1, new Vector3f(0,10,0), new Vector3f(180,0,0), ticker);
            CameraControl cameraControl = new CameraControl(entityControl);
            Camera camera = new ControlledCamera(new Vector3f(0, 10, 0), cameraControl);

            MasterRenderer renderer = new MasterRenderer();
            while (!Display.isCloseRequested()) {
                ticker.tick();
                renderer.processTerrain(terrain);
                entities.forEach(renderer::processEntity);
                renderer.render(light, camera);
                DisplayManager.updateDisplay();
            }

            renderer.cleanUp();
            loader.cleanUp();
            DisplayManager.closeDisplay();
        } catch (LWJGLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private static List<Entity> generateEntities(Loader loader) throws IOException {
        List<Entity> entities = new ArrayList<>();
        Map<Long, List<Geometry>> geometries = TerrainObjectGeometries.getInstance();
        RawModel model = OBJLoader.loadModel("models/lowPolyTree/lowPolyTree.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/lowPolyTree/lowPolyTree.png")));
        List<Geometry> geometryList = geometries.get(1L);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        model = OBJLoader.loadModel("models/palm/palm1.obj", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/palm/palm.png")));
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

    private static TexturedModel loadPlayer(Loader loader) {
        RawModel model = OBJLoader.loadModel("models/player/person.obj", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/player/playerTexture.png")));
        return staticModel;
    }
}
