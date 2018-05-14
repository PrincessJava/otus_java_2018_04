package ru.otus.t1.test;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;
import ru.otus.t1.assertion.MyAssert;

public class TestClass {

    @Before
    public void beforeMethod() {
        System.out.println("@Before");
    }

    @Test
    public void testMethod1() {
        System.out.println("@Test 1");
        MyAssert.MyAssertFalse(true);
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
