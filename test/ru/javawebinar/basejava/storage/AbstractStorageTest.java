package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.ResumeTestData;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1 = ResumeTestData.getResume(UUID_1, "3Петя Иванов");
    private static final Resume RESUME_2 = ResumeTestData.getResume(UUID_2, "1Коля Жуков");
    private static final Resume RESUME_3 = ResumeTestData.getResume(UUID_3, "2Эмиль Эрнестов");
    private static final Resume RESUME_4 = ResumeTestData.getResume(UUID_4, "Петя Иванов");

    protected AbstractStorageTest(Storage storage) {
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
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        final Resume resume = ResumeTestData.getResume(UUID_2, "Галя Петрова");
        resume.setContact(ContactType.PHONE, "+79333333333");
        resume.setContact(ContactType.LINKEDIN, "tata@mail.ru");
        resume.setContact(ContactType.HOMEPAGE, "http://gkislin.com/");
        storage.update(resume);
        assertSize(3);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

//    @Test
//    public void getAll() {
//        final Resume[] resumes = storage.getAll();
//        assertEquals(3, resumes.length);
//        assertEquals(RESUME_1, resumes[0]);
//        assertEquals(RESUME_2, resumes[1]);
//        assertEquals(RESUME_3, resumes[2]);
//    }

    @Test
    public void getListSorted() {
        final List<Resume> resumesList = storage.getAllSorted();
        assertEquals(3, resumesList.size());
        assertEquals(Arrays.asList(RESUME_2, RESUME_3, RESUME_1), resumesList);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        assertGet(RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
