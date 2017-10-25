package ru.reeson2003.my3d.client.renderEngine;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.reeson2003.my3d.client.models.RawModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 15.10.2017.
 */
public class OBJLoader {

    private static Logger LOGGER = LoggerFactory.getLogger(OBJLoader.class);

    public static RawModel loadModel(String fileName, Loader loader) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        List<Vector3f> vertices = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;
        try {

            int lineNumber = 0;
            while (true) {
                lineNumber++;
                line = reader.readLine();
                line = line.replaceAll(" {2}", " ");
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(0,0,0);
                    try {
                        vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                                Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    } catch (NumberFormatException e) {
                        LOGGER.debug(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'");
                    } catch (Exception e) {
                        LOGGER.error(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'", e);
                    }
                    vertices.add(vertex);
                } else if (line.startsWith("vt ")) {
                    Vector2f texture = new Vector2f(0,0);
                    try {
                        texture = new Vector2f(Float.parseFloat(currentLine[1]),
                                Float.parseFloat(currentLine[2]));
                    } catch (NumberFormatException e) {
                        LOGGER.debug(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'");
                    } catch (Exception e) {
                        LOGGER.error(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'", e);
                    }
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(0,0,0);
                    try {
                        normal = new Vector3f(Float.parseFloat(currentLine[1]),
                                Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    } catch (NumberFormatException e) {
                        LOGGER.debug(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'");
                    } catch (Exception e) {
                        LOGGER.error(e.getLocalizedMessage() + "line number: " + lineNumber + ", line '" + line + "'", e);
                    }
                    normals.add(normal);
                } else if (line.startsWith("f ")) {
                    textureArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }

            while (line != null) {
                if (!line.startsWith("f ")) {
                    line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

                processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
                line = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        verticesArray = new float[vertices.size() * 3];
        indicesArray = new int[indices.size()];

        int vertexPointer = 0;
        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }
        return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);

    }

    private static void processVertex(String[] vertexData, List<Integer> indices,
                                      List<Vector2f> textures, List<Vector3f> normals, float[] textureArray,
                                      float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = new Vector2f(0, 0);
        try {
            currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
        } catch (NumberFormatException ignore) {

        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(),e);
        }
        textureArray[currentVertexPointer * 2] = currentTex.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }
}
