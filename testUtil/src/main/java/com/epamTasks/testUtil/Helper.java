package com.epamTasks.testUtil;

import java.lang.reflect.Field;

public class Helper {
    public static Field makeFieldPublic(Class<?> clas, String field) throws NoSuchFieldException {
        Field privateField = clas.getDeclaredField(field);
        privateField.setAccessible(true);
        return privateField;
    }
}