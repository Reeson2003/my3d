package ru.reeson2003.my3d.common.loader;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public interface TerrainObjectLoader {
    HashMap<String, Long> load() throws IOException;
}
