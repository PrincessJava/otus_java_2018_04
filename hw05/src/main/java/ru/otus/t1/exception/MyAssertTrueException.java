package ru.otus.t1.exception;

public class MyAssertTrueException extends MyAbstractAssertionException {
    @Override
    public String getMessage() {
        return super.getMessage().concat("Condition is not true");
    }
}
