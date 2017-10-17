package ru.reeson2003.my3d.renderEngine;

import ru.reeson2003.my3d.entities.Camera;
import ru.reeson2003.my3d.entities.Entity;
import ru.reeson2003.my3d.entities.Light;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 17.10.2017.
 */
public class MasterRenderer {
    private StaticShader shader = new StaticShader();
    private Renderer renderer = new Renderer(shader);

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    public void render(Light sun, Camera camera) {
        renderer.prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        List<Entity> batch = entities.get(model);
        if (batch != null) {
            batch.add(entity);
        }else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public void cleanUp() {
        shader.cleanUp();
    }

}
