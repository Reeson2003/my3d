package ru.reeson2003.my3d.client.rest;

import org.springframework.web.client.RestTemplate;
import ru.reeson2003.my3d.common.Geometry;

import java.util.*;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class RestTerrainLoader {
    public static final String URL = "http://localhost:8080/terrain";
    private RestTemplate restTemplate = new RestTemplate();

    public Map<Long, List<Geometry>> loadTerrainObjects() {
        Map<String, List<Map<String, Double>>> result = restTemplate.getForObject(URL, Map.class);
        return convert(result);
    }

    private Map<Long, List<Geometry>> convert(Map<String, List<Map<String, Double>>> raw) {
        Map<Long, List<Geometry>> result = new HashMap<>();
        for (Map.Entry<String, List<Map<String, Double>>> entry : raw.entrySet()) {
            List<Map<String, Double>> list = entry.getValue();
            List<Geometry> geometries = new ArrayList<>(list.size());
            for (Map<String, Double> map : list) {
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
                Geometry geometry = new Geometry(posX, posY, posZ, rotX, rotY, rotZ, scale);
                geometries.add(geometry);
            }
            Long key = Long.parseLong(entry.getKey());
            result.put(key, geometries);
        }
        return result;
    }
}
