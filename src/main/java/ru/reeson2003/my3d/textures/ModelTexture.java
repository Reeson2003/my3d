package ru.reeson2003.my3d.textures;

/**
 * Created by Pavel Gavrilov on 12.10.2017.
 */
public class ModelTexture {
    private int id;
    private float shineDamper = 1;
    private float reflectivity = 0;
    private boolean hasTransprency = false;

    public ModelTexture(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public boolean isHasTransprency() {
        return hasTransprency;
    }

    public void setHasTransprency(boolean hasTransprency) {
        this.hasTransprency = hasTransprency;
    }
}
