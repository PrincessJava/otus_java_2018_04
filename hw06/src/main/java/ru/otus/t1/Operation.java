package ru.otus.t1;

public enum Operation {
    INFO,    //остаток на счете
    DEPOSIT, //внести деньги
    WITHDRAW,//снять деньги
    EXIT;    //завершить обслуживание


    public static Operation getAllowableOperationByOrdinal(Integer i) {
        switch (i) {
            case 1:
                return Operation.INFO;
            case 2:
                return Operation.DEPOSIT;
            case 3:
                return Operation.WITHDRAW;
            case 4:
                return Operation.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
