package ru.reeson2003.my3d.common;

import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class RandomTerrainGenerator {
    public static final int ITEM_AMOUNT_CAP = 100;
    public static void main(String[] args) {
        BaseLoaderFactory.setFactory(new InternalLoaderFactory());
        try {
            new RandomTerrainGenerator().generate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generate() throws IOException {
        String path = "my3dcommon/src/main/resources/terrainObjects.geometry";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(path));
            Map<String, List<Geometry>> geoms = getGeometries();
            for (Map.Entry<String, List<Geometry>> entry : geoms.entrySet()) {
                List<Geometry> geometries = entry.getValue();
                for (Geometry g : geometries) {
                    writer.write(formatGeometry(entry.getKey(), g) + "\n");
                }
            }
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private Map<String , List<Geometry>> getGeometries() throws IOException {
        Random random = new Random();
        Map<String, List<Geometry>> result = new HashMap<>();
        TerrainObjects objects = TerrainObjects.getInstance();
        for (String s : objects) {
            List<Geometry> geometries = new ArrayList<>();
            for (int i = 0; i <  ITEM_AMOUNT_CAP; i++) {
                Geometry geometry =new Geometry(25 + random.nextFloat() * 800, 0, 25 + random.nextFloat() * 800, 0,0,0, 1);
                geometries.add(geometry);
            }
            result.put(s, geometries);
        }
        return result;
    }

    private String formatGeometry(String id, Geometry geometry) {
        return new StringBuilder().append(id)
                .append(",")
                .append(geometry.getPosX())
                .append(",")
                .append(geometry.getPosY())
                .append(",")
                .append(geometry.getPosZ())
                .append(",")
                .append(geometry.getRotX())
                .append(",")
                .append(geometry.getRotY())
                .append(",")
                .append(geometry.getRotZ())
                .append(",")
                .append(geometry.getScale())
                .toString();
    }
}
