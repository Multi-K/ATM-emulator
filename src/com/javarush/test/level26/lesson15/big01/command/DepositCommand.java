package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by 123 on 28.01.2017.
 */
class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String newVal = ConsoleHelper.askCurrencyCode();
        String[] denon = ConsoleHelper.getValidTwoDigits(newVal);
        CurrencyManipulator manip = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(newVal);
        manip.addAmount(new Integer(denon[0]), new Integer(denon[1]));
        ConsoleHelper.writeMessage(res.getString("success.format"));
    }
}
