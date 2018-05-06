package ru.otus.t1;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestFramework {
    private List<Method> testMethods = new LinkedList<>();
    private Method beforeMethod;
    private Method afterMethod;
    private Class<?> clazz;

    public static void main(String[] args) throws Exception {
        TestFramework testFramework = new TestFramework();
        testFramework.prepare("ru.otus.t1", "TestClass");
        testFramework.run();
    }

    private void run() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Method m : testMethods) {
            beforeMethod.invoke(clazz.newInstance());
            m.invoke(clazz.newInstance());
            afterMethod.invoke(clazz.newInstance());
        }

    }

    private void prepare(String packageName, String className) {
        try {
            clazz = Class.forName(packageName + "." + className);
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    testMethods.add(m);
                } else if (m.isAnnotationPresent(Before.class)) {
                    beforeMethod = m;
                } else if (m.isAnnotationPresent(After.class)) {
                    afterMethod = m;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
