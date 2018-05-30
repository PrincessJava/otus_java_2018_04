package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.exception.InterruptOperationException;
import ru.otus.t1.exception.NotEnoughMoneyException;
import ru.otus.t1.myTry.Nominal;

import java.util.Map;

public class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
//        String code = ConsoleHelper.askCurrencyCode();
        String[] values = ConsoleHelper.getNominalAndAmount();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(Nominal.valueOf(values[0]));
        for (; ; ) {
            ConsoleHelper.writeMessage("Введите сумму: ");
            String in = ConsoleHelper.readString();
            if (!in.matches("\\d+")) {
                ConsoleHelper.writeMessage("Некоректные данные");
                continue;
            }
            int sum = Integer.parseInt(in);
            if (sum <= 0) {
                ConsoleHelper.writeMessage("Некоректные данные");
                continue;
            }
            if (cm.isAmountAvailable(sum)) {
                try {
                    Map<Nominal, Integer> answ = cm.withdrawAmount(sum);
                    for (Map.Entry<Nominal, Integer> e : answ.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + e.getKey() + " - " + e.getValue());
                    }
                    ConsoleHelper.writeMessage("Транзакция проведена успешно!");
                    return;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage("Указанную сумму выдать нельзя!");
                }
            } else {
                ConsoleHelper.writeMessage("Недостаточно денег на счету");
            }
        }
    }
}
