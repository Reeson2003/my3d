package ru.reeson2003.my3d.common.loader.internal;

import ru.reeson2003.my3d.common.loader.TerrainObjectLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class InternalTerrainObjectLoader implements TerrainObjectLoader {
    @Override
    public HashMap<String, Long> load() throws IOException {
        Properties properties = new Properties();
        HashMap<String, Long> result = new HashMap<>();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjectNames.properties"));
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            Long value = Long.parseLong((String) entry.getValue());
            if (result.containsValue(value)) {
                throw new IOException("Duplicate object id [" + value + "]");
            }
            result.put(key, value);
        }
        return result;
    }
}
