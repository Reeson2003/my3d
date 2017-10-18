package ru.reeson2003.my3d.ticker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Gavrilov on 18.10.2017.
 */
public class TickerImpl implements Ticker {
    private static Ticker instance = null;
    private long period = 10;
    private List<TickerListener> listeners = new ArrayList<>();
    private long last;

    public TickerImpl(long periodMillis) {
        instance = this;
        this.period = periodMillis;
        last = System.currentTimeMillis();
    }

    public static Ticker getInstance() {
        return instance;
    }

    @Override
    public void addListener(TickerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void deleteListener(TickerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void tick() {
        long now = System.currentTimeMillis();
        if (now - last > period) {
            listeners.forEach(l -> l.tick());
            last = now;
        }
    }
}
