package ru.reeson2003.my3d.common;

import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjects extends HashSet<String> {
    private static TerrainObjects instance;
    private static Exception exception;

    static {
        instance = new TerrainObjects();
        try {
            instance.addAll(BaseLoaderFactory.getTerrainObjectLoader().load());
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
        for (String  s : instance) {
            sb.append("\t")
                    .append(s)
                    .append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
