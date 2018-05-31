package ru.otus.t1;

public enum Nominal {
    FIVETHOUSAND,
    ONETHOUSAND,
    FIVEHUNDRED,
    ONEHUNDRED,
    FIFTY,
    TEN;

    public int nominalValue() {
        switch (this) {
            case TEN:
                return 10;
            case FIFTY:
                return 50;
            case ONEHUNDRED:
                return 100;
            case FIVEHUNDRED:
                return 500;
            case ONETHOUSAND:
                return 1000;
            case FIVETHOUSAND:
                return 5000;
            default:
                return 0;
        }
    }
}
