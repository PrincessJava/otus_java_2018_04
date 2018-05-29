package ru.otus.t1.myTry;

import java.util.Map;

public class ATM {
    private static int BALANCE = 0;
    private static Map<Nominal, Integer> denominations;
    private int credit;

    public ATM(int cash) {
        this.credit = cash;
    }

    public static void putCash(int cash) {
        BALANCE += cash;

    }

    public int getBalance() {
        return credit;
    }

    public void setBalance(int cash) {
        credit += cash;
    }

    public String getCash(int cash) {
        if (cash > BALANCE) {
            return "In ATM isn't enough cash";
        }
        if (cash > credit) {
            return "Your account isn't sufficiently funded";
        }
        credit -= cash;
        BALANCE -= cash;
        return ("Your cash: " + cash);
    }
}
