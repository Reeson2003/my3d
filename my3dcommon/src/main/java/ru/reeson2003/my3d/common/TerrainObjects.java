package ru.reeson2003.my3d.common;

import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjects extends HashMap<String, Long> {
    private static TerrainObjects instance;
    private static Exception exception;

    static {
        instance = new TerrainObjects();
        try {
            instance.putAll(BaseLoaderFactory.getTerrainObjectLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
            exception = e;
            instance = null;
        }
    }

    private TerrainObjects() {
    }

    public static TerrainObjects getInstance() throws IOException {
        if (instance == null)
            throw new IOException("Can not load 'terrainObjectNames.properties", exception);
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TerrainObjects {\n");
        for (Entry<String, Long> entry : instance.entrySet()) {
            sb.append("\t")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
