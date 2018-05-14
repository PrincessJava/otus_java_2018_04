package ru.otus.t1.exception;

public class MyAbstractAssertionException extends Exception {
    private String message = "Assertion Error:\n";

    @Override
    public String getMessage() {
        return message;
    }
}
