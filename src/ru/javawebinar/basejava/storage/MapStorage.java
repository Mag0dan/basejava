package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.containsKey(uuid) ? 0 : -1;
    }

    @Override
    protected void add(int index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void set(int index, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected void remove(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected Resume getByIndex(int index, String uuid) {
        return storage.get(uuid);
    }
}
