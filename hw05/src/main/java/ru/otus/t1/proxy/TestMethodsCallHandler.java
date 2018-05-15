package ru.otus.t1.proxy;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestMethodsCallHandler {
    private Method beforeMethod;
    private Method afterMethod;
    private List<Method> testMethods;
    private Class<?> clazz;

    public TestMethodsCallHandler() {
    }

    public void setBeforeMethod(Method beforeMethod) {
        this.beforeMethod = beforeMethod;
    }

    public void setAfterMethod(Method afterMethod) {
        this.afterMethod = afterMethod;
    }

    public void setTestMethods(List<Method> testMethods) {
        this.testMethods = testMethods;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void runTests() throws IllegalAccessException, InstantiationException {

        for (@Nonnull Method m : testMethods) {
            try {
                if (beforeMethod != null) {
                    beforeMethod.invoke(clazz.newInstance());
                }
                m.invoke(clazz.newInstance());
                if (afterMethod != null) {
                    afterMethod.invoke(clazz.newInstance());
                }
            } catch (InvocationTargetException e) {
                System.err.println(e.getTargetException().getMessage());
            }
        }
    }
}
