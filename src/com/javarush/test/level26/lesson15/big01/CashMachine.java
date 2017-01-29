package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by 123 on 27.01.2017.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation oper;
        try {
            CommandExecutor.execute(Operation.LOGIN);
            do
            {
                oper = ConsoleHelper.askOperation();
                CommandExecutor.execute(oper);
            }
            while (oper != Operation.EXIT);
        } catch (InterruptOperationException e) {ConsoleHelper.printExitMessage();}
    }
}
