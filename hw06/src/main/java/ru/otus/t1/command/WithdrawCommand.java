package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.exception.InterruptOperationException;
import ru.otus.t1.exception.NotEnoughMoneyException;

import java.util.Map;

public class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
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
                    Map<Integer, Integer> answ = cm.withdrawAmount(sum);
                    for (Map.Entry<Integer, Integer> e : answ.entrySet()) {
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
