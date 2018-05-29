package ru.otus.t1;

import ru.otus.t1.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder());

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> e : denominations.entrySet()) {
            total += e.getKey() * e.getValue();
        }
        return total;
    }

    public boolean hasMoney() {
        return denominations.size() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return (expectedAmount <= getTotalAmount());
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> answ = new TreeMap<>(Collections.reverseOrder());
        try {
            int ostatok = withdraw(denominations, answ, expectedAmount);
            for (Map.Entry<Integer, Integer> e : answ.entrySet()) {
                int count = denominations.get(e.getKey()) - e.getValue();
                if (count == 0) {
                    denominations.remove(e.getKey());
                } else {
                    denominations.put(e.getKey(), count);
                }
            }
            if (ostatok != 0) {
                throw new NotEnoughMoneyException();
            }
        } catch (ConcurrentModificationException e) {
        }
        return answ;
    }

    //Снимает sum максимально крупными купюрами, результат помещается в result
    //Если сумму удалось снять - возвращает ноль
    private int withdraw(Map<Integer, Integer> map, Map<Integer, Integer> result, int sum) {
        Map<Integer, Integer> copymap = new TreeMap<>(Collections.reverseOrder());
        copymap.putAll(map);
        for (Map.Entry<Integer, Integer> e : copymap.entrySet()) {
            if (e.getKey() <= sum && e.getValue() > 0) {
                sum -= e.getKey();
                copymap.put(e.getKey(), e.getValue() - 1);

                if (result.containsKey(e.getKey())) {
                    result.put(e.getKey(), result.get(e.getKey()) + 1);
                } else {
                    result.put(e.getKey(), 1);
                }

                if (sum <= 0) return sum;
                return withdraw(copymap, result, sum);
            }
        }
        return sum;
    }
}