package ru.reeson2003.my3d.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjects extends HashMap<String, Long> {
    private static TerrainObjects instance = new TerrainObjects();

    private TerrainObjects() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjects.properties"));
            for (Entry<Object, Object> entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                Long value = Long.parseLong((String) entry.getValue());
                this.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            instance = null;
        }
    }

    public static TerrainObjects getInstance() throws IOException {
        if (instance == null)
            throw new IOException("Can not load 'terrainObjects.properties");
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TerrainObjects{\n");
        for (Entry<String, Long> entry : instance.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
