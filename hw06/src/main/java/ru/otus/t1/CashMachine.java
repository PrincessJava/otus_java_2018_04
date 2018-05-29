package ru.otus.t1;

import ru.otus.t1.command.CommandExecutor;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation op;
        try {
            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            }
            while (op != Operation.EXIT);
        } catch (InterruptOperationException e) {

        }

    }
}