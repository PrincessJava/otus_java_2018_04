package ru.otus.t1;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;
import ru.otus.t1.proxy.FrameworkExecutor;
import ru.otus.t1.proxy.TestMethodsCallHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestFramework implements FrameworkExecutor {
    private String packageName;
    private String className;
    private TestMethodsCallHandler methodsCallHandler;

    public TestFramework(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    @Override
    public void run() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        methodsCallHandler = new TestMethodsCallHandler();
        prepare(className);
        methodsCallHandler.runTests();
    }

    private void prepare(String className) throws ClassNotFoundException {
        List<Method> testMethods = new LinkedList<>();
        Class<?> clazz = Class.forName(packageName.concat(className));
        methodsCallHandler.setClazz(clazz);

        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            } else if (m.isAnnotationPresent(Before.class)) {
                methodsCallHandler.setBeforeMethod(m);
            } else if (m.isAnnotationPresent(After.class)) {
                methodsCallHandler.setAfterMethod(m);
            }
        }
        methodsCallHandler.setTestMethods(testMethods);
    }
}
