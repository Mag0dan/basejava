package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Storage is full. Can`s save your resume " + r.getUuid() + ". Sorry");
            return;
        }
        int index = findIndexByUUID(r.getUuid());
        if (index == -1) {
            storage[size] = r;
            size++;
            return;
        }
        System.out.println("Your resume " + r.getUuid() + " already saved");
    }

    public void update(Resume r) {
        if (r == null) {
            System.out.println("Nothing to update");
            return;
        }
        int index = findIndexByUUID(r.getUuid());
        if (index == -1) {
            System.out.println("Resume uuid " + r.getUuid() + " not found");
            return;
        }
        storage[index] = r;
        System.out.println("Resume successfully saved");

    }

    public Resume get(String uuid) {
        int index = findIndexByUUID(uuid);
        if (index == -1) {
            System.out.println("Resume uuid " + uuid + " not found");
            return null;
        }
        return storage[index];

    }

    public void delete(String uuid) {
        int index = findIndexByUUID(uuid);
        if (index == -1) {
            System.out.println("Resume uuid " + uuid + " not found");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, resumes.length);
        return resumes;
    }

    public int size() {
        return size;
    }

    private int findIndexByUUID(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
