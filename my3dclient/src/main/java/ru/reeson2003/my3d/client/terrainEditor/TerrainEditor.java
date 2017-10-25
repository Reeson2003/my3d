package ru.reeson2003.my3d.client.terrainEditor;

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
import ru.reeson2003.my3d.client.terrains.ShiftedControl;
import ru.reeson2003.my3d.client.terrains.Terrain;
import ru.reeson2003.my3d.client.textures.TerrainTexture;
import ru.reeson2003.my3d.client.textures.TerrainTexturePack;
import ru.reeson2003.my3d.client.ticker.Ticker;
import ru.reeson2003.my3d.client.ticker.TickerImpl;
import ru.reeson2003.my3d.client.tools.ColourVector;
import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Gavrilov on 25.10.2017.
 */
public class TerrainEditor {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int FPS = 60;
    public static final float CAMERA_SPEED = 1f;

    public static void main(String[] args) {
        try {
            Ticker ticker = new TickerImpl(20);
            DisplayManager.createDisplay(WIDTH, HEIGHT, FPS);
            Loader loader = new Loader();

            BaseLoaderFactory.setFactory(new InternalLoaderFactory());
            List<Entity> entities = new ArrayList<>();
            ModelLoader modelLoader = null;
            try {
                modelLoader = ModelLoaderInternal.getInstance(loader);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            List<TexturedModel> models = null;
            try {
                models = new ArrayList<>(modelLoader.getModels(loader).values());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            StaticEntity entity = new StaticEntity(models.get(0), new Vector3f(0, 0, 0), 0, 0, 0, 3f);
            TerrainTexturePack texturePack = getTexturePack(loader);
            TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap.png"));
            Light light = new Light(new Vector3f(2000, 2000, 2000), new ColourVector(255, 235, 230));

            Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);
            Control entityControl = new FreeKeyboardMouseControl(CAMERA_SPEED, new Vector3f(0, 10, 0), new Vector3f(120, 0, 0), ticker);
            ShiftedControl shiftedControl = new ShiftedControl(entityControl, new Vector3f(200,0,200));
            ControlledEntity controlledEntity = new ControlledEntity(entity, shiftedControl);
            entities.add(controlledEntity);
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

    private static TerrainTexturePack getTexturePack(Loader loader) {
        TerrainTexture background = new TerrainTexture(loader.loadTexture("textures/grassy2.png"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("textures/mud.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textures/grassFlowers.png"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("textures/path.png"));
        return new TerrainTexturePack(background, rTexture, gTexture, bTexture);
    }
}
