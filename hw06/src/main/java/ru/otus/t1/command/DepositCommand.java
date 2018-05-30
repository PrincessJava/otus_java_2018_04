package ru.otus.t1.command;

import ru.otus.t1.ConsoleHelper;
import ru.otus.t1.CurrencyManipulator;
import ru.otus.t1.CurrencyManipulatorFactory;
import ru.otus.t1.exception.InterruptOperationException;
import ru.otus.t1.myTry.Nominal;

public class DepositCommand implements Command {
//    private ResourceBundle res = ResourceBundle.getBundle("ru.otus.t1.deposit.properties");

    @Override
    public void execute() throws InterruptOperationException {
//        ConsoleHelper.writeMessage(res.getString("before"));
//        String code = ConsoleHelper.askCurrencyCode();
        while (true) {
            try {
//                String[] values = ConsoleHelper.getValidTwoDigits(code);
                String[] values = ConsoleHelper.getNominalAndAmount();
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(Nominal.valueOf(values[0]));
                Nominal dom = Nominal.valueOf(values[0]);
                int count = Integer.parseInt(values[1]);
                manipulator.addAmount(dom, count);
//                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (dom * count), code));
                ConsoleHelper.writeMessage(String.format("%d %s успешно внесено", (Integer.parseInt(dom.toString()) * count)));
                break;
            } catch (NumberFormatException ex) {
//                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                ConsoleHelper.writeMessage("Пожалуйста, введите корректные данные");
            }
        }
    }
}
