package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume r = new Resume("Name");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        final Method[] resumeMethods = r.getClass().getMethods();
        for (Method resumeMethod : resumeMethods) {
            if (resumeMethod.getName().equals("toString")) {
                System.out.println(resumeMethod.invoke(r));
            }
        }
        System.out.println(r);
    }
}