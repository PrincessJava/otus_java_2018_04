package ru.otus.t1.proxy;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestMethodsCallHandler {
    private List<Method> afterMethods;
    private List<Method> testMethods;
    private List<Method> beforeMethods;

    private Class<?> clazz;

    public TestMethodsCallHandler(List<Method> beforeMethods, List<Method> testMethods, List<Method> afterMethods, Class<?> clazz) {
        this.afterMethods = afterMethods;
        this.testMethods = testMethods;
        this.beforeMethods = beforeMethods;
        this.clazz = clazz;
    }

    public void runTests() throws InstantiationException, IllegalAccessException {

        for (@Nonnull Method m : testMethods) {
            Object c = clazz.newInstance();
            if (!beforeMethods.isEmpty()) {
                invokeTestMethod(beforeMethods.get(0), c);
            }

            invokeTestMethod(m, c);

            if (!afterMethods.isEmpty()) {
                invokeTestMethod(afterMethods.get(0), c);
            }
        }
    }

    private void invokeTestMethod(Method method, Object c) throws IllegalAccessException {
        try {
            method.invoke(c);
        } catch (InvocationTargetException e) {
            System.err.println(e.getTargetException().getMessage());
        }
    }
}
