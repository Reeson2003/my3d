import ru.reeson2003.my3d.transport.rest.RestLoader;
import ru.reeson2003.my3d.common.Geometry;

import java.util.Map;

/**
 * Created by User on 20.10.2017.
 */
public class RestTester {
    public static void main(String[] args) {
        Geometry geometry = new Geometry(1,2,3,4,5,6,7);
        RestLoader loader = new RestLoader("http://192.168.1.50:8080");
        long id = loader.registerEntity(geometry);
        Map<Long, Geometry> longGeometryMap = loader.loadEntityObjects();
        System.out.println(longGeometryMap);
//        loader.updateEntity(id, geometry);
    }
}
