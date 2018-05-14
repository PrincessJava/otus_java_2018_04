package ru.otus.t1.assertion;

import ru.otus.t1.exception.MyAssertionException;

public class MyAssert {

    public static void MyAssertEquals(String message, Object expected, Object actual) throws MyAssertionException {
        if (!expected.equals(actual)) {
            fail(message, expected, actual);
        }
    }

    public static void MyAssertEquals(Object expected, Object actual) throws MyAssertionException {
        if (!expected.equals(actual)) {
            fail(null, expected, actual);
        }
    }

    public static void MyAssertTrue(String message, Boolean condition) throws MyAssertionException {
        if (!condition) {
            fail(message);
        }
    }

    public static void MyAssertTrue(Boolean condition) throws MyAssertionException {
        if (!condition) {
            fail(null);
        }
    }

    public static void MyAssertFalse(String message, Boolean condition) throws MyAssertionException {
        if (condition) {
            fail(message);
        }
    }

    public static void MyAssertFalse(Boolean condition) throws MyAssertionException {
        if (condition) {
            fail(null);
        }
    }

    private static void fail(String message) throws MyAssertionException {
        if (message == null) {
            throw new MyAssertionException();
        } else {
            throw new MyAssertionException(message);
        }
    }

    private static void fail(String message, Object expected, Object actual) throws MyAssertionException {
        throw new MyAssertionException(format(message, expected, actual));
    }

    private static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null && !message.equals("")) {
            formatted = message + " ";
        }

        String expectedString = expected.toString();
        String actualString = actual.toString();

        if (expected.equals(actual)) {
            return formatted + "expected: "
                    + expectedString
                    + " but was: " + actualString;
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                    + actualString + ">";
        }
    }

}
