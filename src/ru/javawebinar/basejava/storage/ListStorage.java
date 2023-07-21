package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void add(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void set(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected void remove(int index, String uuid) {
        storage.remove(index);
    }

    @Override
    protected Resume getByIndex(int index, String uuid) {
        return storage.get(index);
    }
}
