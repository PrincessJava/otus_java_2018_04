package t1;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import t1.annotation.After;
import t1.annotation.Before;
import t1.annotation.Test;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TestFramework {
    private static final String packageName = "t1.test";
    private Method beforeMethod;
    private Method afterMethod;
    private Class<?> clazz;
    private List<Method> testMethods;

    public static void main(String[] args) {
        TestFramework testFramework = new TestFramework();

        for (String className : testFramework.getAllTestClasses(packageName)) {
            try {
                testFramework.prepare(className);
                testFramework.run();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> getAllTestClasses(String packageName) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                        .setUrls(ClasspathHelper.forJavaClassPath())
                        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
        return reflections.getAllTypes();
    }

    private void prepare(String className) throws ClassNotFoundException {
        beforeMethod = null;
        afterMethod = null;
        clazz = null;
        testMethods = new LinkedList<>();

        clazz = Class.forName(className);
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
    }

    private void run() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (@Nonnull Method m : testMethods) {
            if (beforeMethod != null) {
                beforeMethod.invoke(clazz.newInstance());
            }
            m.invoke(clazz.newInstance());
            if (afterMethod != null) {
                afterMethod.invoke(clazz.newInstance());
            }
        }
    }

}
