package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question"));
        String answer = ConsoleHelper.readString();
        if (answer.equals("y")) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
