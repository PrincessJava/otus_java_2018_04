package ru.otus.t1.proxy;

import java.lang.reflect.InvocationTargetException;

public interface FrameworkExecutor {
    void run() throws IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException;
}
