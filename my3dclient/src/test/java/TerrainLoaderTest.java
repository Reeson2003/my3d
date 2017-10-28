import ru.reeson2003.my3d.transport.rest.RestLoader;
import ru.reeson2003.my3d.common.Geometry;

import java.util.List;
import java.util.Map;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainLoaderTest {
    public static void main(String[] args) {
        RestLoader loader = new RestLoader("http://localhost:8080");
        Map<Long, List<Geometry>> longListMap = loader.loadTerrainObjects();
        System.out.println(longListMap);
    }
}
