package ru.reeson2003.my3d.server.entities;

import ru.reeson2003.my3d.common.Geometry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User on 20.10.2017.
 */
public class EntityManagerImpl implements EntitiesManager {
    private static EntitiesManager instance = new EntityManagerImpl();

    private EntityManagerImpl() {
        entities = new ConcurrentHashMap<>();
    }

    private Map<Long, Geometry> entities;

    public static EntitiesManager getInstance() {
        return instance;
    }

    @Override
    public Map<Long, Geometry> getEntities() {
        return entities;
    }

    @Override
    public void updateEntity(long id, Geometry geometry) {
        if (entities.containsKey(id))
            entities.put(id, geometry);
    }

    @Override
    public void removeEntity(long id) {
        entities.remove(id);
    }

    @Override
    public synchronized long registerEntity(Geometry geometry) {
        long result = entities.size();
        entities.put(result, geometry);
        return result;
    }
}
