package ru.otus.t1;

import ru.otus.t1.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private Map<Nominal, Integer> denominations = new TreeMap<>(Collections.reverseOrder());

    public void addAmount(Nominal denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Nominal, Integer> e : denominations.entrySet()) {
            total += e.getKey().nominalValue() * e.getValue();
        }
        return total;
    }

    public boolean hasMoney() {
        return denominations.size() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return (expectedAmount <= getTotalAmount());
    }

    public Map<Nominal, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Nominal, Integer> answ = new TreeMap<>(Collections.reverseOrder());
        try {
            int residue = withdraw(denominations, answ, expectedAmount);
            for (Map.Entry<Nominal, Integer> e : answ.entrySet()) {
                int count = denominations.get(e.getKey()) - e.getValue();
                if (count == 0) {
                    denominations.remove(e.getKey());
                } else {
                    denominations.put(e.getKey(), count);
                }
            }
            if (residue != 0) {
                throw new NotEnoughMoneyException();
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
        return answ;
    }

    //Снимает sum максимально крупными купюрами, результат помещается в result
    //Если сумму удалось снять - возвращает ноль
    private int withdraw(Map<Nominal, Integer> map, Map<Nominal, Integer> result, int sum) {
        Map<Nominal, Integer> copyMap = new TreeMap<>(Collections.reverseOrder());
        copyMap.putAll(map);
        for (Map.Entry<Nominal, Integer> e : copyMap.entrySet()) {
            if (e.getKey().nominalValue() <= sum && e.getValue() > 0) {
                sum -= e.getKey().nominalValue();
                copyMap.put(e.getKey(), e.getValue() - 1);

                if (result.containsKey(e.getKey())) {
                    result.put(e.getKey(), result.get(e.getKey()) + 1);
                } else {
                    result.put(e.getKey(), 1);
                }

                if (sum <= 0) return sum;
                return withdraw(copyMap, result, sum);
            }
        }
        return sum;
    }
}