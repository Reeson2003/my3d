package ru.reeson2003.my3d.transport;

import ru.reeson2003.my3d.common.Geometry;

import java.util.*;

/**
 * Created by User on 21.10.2017.
 */
public class GeometryConverter {

    public static Geometry convertGeometry(Map<String, Double> map) {
        Iterator<Map.Entry<String, Double>> iterator = map.entrySet().iterator();
        double value = iterator.next().getValue();
        float posX = (float) value;
        value = iterator.next().getValue();
        float posY = (float) value;
        value = iterator.next().getValue();
        float posZ = (float) value;
        value = iterator.next().getValue();
        float rotX = (float) value;
        value = iterator.next().getValue();
        float rotY = (float) value;
        value = iterator.next().getValue();
        float rotZ = (float) value;
        value = iterator.next().getValue();
        float scale = (float) value;
        return new Geometry(posX, posY, posZ, rotX, rotY, rotZ, scale);
    }

    public static Map<String, List<Geometry>> convertGeometries(Map<String, List<Map<String, Double>>> raw) {
        Map<String, List<Geometry>> result = new HashMap<>();
        for (Map.Entry<String, List<Map<String, Double>>> entry : raw.entrySet()) {
            List<Map<String, Double>> list = entry.getValue();
            List<Geometry> geometries = new ArrayList<>(list.size());
            for (Map<String, Double> map : list) {
                geometries.add(convertGeometry(map));
            }
            result.put(entry.getKey(), geometries);
        }
        return result;
    }
}
