package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by 123 on 27.01.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.get(denomination) == null)
            denominations.put(denomination, count);
        else
            denominations.put(denomination, denominations.get(denomination) + count);
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> it : denominations.entrySet())
            result += (it.getKey() * it.getValue());
        return result;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        if (expectedAmount <= getTotalAmount())
            return true;
        else
            return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Set<Integer> set = new TreeSet<Integer>(Collections.<Integer>reverseOrder());
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        set.addAll(denominations.keySet());
        for (Integer s : set) {
            while (expectedAmount >= s) {
                expectedAmount -= s;
                if (map.containsKey(s))
                    map.put(s, map.get(s)+1);
                else
                    map.put(s, 1);
                if (map.get(s).equals(denominations.get(s)))
                    break;
            }
        }
        if (expectedAmount != 0)
            throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> it : map.entrySet())
            if (denominations.get(it.getKey()).equals(it.getValue()))
                denominations.remove(it.getKey());
            else
                denominations.put(it.getKey(), denominations.get(it.getKey())-it.getValue());
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyManipulator that = (CurrencyManipulator) o;

        return currencyCode.equals(that.currencyCode);
    }

    @Override
    public int hashCode() {
        return currencyCode.hashCode();
    }
}
