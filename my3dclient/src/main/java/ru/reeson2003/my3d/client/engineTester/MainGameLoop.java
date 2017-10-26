package ru.reeson2003.my3d.client.engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import ru.reeson2003.my3d.client.control.CameraControl;
import ru.reeson2003.my3d.client.control.Control;
import ru.reeson2003.my3d.client.control.FreeKeyboardMouseControl;
import ru.reeson2003.my3d.client.entities.*;
import ru.reeson2003.my3d.client.models.ModelLoader;
import ru.reeson2003.my3d.client.models.ModelLoaderInternal;
import ru.reeson2003.my3d.client.models.TexturedModel;
import ru.reeson2003.my3d.client.renderEngine.DisplayManager;
import ru.reeson2003.my3d.client.renderEngine.Loader;
import ru.reeson2003.my3d.client.renderEngine.MasterRenderer;
import ru.reeson2003.my3d.client.terrains.Terrain;
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

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class MainGameLoop {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;
    public static final int FPS = 60;
    public static final String TITLE = "AWESOME";
    public static final float CAMERA_SPEED = 1f;


    public static void main(String[] args) {
        Vector3f playerPosition = new Vector3f(100, 0, 100);
        Vector3f playerYapPitchRoll = new Vector3f(0, 0, 0);
        float playerScale = 1f;
        try {
            Ticker ticker = new TickerImpl(20);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS, TITLE);
            Loader loader = new Loader();
            List<Entity> entities = new ArrayList<>();
            RawModel model = OBJLoader.loadModel("models/ware/arc_test.obj", loader);
            TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("models/ware/arc_test.png")));
            Entity entity = new StaticEntity(staticModel, new Vector3f(10, 0 , 10),0,0,0,3.3f);
            entities.add(entity);
            Control entityControl = new FreeKeyboardMouseControl(1, new Vector3f(0,10,0), new Vector3f(180,0,0), ticker);
            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(-2000, 2000, 100), new Vector3f(0.9f, 0.9f, 0.9f));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap, "C:/myasnikov/IDEA_Projeckt/my3d/my3dclient/src/main/resources/textures/blendMap4.png");
            CameraControl cameraControl = new CameraControl(entityControl);
            Camera camera = new ControlledCamera(new Vector3f(0, 10, 0), cameraControl);

            MasterRenderer renderer = new MasterRenderer();
            TexturedModel playerModel = loadPlayer(loader);
            boolean flag = true;
            int i = 0;
            while (!Display.isCloseRequested()) {
                ticker.tick();
                renderer.processTerrain(terrain);
                entities.forEach(renderer::processEntity);
                renderer.render(light, camera);
                if (light.getColour().getX() < 4.0f && flag) {
                    light.getColour().setX(light.getColour().getX() + 0.05f);
                    flag = !(light.getColour().getX() > 3.9f);
                }
                else {
                    light.getColour().setX(light.getColour().getX() - 0.05f);
                    flag = light.getColour().getX() < 0.9f;
                }
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

    private static List<Entity> generateEntities(Loader loader) throws IOException {
        List<Entity> entities = new ArrayList<>();
        Map<String, List<Geometry>> geometries = TerrainObjectGeometries.getInstance();
        ModelLoader models = ModelLoaderInternal.getInstance(loader);
        String id = "terrain.object.tree1";
        TexturedModel staticModel = models.getModel(id);
        List<Geometry> geometryList = geometries.get(id);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        id = "terrain.object.tree2";
        geometryList = geometries.get(id);
        staticModel = models.getModel(id);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        id = "terrain.object.grass";
        geometryList = geometries.get(id);
        staticModel = models.getModel(id);
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        id = "terrain.object.flower";
        geometryList = geometries.get(id);
        staticModel = models.getModel(id);
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        for (int i = 0; i < geometryList.size(); i++) {
            Geometry g = geometryList.get(i);
            entities.add(new StaticEntity(staticModel, new Vector3f(g.getPosX(), g.getPosY(), g.getPosZ()), g.getRotX(), g.getRotY(), g.getRotZ(), g.getScale()));
        }
        id = "terrain.object.fern";
        geometryList = geometries.get(id);
        staticModel = models.getModel(id);
        staticModel.getTexture().setHasTransparency(true);
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

}
