package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void save(Resume r) {
        final int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        add(index, r);
    }

    @Override
    public void update(Resume r) {
        final int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        set(index, r);
    }

    @Override
    public void delete(String uuid) {
        final int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        remove(index, uuid);
    }

    @Override
    public Resume get(String uuid) {
        final int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getByIndex(index, uuid);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(int index, Resume r);

    protected abstract void set(int index, Resume r);

    protected abstract void remove(int index, String uuid);

    protected abstract Resume getByIndex(int index, String uuid);
}
