package ru.otus.t1;

public final class CurrencyManipulatorFactory {

    private CurrencyManipulatorFactory() {
    }

    private static CurrencyManipulator currencyManipulator;

    public static CurrencyManipulator getCurrencyManipulator() {
        if (currencyManipulator == null) {
            currencyManipulator = new CurrencyManipulator();
        }

        return currencyManipulator;
    }
}
