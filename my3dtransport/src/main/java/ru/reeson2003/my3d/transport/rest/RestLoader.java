package ru.reeson2003.my3d.transport.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.reeson2003.my3d.common.Geometry;
import ru.reeson2003.my3d.transport.GeometryConverter;

import java.util.*;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class RestLoader {
    private String URL;
    private RestTemplate restTemplate = new RestTemplate();

    public RestLoader(String URL) {
        this.URL = URL;
    }

    private Map<String, List<Geometry>> loadObjects(String path) {
        Map<String, List<Map<String, Double>>> result = restTemplate.getForObject(URL + path, Map.class);
        return GeometryConverter.convertGeometries(result);
    }

    public Map<String, List<Geometry>> loadTerrainObjects() {
        String path = "terrain";
        return loadObjects(path);
    }

    public Map<Long, Geometry> loadEntityObjects() {
        String path = "entity";
        Map<String, Map<String, Double>> raw = restTemplate.getForObject(URL + path, Map.class);
        Map<Long, Geometry> result = new HashMap<>();
        for (Map.Entry<String , Map<String, Double>> entry : raw.entrySet()) {
            long key = Long.parseLong(entry.getKey());
            Geometry value = GeometryConverter.convertGeometry(entry.getValue());
            result.put(key, value);
        }
        return result;
    }

    public void updateEntity(long id, Geometry geometry) {
        String url = URL + "/entity?id=" + id;
        HttpEntity<Geometry> entity = new HttpEntity<>(geometry);
        restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);
    }

    public long registerEntity(Geometry geometry) {
        String url = URL;
        HttpEntity<Geometry> entity = new HttpEntity<>(geometry);
        return restTemplate.exchange(url + "/id", HttpMethod.PUT, entity, Long.class).getBody();
    }

    public void deleteEntity(long id) {
        String url = URL;
        restTemplate.delete(url + "/entity?id=" + id);
    }
}
