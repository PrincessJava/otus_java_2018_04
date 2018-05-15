package ru.otus.t1.proxy;

import ru.otus.t1.TestFramework;

public class FrameworkExecutorImpl implements FrameworkExecutor {
    private TestFramework testFramework;

    private FrameworkExecutorImpl() {
        this.testFramework = new TestFramework("ru.otus.t1.test.", "TestClass");
    }

    public static void main(String[] args) {
        FrameworkExecutorImpl executor = new FrameworkExecutorImpl();

        try {
            executor.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() throws Exception {
        testFramework.run();

    }

    //todo сделать получение классов из директории test в runtime
 /*   private Set<String> getAllTestClasses(String packageName) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                        .setUrls(ClasspathHelper.forJavaClassPath())
                        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
        return reflections.getAllTypes();
    }*/
}
