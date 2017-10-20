package ru.reeson2003.my3d.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjects extends HashMap<String, Long> {
    private static TerrainObjects instance;
    private static Exception exception;

    static {
        instance = new TerrainObjects();
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjectNames.properties"));
            for (Entry<Object, Object> entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                Long value = Long.parseLong((String) entry.getValue());
                if (instance.containsValue(value)) {
                    throw new Exception("Duplicate object id [" + value + "]");
                }
                instance.put(key, value);
            }
        } catch (Exception e) {
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
