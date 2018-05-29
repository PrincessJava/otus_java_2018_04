package ru.otus.t1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> mapCurrencyManipulators = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!mapCurrencyManipulators.containsKey(currencyCode))
            mapCurrencyManipulators.put(currencyCode, new CurrencyManipulator(currencyCode));

        return mapCurrencyManipulators.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return mapCurrencyManipulators.values();
    }
}
