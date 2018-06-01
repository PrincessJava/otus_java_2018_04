package ru.otus.t1;

import org.junit.Before;
import org.junit.Test;
import ru.otus.t1.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CurrencyManipulatorTest {
    private CurrencyManipulator currencyManipulator;
    private Map<Nominal, Integer> expected;

    @Before
    public void initialize() {
        currencyManipulator = CurrencyManipulatorFactoryFake.getCurrencyManipulator();
        addAmount();

        expected = new TreeMap<>();
        expected.put(Nominal.FIFTY, 1);
        expected.put(Nominal.TEN, 1);
    }

    @Test
    public void getTotalAmountTest() {
        assertEquals(70, currencyManipulator.getTotalAmount());
    }

    @Test
    public void isAmountAvailableTest() {
        assertTrue(currencyManipulator.isAmountAvailable(70));
    }

    @Test
    public void hasMoneyTest() {
        assertTrue(currencyManipulator.hasMoney());
    }

    @Test
    public void withdrawAmountTest() throws NotEnoughMoneyException {
        Map<Nominal, Integer> actual = currencyManipulator.withdrawAmount(60);

        assertEquals(expected, actual);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawAmountNotEnoughMoneyTest() throws NotEnoughMoneyException {
        currencyManipulator.withdrawAmount(80);
    }

    private void addAmount() {
        currencyManipulator.addAmount(Nominal.TEN, 2);
        currencyManipulator.addAmount(Nominal.FIFTY, 1);
    }

}
