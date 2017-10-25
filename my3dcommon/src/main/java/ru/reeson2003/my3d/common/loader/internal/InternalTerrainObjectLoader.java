package ru.reeson2003.my3d.common.loader.internal;

import ru.reeson2003.my3d.common.loader.TerrainObjectLoader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class InternalTerrainObjectLoader implements TerrainObjectLoader {
    @Override
    public Set<String> load() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjectNames.properties"));
        Set<String> result = new HashSet<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            if (result.contains(key)) {
                throw new IOException("Duplicate object id [" + key + "]");
            }
            result.add(key);
        }
        return result;
    }
}
