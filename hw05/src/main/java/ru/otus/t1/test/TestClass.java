package ru.otus.t1.test;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;

public class TestClass {

    @Before
    public void beforeMethod() {
        System.out.println("@Before");
    }

    @Test
    public void testMethod1() {
        System.out.println("@Test 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("@Test 2");
    }

    @After
    public void afterMethod() {
        System.out.println("@After");
    }

}
