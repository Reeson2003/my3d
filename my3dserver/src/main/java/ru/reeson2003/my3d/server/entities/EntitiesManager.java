package ru.reeson2003.my3d.server.entities;

import ru.reeson2003.my3d.common.Geometry;

import java.util.Map;

/**
 * Created by User on 20.10.2017.
 */
public interface EntitiesManager {
    Map<Long, Geometry> getEntities();

    void updateEntity(long id, Geometry geometry);

    void removeEntity(long id);

    long registerEntity(Geometry geometry);
}
