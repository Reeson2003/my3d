package ru.reeson2003.my3d.common;

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
public class GeometryLoader {
    public static Map<Long, List<Geometry>> load() throws Exception {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("terrainObjects.geometry");
        if (is == null)
            throw new Exception("Can not open stream for resource 'terrainObjects.geometry'");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        Map<Long, List<Geometry>> result = new HashMap<>();
        while (true) {
            line = reader.readLine();
            if (line == null)
                break;
            String[] values = line.split(",");
            if (values.length != 7)
                throw new Exception("Invalid 'terrainObjects.geometry' file content");
            Long id;
            Float posX;
            Float posY;
            Float posZ;
            Float rotX;
            Float rotY;
            Float rotZ;
            try {
                id = Long.parseLong(values[0]);
                posX = Float.parseFloat(values[1]);
                posY = Float.parseFloat(values[2]);
                posZ = Float.parseFloat(values[3]);
                rotX = Float.parseFloat(values[4]);
                rotY = Float.parseFloat(values[5]);
                rotZ = Float.parseFloat(values[6]);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid 'terrainObjects.geometry' file content");
            }
            List<Geometry> list = result.get(id);
            if (list == null)
                list = new ArrayList<>();
            list.add(new Geometry(posX, posY, posZ, rotX,rotY,rotZ));
        }
        return result;
    }
}
