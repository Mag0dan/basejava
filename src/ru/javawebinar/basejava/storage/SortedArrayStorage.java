package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        if (r == null) {
            System.out.println("Nothing to save");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Your resume " + r.getUuid() + " already saved");
            return;
        }
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full. Can`s save your resume " + r.getUuid() + ". Sorry");
            return;
        }
        index = -index - 1;
        if (size >= index) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Resume uuid " + uuid + " not found");
            return;
        }
        if (size > index) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
        storage[size-1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}