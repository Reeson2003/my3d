package ru.reeson2003.my3d.ticker;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public interface Ticker {
    void addListener(TickerListener listener);

    void deleteListener(TickerListener listener);

    void tick();
}
