package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.exit_ru.properties");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (answer.equals(res.getString("yes")))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
