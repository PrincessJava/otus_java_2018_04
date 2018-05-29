package ru.otus.t1.exception;

public class MyAssertionException extends RuntimeException {
    private String message = "Assertion Error";

    public MyAssertionException() {
    }

    public MyAssertionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
