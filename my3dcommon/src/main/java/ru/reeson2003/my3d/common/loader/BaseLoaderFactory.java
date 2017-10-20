package ru.reeson2003.my3d.common.loader;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public abstract class BaseLoaderFactory {
    private static LoaderFactory factory;

    public static GeometryLoader getGeometryLoader() {
        return factory.getGeometryLoader();
    }

    public static TerrainObjectLoader getTerrainObjectLoader() {
        return factory.getTerrainObjectLoader();
    }

    public static void setFactory(LoaderFactory f) {
        factory = f;
    }
}
