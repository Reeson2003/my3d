package ru.reeson2003.my3d.shaders;

import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class StaticShader extends ShaderProgram {
    private static final String VERTEX_FILE = "shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "shaders/fragmentShader.frag";

    private int loaction_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        loaction_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(loaction_transformationMatrix, matrix);
    }
}
