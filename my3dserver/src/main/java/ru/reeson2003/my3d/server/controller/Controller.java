package ru.reeson2003.my3d.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/terrain",method = RequestMethod.GET)
    Map<String, List<Geometry>> getTerrainObjects() {
        TerrainObjectGeometries objects = null;
        LOGGER.debug("Requesting terrain objects");
        try {
            objects = TerrainObjectGeometries.getInstance();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return objects;
    }

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    Map<Long, Geometry> getEntities() {
        LOGGER.debug("Requesting entities");
        return EntityManagerImpl.getInstance().getEntities();
    }

    @RequestMapping(value = "/entity", method = RequestMethod.PUT)
    void updateEntity(@RequestParam long id, @RequestBody Geometry geometry) {
        EntityManagerImpl.getInstance().updateEntity(id, geometry);
//        LOGGER.debug("Updating entity id: [" + id + "]");
    }

    @RequestMapping(value = "/id", method = RequestMethod.PUT)
    long getId(@RequestBody Geometry geometry) {
        LOGGER.info("Registering new entity");
        long result =  EntityManagerImpl.getInstance().registerEntity(geometry);
        LOGGER.info("Registered entity id: [" + result + "]");
        return result;
    }

    @RequestMapping(value = "/entity", method = RequestMethod.DELETE)
    void deleteEntity(@RequestParam long id) {
        LOGGER.info("Deleting entity id: [" + id + "]");
        EntityManagerImpl.getInstance().removeEntity(id);
    }
}
