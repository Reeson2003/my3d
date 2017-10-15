package ru.reeson2003.my3d.shaders;

import org.lwjgl.util.vector.Matrix4f;
import ru.reeson2003.my3d.entities.Camera;
import ru.reeson2003.my3d.entities.Light;
import ru.reeson2003.my3d.tools.Maths;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class StaticShader extends ShaderProgram {
    private static final String VERTEX_FILE = "shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "shaders/fragmentShader.frag";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int loaction_lightColour;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        loaction_lightColour = super.getUniformLocation("lightColour");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normals");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadLight(Light light) {
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(loaction_lightColour, light.getColour());
    }
}
