package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

public class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        } else {
            int counter = 0;
            for (CurrencyManipulator item : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                if (item.hasMoney()) {
                    ConsoleHelper.writeMessage(item.getCurrencyCode() + " - " + item.getTotalAmount());
                } else counter++;
            }

            if (counter == CurrencyManipulatorFactory.getAllCurrencyManipulators().size()) {
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }

    }
}
