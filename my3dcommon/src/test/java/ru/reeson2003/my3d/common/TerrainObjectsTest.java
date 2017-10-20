package ru.reeson2003.my3d.common;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjectsTest {

    @Test
    public void terrainObjectsTest() throws IOException {
        TerrainObjects terrainObjects = TerrainObjects.getInstance();
        assertNotNull("TerrainObjects is null", terrainObjects);
        assertFalse("TerrainObjects is empty", terrainObjects.isEmpty());
    }

}