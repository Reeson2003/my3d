package ru.reeson2003.my3d.client.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.reeson2003.my3d.common.Geometry;

import java.util.*;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class RestLoader {
    public static final String URL = "http://192.168.1.50:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    private Map<Long, List<Geometry>> loadObjects(String path) {
        Map<String, List<Map<String, Double>>> result = restTemplate.getForObject(URL + path, Map.class);
        return convert(result);
    }

    public Map<Long, List<Geometry>> loadTerrainObjects() {
        String path = "terrain";
        return loadObjects(path);
    }

    public Map<Long, Geometry> loadEntityObjects() {
        String path = "entity";
        Map<String, Map<String, Double>> raw = restTemplate.getForObject(URL + path, Map.class);
        Map<Long, Geometry> result = new HashMap<>();
        for (Map.Entry<String , Map<String, Double>> entry : raw.entrySet()) {
            long key = Long.parseLong(entry.getKey());
            Geometry value = convertGeometry(entry.getValue());
            result.put(key, value);
        }
        return result;
    }

    public void updateEntity(long id, Geometry geometry) {
        String url = URL + "/entity?id=" + id;
        HttpEntity<Geometry> entity = new HttpEntity<>(geometry);
        restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);
    }

    private Map<Long, List<Geometry>> convert(Map<String, List<Map<String, Double>>> raw) {
        Map<Long, List<Geometry>> result = new HashMap<>();
        for (Map.Entry<String, List<Map<String, Double>>> entry : raw.entrySet()) {
            List<Map<String, Double>> list = entry.getValue();
            List<Geometry> geometries = new ArrayList<>(list.size());
            for (Map<String, Double> map : list) {
                geometries.add(convertGeometry(map));
            }
            Long key = Long.parseLong(entry.getKey());
            result.put(key, geometries);
        }
        return result;
    }

    private Geometry convertGeometry(Map<String, Double> map) {
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
}
