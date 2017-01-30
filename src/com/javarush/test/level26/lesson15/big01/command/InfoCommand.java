package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by 123 on 28.01.2017.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    public void execute() {
        Collection<CurrencyManipulator> allManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (allManipulators.isEmpty()) {
            ConsoleHelper.writeMessage("Card is empty");
            return;
        }
        for (CurrencyManipulator cm : allManipulators) {
            if (!cm.hasMoney())
                ConsoleHelper.writeMessage(res.getString(res.getString("no.money")));
            else
            ConsoleHelper.writeMessage(res.getString("before") + " " +cm.getCurrencyCode() + " - " + cm.getTotalAmount());
        }
    }
}
