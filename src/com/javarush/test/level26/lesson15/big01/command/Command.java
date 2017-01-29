package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by 123 on 28.01.2017.
 */
interface Command {
    public void execute() throws InterruptOperationException;
}
