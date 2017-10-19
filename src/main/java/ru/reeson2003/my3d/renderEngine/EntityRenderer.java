package ru.reeson2003.my3d.renderEngine;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import ru.reeson2003.my3d.entities.Entity;
import ru.reeson2003.my3d.models.RawModel;
import ru.reeson2003.my3d.models.TexturedModel;
import ru.reeson2003.my3d.shaders.StaticShader;
import ru.reeson2003.my3d.textures.ModelTexture;
import ru.reeson2003.my3d.tools.Maths;

import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class EntityRenderer {

    private StaticShader shader;

    public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<Entity >> entities) {
        for (TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> bathc = entities.get(model);
            for (Entity entity : bathc) {
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel raw = model.getRawModel();
        GL30.glBindVertexArray(raw.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = model.getTexture();
        if (texture.isHasTransparency())
            MasterRenderer.disableCulling();
        shader.loadShineVariaables(texture.getShineDamper(), texture.getReflectivity());
        shader.loadFakeLightingVariable(texture.isUseFakeLighting());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());

    }

    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),
                entity.getRotX(),
                entity.getRotY(),
                entity.getRotZ(),
                entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
