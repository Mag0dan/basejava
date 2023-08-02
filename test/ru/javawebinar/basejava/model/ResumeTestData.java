package ru.javawebinar.basejava.model;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    @Test
    public void getResumeTest() {
        String uuid = "UUID123";
        String fullName = "Богдан";
        final Resume resume = getResume(uuid, fullName);

        Assert.assertEquals(resume.getUuid(), uuid);
        Assert.assertEquals(resume.getFullName(), fullName);

        Assert.assertEquals(resume.getSection(SectionType.OBJECTIVE), new TextSection("Позиция Богдана"));
        Assert.assertEquals(resume.getSection(SectionType.PERSONAL), new TextSection("Личные качества Богдана"));
        Assert.assertEquals(resume.getSection(SectionType.ACHIEVEMENT), achievementSection());
        Assert.assertEquals(resume.getSection(SectionType.QUALIFICATIONS), qualificationsSection());
        Assert.assertEquals(resume.getSection(SectionType.EXPERIENCE), experienceSection());
        Assert.assertEquals(resume.getSection(SectionType.EDUCATION), educationSection());

        Assert.assertEquals(resume.getContact(ContactType.GITHUB), "https://github.com/gkislin");
        Assert.assertEquals(resume.getContact(ContactType.PHONE), "+79031234567");
        Assert.assertEquals(resume.getContact(ContactType.MAIL), "tata@mail.ru");
        Assert.assertEquals(resume.getContact(ContactType.HOMEPAGE), "http://gkislin.ru/");
    }


    public static Resume getResume(String uuid, String fullName) {
        final Resume resume = new Resume(uuid, fullName);

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Позиция Богдана"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Личные качества Богдана"));
        resume.setSection(SectionType.ACHIEVEMENT, achievementSection());
        resume.setSection(SectionType.QUALIFICATIONS, qualificationsSection());
        resume.setSection(SectionType.EXPERIENCE, experienceSection());
        resume.setSection(SectionType.EDUCATION, educationSection());

        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.PHONE, "+79031234567");
        resume.setContact(ContactType.MAIL, "tata@mail.ru");
        resume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
        return resume;
    }

    private static ListSection achievementSection() {
        List<String> achievements = new ArrayList<>();
        achievements.add("Достижение 1");
        achievements.add("Достижение 2");
        achievements.add("Достижение 3");
        achievements.add("Достижение 4");
        achievements.add("Достижение 5");
        achievements.add("Достижение 6");
        return new ListSection(achievements);
    }

    private static ListSection qualificationsSection() {
        List<String> qualifications = new ArrayList<>();
        qualifications.add("Квалификация 1");
        qualifications.add("Квалификация 2");
        qualifications.add("Квалификация 3");
        qualifications.add("Квалификация 4");
        qualifications.add("Квалификация 5");
        return new ListSection(qualifications);
    }

    private static OrganizationSection experienceSection() {
        List<Organization> organizations = new ArrayList<>();
        ArrayList<Organization.Position> activities = new ArrayList<>();

        activities.add(new Organization.Position(
                DateUtil.of(2013, Month.OCTOBER),
                DateUtil.of(2023, Month.AUGUST),
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        organizations.add(new Organization(new Link("Java Online Projects", "http://javaops.ru/"), activities));

        activities = new ArrayList<>();
        activities.add(new Organization.Position(
                DateUtil.of(2014, Month.OCTOBER),
                DateUtil.of(2016, Month.JANUARY),
                "Старший разработчик (backend)",
                "Описание 2"));
        organizations.add(new Organization(new Link("Wrike", "https://www.wrike.com/"), activities));

        return new OrganizationSection(organizations);
    }

    private static OrganizationSection educationSection() {
        List<Organization> organizations = new ArrayList<>();
        ArrayList<Organization.Position> activities = new ArrayList<>();

        activities.add(new Organization.Position(
                DateUtil.of(2013, Month.MARCH),
                DateUtil.of(2013, Month.MAY),
                "'Functional Programming Principles in Scala' by Martin Odersky",
                null));
        organizations.add(new Organization(new Link("Coursera", "https://www.coursera.org/course/progfun"), activities));

        activities = new ArrayList<>();
        activities.add(new Organization.Position(
                DateUtil.of(2011, Month.MARCH),
                DateUtil.of(2011, Month.APRIL),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                null));
        organizations.add(new Organization(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"), activities));

        activities = new ArrayList<>();
        activities.add(new Organization.Position(
                DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY),
                "Аспирантура (программист С, С++)",
                null));
        activities.add(new Organization.Position(
                DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY),
                "Инженер (программист Fortran, C)",
                null));
        organizations.add(new Organization(
                new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                activities));

        return new OrganizationSection(organizations);
    }

}
