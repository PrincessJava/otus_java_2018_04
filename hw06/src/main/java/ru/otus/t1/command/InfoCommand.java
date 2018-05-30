package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;

public class InfoCommand implements Command {
    //private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.info_en");

    @Override
    public void execute() {
        //ConsoleHelper.writeMessage(res.getString("before"));
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            ConsoleHelper.writeMessage("Нет доступных стредств");
        } else {
            int counter = 0;
            for (CurrencyManipulator item : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                if (item.hasMoney()) {
                    ConsoleHelper.writeMessage(item.getCurrencyCode() + " - " + item.getTotalAmount());
                } else counter++;
            }

            if (counter == CurrencyManipulatorFactory.getAllCurrencyManipulators().size()) {
                ConsoleHelper.writeMessage("Нет доступных стредств");
            }
        }

    }
}
