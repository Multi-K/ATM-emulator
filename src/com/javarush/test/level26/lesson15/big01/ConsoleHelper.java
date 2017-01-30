package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by 123 on 27.01.2017.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"common_en");
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        if (!code.matches("[a-zA-Z]{3}"))
        {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] twoDigits = readString().split(" ");
        try
        {
            if (twoDigits[0].matches("[^\\d]+") || twoDigits[1].matches("[^\\d]+"))
            {
                writeMessage(res.getString("invalid.data"));
                return getValidTwoDigits(currencyCode);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
        return twoDigits;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO") + " : 1");
        writeMessage(res.getString("operation.DEPOSIT") + " : 2");
        writeMessage(res.getString("operation.WITHDRAW") + " : 3");
        writeMessage(res.getString("operation.EXIT") + " : 4");
        String operation = readString();
        if (!operation.matches("[\\d]")) {
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
        int numberOfOperation = Integer.parseInt(operation);
        Operation oper = null;
        try {
            oper = Operation.getAllowableOperationByOrdinal(numberOfOperation);
        } catch (Exception e) {
            askOperation();
        }
        return oper;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String string = "";
        try {
            string = in.readLine();
        } catch (IOException e) {}
        if (string.equalsIgnoreCase(res.getString("operation.EXIT")))
            throw new InterruptOperationException();

        return string;
    }
}