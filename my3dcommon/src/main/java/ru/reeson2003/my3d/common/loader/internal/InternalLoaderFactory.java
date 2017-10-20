package ru.reeson2003.my3d.common.loader.internal;

import ru.reeson2003.my3d.common.loader.GeometryLoader;
import ru.reeson2003.my3d.common.loader.LoaderFactory;
import ru.reeson2003.my3d.common.loader.TerrainObjectLoader;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class InternalLoaderFactory implements LoaderFactory {
    @Override
    public GeometryLoader getGeometryLoader() {
        return new InternalGeometryLoader();
    }

    @Override
    public TerrainObjectLoader getTerrainObjectLoader() {
        return new InternalTerrainObjectLoader();
    }
}
