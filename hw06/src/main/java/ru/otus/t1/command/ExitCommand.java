package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.exception.InterruptOperationException;

public class ExitCommand implements Command {
//    private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.exit_ru.properties");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите выйти? <y,n>");
        String answer = ConsoleHelper.readString();
        if (answer.equals("y")) {
//            ConsoleHelper.writeMessage(res.getString("thank.message"));
            ConsoleHelper.writeMessage("Спасибо за визит!");
        }
    }
}
