package ru.reeson2003.my3d.client.models;

import ru.reeson2003.my3d.client.renderEngine.Loader;
import ru.reeson2003.my3d.client.renderEngine.OBJLoader;
import ru.reeson2003.my3d.client.textures.ModelTexture;

import java.io.IOException;
import java.util.*;


/**
 * Created by Pavel Gavrilov on 25.10.2017.
 */
public class ModelLoaderInternal implements ModelLoader {
    private static ModelLoader instance = null;

    private SortedMap<String, TexturedModel> models;

    private ModelLoaderInternal(Loader loader) throws IOException {
        this.models = loadModels(loader);
    }

    public static ModelLoader getInstance(Loader loader) throws IOException {
        if (instance == null)
            instance = new ModelLoaderInternal(loader);
        return instance;
    }

    @Override
    public SortedMap<String, TexturedModel> getModels(Loader loader) throws IOException {
        return models;
    }

    @Override
    public TexturedModel getModel(String id) {
        return models.get(id);
    }

    private SortedMap<String,TexturedModel> loadModels(Loader loader) throws IOException {
        SortedMap<String, TexturedModel> result = new TreeMap<>();
        Map<String, String[]> descriptor = loadDescriptors();
        for (Map.Entry<String, String[]> entry : descriptor.entrySet()) {
            String[] desc = entry.getValue();
            RawModel rawModel = OBJLoader.loadModel(desc[0], loader);
            ModelTexture texture = new ModelTexture(loader.loadTexture(desc[1]));
            TexturedModel model = new TexturedModel(rawModel, texture);
            result.put(entry.getKey(), model);
        }
        return result;
    }

    private Map<String, String[]> loadDescriptors() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("models/models.properties"));
        Map<String, String[]> result = new HashMap<>();
        String delimiter = properties.getProperty("delimiter");
        properties.remove("delimiter");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            String[] value = val.split(delimiter);
            result.put(key, value);
        }
        return result;
    }
}
