package ru.reeson2003.my3d.common.loader.internal;

import ru.reeson2003.my3d.common.Geometry;
import ru.reeson2003.my3d.common.loader.GeometryLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class InternalGeometryLoader implements GeometryLoader {
    @Override
    public Map<String, List<Geometry>> load() throws Exception {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjects.geometry");
        if (is == null)
            throw new Exception("Can not open stream for resource 'terrainObjects.geometry'");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        Map<String, List<Geometry>> result = new HashMap<>();
        while (true) {
            line = reader.readLine();
            if (line == null)
                break;
            String[] values = line.split(",");
            if (values.length != 8)
                throw new Exception("Invalid 'terrainObjects.geometry' file content");
            String  id;
            Float posX;
            Float posY;
            Float posZ;
            Float rotX;
            Float rotY;
            Float rotZ;
            Float scale;
            try {
                id = values[0];
                posX = Float.parseFloat(values[1]);
                posY = Float.parseFloat(values[2]);
                posZ = Float.parseFloat(values[3]);
                rotX = Float.parseFloat(values[4]);
                rotY = Float.parseFloat(values[5]);
                rotZ = Float.parseFloat(values[6]);
                scale = Float.parseFloat(values[7]);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid 'terrainObjects.geometry' file content");
            }
            List<Geometry> list = result.get(id);
            if (list == null)
                list = new ArrayList<>();
            list.add(new Geometry(posX, posY, posZ, rotX,rotY,rotZ, scale));
            result.put(id, list);
        }
        return result;
    }
}
