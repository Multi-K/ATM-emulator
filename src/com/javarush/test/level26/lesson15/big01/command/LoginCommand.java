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
        String cardNumber, pin;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            cardNumber = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();
            if (cardNumber.matches("\\d{12}") && pin.matches("\\d{4}")) {
                if (validCreditCards.containsKey(cardNumber) && pin.equals(validCreditCards.getString(cardNumber))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                    break;
                }
            }
            ConsoleHelper.writeMessage(res.getString(String.format(res.getString("success.verified.format"), cardNumber)));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
