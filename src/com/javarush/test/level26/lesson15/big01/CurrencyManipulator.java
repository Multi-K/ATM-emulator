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
        Set<Integer> keyset = new TreeSet<Integer>(Collections.<Integer>reverseOrder());
        Map<Integer, Integer> amount = new LinkedHashMap<Integer, Integer>();
        keyset.addAll(denominations.keySet());
        for (Integer denomination : keyset) {
            while (expectedAmount >= denomination) {
                expectedAmount -= denomination;
                if (amount.containsKey(denomination))
                    amount.put(denomination, amount.get(denomination)+1);
                else
                    amount.put(denomination, 1);
                if (amount.get(denomination).equals(denominations.get(denomination)))
                    break;
            }
        }
        if (expectedAmount != 0)
            throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> it : amount.entrySet())
            if (denominations.get(it.getKey()).equals(it.getValue()))
                denominations.remove(it.getKey());
            else
                denominations.put(it.getKey(), denominations.get(it.getKey())-it.getValue());
        return amount;
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
