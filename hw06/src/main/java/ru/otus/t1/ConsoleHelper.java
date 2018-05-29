package ru.otus.t1;

import ru.otus.t1.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = reader.readLine();
            if (line.equalsIgnoreCase("exit_ru.properties")) throw new InterruptOperationException();
        } catch (IOException e) {
        }
        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        System.out.println("Enter currency code, please");
        String code = readString().trim();
        if (code.length() == 3) {
            code = code.toUpperCase();
            return code;
        }
        System.out.println("Data are not valid.");
        return askCurrencyCode();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage("Enter nominal and value, please");
        String[] nominalAndValue = readString().split("\\s+");
        return nominalAndValue;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Enter operation type, please: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        try {
            int operationType = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(operationType);
        } catch (IllegalArgumentException e) {
        } catch (Exception e1) {
        }
        writeMessage("Data are not valid.");
        return askOperation();
    }
}
