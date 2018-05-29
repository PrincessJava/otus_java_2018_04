package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.deposit.properties");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        while (true) {
            try {
                String[] values = ConsoleHelper.getValidTwoDigits(code);
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
                int dom = Integer.parseInt(values[0]);
                int count = Integer.parseInt(values[1]);
                manipulator.addAmount(dom, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (dom * count), code));
                break;
            } catch (NumberFormatException ex) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
