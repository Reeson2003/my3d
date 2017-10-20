package ru.reeson2003.my3d.common;

import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
public class TerrainObjectGeometries implements Map<Long, List<Geometry>> {
    private static TerrainObjectGeometries instance;
    private static Map<Long, List<Geometry>> geometryHashMap;
    private static Exception exception;

    static {
        geometryHashMap = null;
        try {
            geometryHashMap = BaseLoaderFactory.getGeometryLoader().load();
            if (geometryHashMap == null)
                instance = null;
            else
                instance = new TerrainObjectGeometries();
        } catch (Exception e) {
            exception = e;
            instance = null;
        }
    }

    private TerrainObjectGeometries() {
    }

    public static TerrainObjectGeometries getInstance() throws IOException {
        if (instance == null)
            throw new IOException("Can not load 'terrainObjects.geometry'", exception);
        return instance;
    }

    @Override
    public void replaceAll(BiFunction<? super Long, ? super List<Geometry>, ? extends List<Geometry>> biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> putIfAbsent(Long aLong, List<Geometry> geometry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o, Object o1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(Long aLong, List<Geometry> geometry, List<Geometry> v1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> replace(Long aLong, List<Geometry> geometry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> computeIfAbsent(Long aLong, Function<? super Long, ? extends List<Geometry>> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> computeIfPresent(Long aLong, BiFunction<? super Long, ? super List<Geometry>, ? extends List<Geometry>> biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> compute(Long aLong, BiFunction<? super Long, ? super List<Geometry>, ? extends List<Geometry>> biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> merge(Long aLong, List<Geometry> geometry, BiFunction<? super List<Geometry>, ? super List<Geometry>, ? extends List<Geometry>> biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> put(Long aLong, List<Geometry> geometry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Geometry> remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Long, ? extends List<Geometry>> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return geometryHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return geometryHashMap.isEmpty();
    }

    @Override
    public List<Geometry> get(Object o) {
        return geometryHashMap.get(o);
    }

    @Override
    public boolean containsKey(Object o) {
        return geometryHashMap.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return geometryHashMap.containsValue(o);
    }

    @Override
    public Set<Long> keySet() {
        return geometryHashMap.keySet();
    }

    @Override
    public Collection<List<Geometry>> values() {
        return geometryHashMap.values();
    }

    @Override
    public Set<Entry<Long, List<Geometry>>> entrySet() {
        return geometryHashMap.entrySet();
    }

    @Override
    public List<Geometry> getOrDefault(Object o, List<Geometry> geometry) {
        return geometryHashMap.getOrDefault(o, geometry);
    }

    @Override
    public void forEach(BiConsumer<? super Long, ? super List<Geometry>> biConsumer) {
        geometryHashMap.forEach(biConsumer);
    }

    @Override
    public boolean equals(Object o) {
        return geometryHashMap.equals(o);
    }

    @Override
    public int hashCode() {
        return geometryHashMap.hashCode();
    }

    @Override
    public String toString() {
        return geometryHashMap.toString();
    }
}
