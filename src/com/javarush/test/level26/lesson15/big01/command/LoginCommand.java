package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by 123 on 29.01.2017.
 */
public class LoginCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
   private ResourceBundle validCreditCards = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.verifiedCards");
    public void execute() throws InterruptOperationException {
        String a, b;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            a = ConsoleHelper.readString();
            b = ConsoleHelper.readString();
            if (a.matches("\\d{12}") && b.matches("\\d{4}")) {
                if (validCreditCards.containsKey(a) && b.equals(validCreditCards.getString(a))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), a));
                    break;
                }
            }
            ConsoleHelper.writeMessage(res.getString(String.format(res.getString("success.verified.format"), a)));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
