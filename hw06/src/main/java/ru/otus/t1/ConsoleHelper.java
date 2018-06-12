package ru.otus.t1;

import ru.otus.t1.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ConsoleHelper {
    protected ConsoleHelper() {
    }

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle("console");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void writeMessage(int message) {
        writeMessage(String.valueOf(message));
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = reader.readLine();
            if (line.equalsIgnoreCase("exit_ru.properties")) throw new InterruptOperationException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String[] getNominalAndAmount() throws InterruptOperationException {
        writeMessage(res.getString("console.ask.nominal"));

        Arrays.stream(Nominal.values()).forEach(x -> writeMessage(x.toString()));

        return readString().split("\\s+");
    }

    public static Operation askOperation() {
        writeMessage(res.getString("console.choose.operation.type"));
        try {
            int operationType = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(operationType);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        writeMessage(res.getString("console.incorrect.data"));
        return askOperation();
    }
}
