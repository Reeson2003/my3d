package ru.reeson2003.my3d.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.reeson2003.my3d.common.Geometry;
import ru.reeson2003.my3d.common.TerrainObjectGeometries;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
@RestController("/terrain")
public class TerrainController {
    @RequestMapping(method = RequestMethod.GET)
    Map<Long, List<Geometry>> getTerrainObjects() {
        TerrainObjectGeometries objects = null;
        try {
            objects =TerrainObjectGeometries.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
