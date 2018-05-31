package ru.otus.t1;

import ru.otus.t1.command.CommandExecutor;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.Locale;

public class ATM {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru"));
        Operation op;
        try {
            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            }
            while (op != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }
}