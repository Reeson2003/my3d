package ru.reeson2003.my3d.common;

import org.junit.Test;
import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

import java.io.IOException;


/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjectsTest {

    @Test
    public void terrainObjectsTest() throws IOException {
        BaseLoaderFactory.setFactory(new InternalLoaderFactory());
        TerrainObjects.getInstance();
    }

}