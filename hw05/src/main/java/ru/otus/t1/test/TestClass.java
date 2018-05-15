package ru.otus.t1.test;

import ru.otus.t1.annotation.After;
import ru.otus.t1.annotation.Before;
import ru.otus.t1.annotation.Test;
import ru.otus.t1.assertion.MyAssert;
import ru.otus.t1.exception.MyAssertionException;

public class TestClass {

    @Before
    public void beforeMethod() {
        System.out.println("@Before");
    }

    @Test
    public void testMethod1() throws MyAssertionException {
        System.out.println("@Test 1");
        MyAssert.MyAssertFalse(true);
    }

    @Test
    public void testMethod2() {
        System.out.println("@Test 2");
    }

    @Test
    public void testMethod3() throws MyAssertionException {
        System.out.println("@Test 3");
        MyAssert.MyAssertEquals(2, 3);
    }

    @After
    public void afterMethod() {
        System.out.println("@After");
    }

}
