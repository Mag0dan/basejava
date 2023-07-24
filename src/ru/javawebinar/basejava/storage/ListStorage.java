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
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int index = (int) searchKey;
        return index >= 0 && index < storage.size();
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set((int) index, r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove((int) index);
    }
}
