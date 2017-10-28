package ru.reeson2003.my3d.client.terrains;

/**
 * Created by User on 28.10.2017.
 */
public class TerrainLoadException extends Exception{
    public TerrainLoadException() {
    }

    public TerrainLoadException(String message) {
        super(message);
    }

    public TerrainLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerrainLoadException(Throwable cause) {
        super(cause);
    }

    public TerrainLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
