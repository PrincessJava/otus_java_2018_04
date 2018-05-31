package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

public class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("info");

    @Override
    public void execute() {
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getCurrencyManipulator();
        if (!currencyManipulator.hasMoney()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));

        } else {
            ConsoleHelper.writeMessage(currencyManipulator.getTotalAmount());
        }
    }
}
