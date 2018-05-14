package ru.otus.t1.exception;

public class MyAssertEqualsException extends MyAbstractAssertionException {
    private String expected;
    private String actual;

    public MyAssertEqualsException(String expected, String actual) {
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return super.getMessage().concat("Expected: ").concat(expected).concat("Actual: ").concat(actual);
    }
}
