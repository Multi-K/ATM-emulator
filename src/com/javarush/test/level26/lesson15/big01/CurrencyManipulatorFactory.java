package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by 123 on 27.01.2017.
 */
public class CurrencyManipulatorFactory
{
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return manipulatorList;
    }

    private static List<CurrencyManipulator> manipulatorList = new ArrayList<CurrencyManipulator>();
    private CurrencyManipulatorFactory() {}
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
        if (manipulatorList.contains(manipulator))
            return manipulatorList.get(manipulatorList.indexOf(manipulator));
        else
        {
            manipulatorList.add(manipulator);
            return manipulator;
        }
    }
}
