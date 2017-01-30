package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.*;

/**
 * Created by 123 on 28.01.2017.
 */
public class CommandExecutor {
    private CommandExecutor() {}
    private static Map<Operation, Command> commandMap = new HashMap<Operation, Command>();
    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }
    public static final void execute(Operation operation) throws InterruptOperationException {
        commandMap.get(operation).execute();
    }
}
