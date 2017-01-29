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
        String val = readString();
        if (!val.matches("[a-zA-Z]{3}"))
        {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
        return val.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currenceCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String[] first = readString().split(" ");
        if (first[0].matches("[^\\d]+") || first[1].matches("[^\\d]+")) {
            writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currenceCode);
        }
        return first;
    }

    public static Operation askOperation() throws InterruptOperationException {
        System.out.println(res.getString("choose.operation"));
        int operation = Integer.parseInt(readString());
        Operation op = null;
        try {
            op = Operation.getAllowableOperationByOrdinal(operation);
        } catch (Exception e) {
            askOperation();
        }
        return op;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String a = "";
        try {
            a = in.readLine();
        } catch (IOException e) {}
        if (a.equalsIgnoreCase(res.getString("operation.EXIT")))
            throw new InterruptOperationException();

        return a;
    }
}