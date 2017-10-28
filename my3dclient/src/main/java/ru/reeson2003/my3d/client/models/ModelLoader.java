package ru.reeson2003.my3d.client.models;

import ru.reeson2003.my3d.client.renderEngine.Loader;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;


/**
 * Created by Pavel Gavrilov on 25.10.2017.
 */
public interface ModelLoader {
    SortedMap<String, TexturedModel> getModels(Loader loader) throws IOException;

    TexturedModel getModel(String id);
}
