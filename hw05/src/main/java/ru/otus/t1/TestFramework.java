package ru.otus.t1;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;
import ru.otus.t1.proxy.FrameworkExecutor;
import ru.otus.t1.proxy.TestMethodsCallHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
        prepare(className);
        methodsCallHandler.runTests();
    }

    private void prepare(String className) throws Exception {
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeMethods = new ArrayList<>();

        Class<?> clazz = Class.forName(packageName.concat(className));

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                processAnnotation(m, Test.class, testMethods);
            } else if (m.isAnnotationPresent(Before.class)) {
                processAnnotation(m, Before.class, beforeMethods);
            } else if (m.isAnnotationPresent(After.class)) {
                processAnnotation(m, After.class, afterMethods);
            }
        }
        methodsCallHandler = new TestMethodsCallHandler(beforeMethods, testMethods, afterMethods, clazz);
    }

    private void processAnnotation(Method method, Class<? extends Annotation> annotation, List<Method> list) throws Exception {
        if (method.getParameters() == null) {
            throw new Exception("Method annotated with @" + annotation.getSimpleName() + " should not have parameters");
        }
        if (list != null) {
            list.add(method);
        }
    }
}
