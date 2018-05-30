package ru.otus.t1;

import ru.otus.t1.myTry.Nominal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory {
    private static Map<Nominal, CurrencyManipulator> mapCurrencyManipulators = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(Nominal currencyCode) {
        if (!mapCurrencyManipulators.containsKey(currencyCode))
            mapCurrencyManipulators.put(currencyCode, new CurrencyManipulator(currencyCode));

        return mapCurrencyManipulators.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return mapCurrencyManipulators.values();
    }
}
