package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        if (r == null) {
            System.out.println("Nothing to save");
            return;
        }
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Your resume " + r.getUuid() + " already saved");
            return;
        }
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full. Can`s save your resume " + r.getUuid() + ". Sorry");
            return;
        }
        storage[size] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume uuid " + uuid + " not found");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
