package ru.otus.t1.myTry;

public class ATMEmulator {
    private static void setBalance() {
        ATM.putCash(100000);
    }

    public static void main(String[] args) {
        setBalance();

        ATM ATMUser1 = new ATM(11032);
        System.out.println("Balance: " + ATMUser1.getBalance());
        System.out.println("Cash: " + ATMUser1.getCash(10000));
        System.out.println("Balance: " + ATMUser1.getBalance());

        ATM ATMUser2 = new ATM(6500);
        System.out.println("Balance: " + ATMUser2.getBalance());
        System.out.println("Cash: " + ATMUser2.getCash(7000));
        System.out.println("Balance: " + ATMUser2.getBalance());
    }
}
