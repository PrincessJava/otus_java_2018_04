package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.Nominal;
import ru.otus.t1.exception.InterruptOperationException;
import ru.otus.t1.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

public class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle("withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getCurrencyManipulator();
        for (; ; ) {
            ConsoleHelper.writeMessage(res.getString("enter.sum"));
            String in = ConsoleHelper.readString();
            if (!in.matches("\\d+") && Integer.parseInt(in) <= 0) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                continue;
            }
            int sum = Integer.parseInt(in);
            if (sum <= 0) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (currencyManipulator.isAmountAvailable(sum)) {
                try {
                    Map<Nominal, Integer> answ = currencyManipulator.withdrawAmount(sum);
                    for (Map.Entry<Nominal, Integer> e : answ.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + e.getKey() + " - " + e.getValue());
                    }
                    ConsoleHelper.writeMessage(res.getString("success.transaction"));
                    return;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.nom.error"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("not.enough.money.error"));
            }
        }
    }
}
