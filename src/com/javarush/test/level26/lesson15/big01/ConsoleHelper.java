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
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String[] twoDigits = readString().split(" ");
        if (twoDigits[0].matches("[^\\d]+") || twoDigits[1].matches("[^\\d]+")) {
            writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
        return twoDigits;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        int operation = Integer.parseInt(readString());
        Operation oper = null;
        try {
            oper = Operation.getAllowableOperationByOrdinal(operation);
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