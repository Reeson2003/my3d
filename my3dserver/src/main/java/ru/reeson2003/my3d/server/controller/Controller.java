package ru.reeson2003.my3d.server.controller;

import org.springframework.web.bind.annotation.*;
import ru.reeson2003.my3d.common.Geometry;
import ru.reeson2003.my3d.common.TerrainObjectGeometries;
import ru.reeson2003.my3d.server.entities.EntityManagerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
@RestController
public class Controller {

    @RequestMapping(value = "/terrain",method = RequestMethod.GET)
    Map<Long, List<Geometry>> getTerrainObjects() {
        TerrainObjectGeometries objects = null;
        try {
            objects =TerrainObjectGeometries.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    Map<Long, Geometry> getEntities() {
        return EntityManagerImpl.getInstance().getEntities();
    }

    @RequestMapping(value = "/entity", method = RequestMethod.PUT)
    void updateEntity(@RequestParam long id, @RequestBody Geometry geometry) {
        EntityManagerImpl.getInstance().updateEntity(id, geometry);
    }
}
