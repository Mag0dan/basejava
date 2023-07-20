package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    protected Storage emptyStorage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        final Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void getAll() {
        final Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        Assert.assertEquals(3, storage.getAll().length);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void save() {
        final Resume resume = new Resume(UUID_4);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveFullStorage() {
        for (int i = storage.size(); i < storage.getLimitSize(); i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException storageException) {
                Assert.fail("Переполнение произошло раньше времени");
            }
        }
        storage.save(new Resume());

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}