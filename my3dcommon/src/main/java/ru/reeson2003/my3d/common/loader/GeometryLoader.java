package ru.reeson2003.my3d.common.loader;

import ru.reeson2003.my3d.common.Geometry;

import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public interface GeometryLoader {
    Map<String, List<Geometry>> load() throws Exception;
}
