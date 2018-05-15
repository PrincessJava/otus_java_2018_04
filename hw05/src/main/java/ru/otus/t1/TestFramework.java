package ru.otus.t1;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;
import ru.otus.t1.proxy.FrameworkExecutor;
import ru.otus.t1.proxy.TestMethodsCallHandler;

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
    public void run() throws Exception {
        methodsCallHandler = new TestMethodsCallHandler();
        prepare(className);
        methodsCallHandler.runTests();
    }

    private void prepare(String className) throws Exception {
        List<Method> testMethods = new LinkedList<>();
        Class<?> clazz = Class.forName(packageName.concat(className));
        methodsCallHandler.setClazz(clazz);

        Method[] methods = clazz.getDeclaredMethods();

        //todo сделать красивше
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                if (m.getParameters() == null) {
                    throw new Exception("Method annotated with @" + Test.class.getSimpleName() + " should not have parameters");
                }
                testMethods.add(m);
            } else if (m.isAnnotationPresent(Before.class)) {
                if (m.getParameters() == null) {
                    throw new Exception("Method annotated with @" + Before.class.getSimpleName() + " should not have parameters");
                }
                methodsCallHandler.setBeforeMethod(m);
            } else if (m.isAnnotationPresent(After.class)) {
                if (m.getParameters() == null) {
                    throw new Exception("Method annotated with @" + After.class.getSimpleName() + " should not have parameters");
                }
                methodsCallHandler.setAfterMethod(m);
            }
        }
        methodsCallHandler.setTestMethods(testMethods);
    }
}
