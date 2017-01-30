package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by 123 on 28.01.2017.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        if (!cm.hasMoney()) {
            ConsoleHelper.writeMessage("No money available");
            return;
        }
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        String money;
        do {
            money = ConsoleHelper.readString();
            if (money.matches("[1-9]\\d*")) {
                if (cm.isAmountAvailable(Integer.parseInt(money))) {
                    try {
                        for (Map.Entry<Integer, Integer> it : cm.withdrawAmount(Integer.parseInt(money)).entrySet())
                        ConsoleHelper.writeMessage("\t" + it.getKey() + " - " + it.getValue());
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(money), code));
                        break;
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                        ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    }
                }

                else
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } else
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        } while (true);
    }
}
