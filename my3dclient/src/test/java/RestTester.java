import ru.reeson2003.my3d.client.rest.RestLoader;
import ru.reeson2003.my3d.common.Geometry;

import java.util.Map;

/**
 * Created by User on 20.10.2017.
 */
public class RestTester {
    public static void main(String[] args) {
        long id = 10L;
        Geometry geometry = new Geometry(1,2,3,4,5,6,7);
        RestLoader loader = new RestLoader();
        loader.updateEntity(id, geometry);
        Map<Long, Geometry> longGeometryMap = loader.loadEntityObjects();
        System.out.println(longGeometryMap);
    }
}
