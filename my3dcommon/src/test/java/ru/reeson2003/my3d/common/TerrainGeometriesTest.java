package ru.reeson2003.my3d.common;

import org.junit.Test;
import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

import java.io.IOException;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainGeometriesTest {
    @Test
    public void terrainGeometriesTest() throws IOException {
        BaseLoaderFactory.setFactory(new InternalLoaderFactory());
        TerrainObjectGeometries.getInstance().values().forEach(i-> System.out.println(i.get(0)));
    }
}
