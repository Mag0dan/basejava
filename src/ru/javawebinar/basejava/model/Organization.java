package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;

    private final List<Activity> activities;
//    private final LocalDate startDate;
//    private final LocalDate endDate;
//    private final String title;
//    private final String description;

    public Organization(String name, String url, List<Activity> activities) {
//        Objects.requireNonNull(startDate, "startDate must not be null");
//        Objects.requireNonNull(endDate, "endDate must not be null");
//        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(activities, "activities must not be null");
        this.homePage = new Link(name, url);
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.title = title;
//        this.description = description;
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", activities=" + activities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && activities.equals(that.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, activities);
    }

    //    @Override
//    public String toString() {
//        return "Organization{" +
//                "homePage=" + homePage +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Organization that = (Organization) o;
//        return homePage.equals(that.homePage) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && title.equals(that.title) && Objects.equals(description, that.description);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(homePage, startDate, endDate, title, description);
//    }
}
