package ru.reeson2003.my3d.models;

import ru.reeson2003.my3d.textures.ModelTexture;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(RawModel rawModel, ModelTexture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }
}
