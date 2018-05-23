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
            if (!beforeMethods.isEmpty()) {
                invokeTestMethod(beforeMethods.get(0));
            }

            invokeTestMethod(m);

            if (!afterMethods.isEmpty()) {
                invokeTestMethod(afterMethods.get(0));
            }
        }
    }

    private void invokeTestMethod(Method method) throws IllegalAccessException, InstantiationException {
        try {
            method.invoke(clazz.newInstance());
        } catch (InvocationTargetException e) {
            System.err.println(e.getTargetException().getMessage());
        }
    }
}
