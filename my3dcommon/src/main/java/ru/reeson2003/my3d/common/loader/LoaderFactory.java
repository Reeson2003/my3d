package ru.reeson2003.my3d.common.loader;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public interface LoaderFactory {
    GeometryLoader getGeometryLoader();

    TerrainObjectLoader getTerrainObjectLoader();
}
