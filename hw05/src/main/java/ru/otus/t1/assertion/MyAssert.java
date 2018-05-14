package ru.otus.t1.assertion;

import ru.otus.t1.exception.MyAssertEqualsException;
import ru.otus.t1.exception.MyAssertFalseException;
import ru.otus.t1.exception.MyAssertTrueException;

public class MyAssert {

    public static void MyAssertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            try {
                throw new MyAssertEqualsException(expected.toString(), actual.toString());
            } catch (MyAssertEqualsException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void MyAssertTrue(Boolean condition) {
        if (!condition) {
            try {
                throw new MyAssertTrueException();
            } catch (MyAssertTrueException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void MyAssertFalse(Boolean condition) {
        if (condition) {
            try {
                throw new MyAssertFalseException();
            } catch (MyAssertFalseException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
