package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.Nominal;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("deposit");

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            try {
                String[] values = ConsoleHelper.getNominalAndAmount();
                CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getCurrencyManipulator();
                Nominal dom = Nominal.valueOf(values[0]);
                int count = Integer.parseInt(values[1]);
                currencyManipulator.addAmount(dom, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), dom.toString(), count));
                ConsoleHelper.writeMessage(res.getString("continue.question"));
                if (ConsoleHelper.readString().equals(res.getString("yes"))) {
                    continue;
                }
                break;
            } catch (NumberFormatException ex) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
