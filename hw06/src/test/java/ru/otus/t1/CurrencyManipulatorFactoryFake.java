package ru.otus.t1;

public class CurrencyManipulatorFactoryFake {
    private CurrencyManipulatorFactoryFake() {
    }

    public static CurrencyManipulator getCurrencyManipulator() {
        return new CurrencyManipulator();
    }
}
