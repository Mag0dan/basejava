import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        storage[getNextIndex()] = r;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume!= null && uuid.equals(resume.uuid))
                return resume;
        }
        return null;
    }

    void delete(String uuid) {
        boolean deletedFlag = false;
        for (int i = 0; i < storage.length; i++) {
            if(deletedFlag) {
                storage[i-1] = storage[i];
            } else if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                deletedFlag = true;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[getNextIndex()];
        System.arraycopy(storage, 0, resumes, 0, resumes.length);
        return resumes;
    }

    int size() {
        return getNextIndex();
    }

    private int getNextIndex() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                return i;
        }
        return storage.length-1;
    }
}
