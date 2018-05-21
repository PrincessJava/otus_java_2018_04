package ru.otus.t1;

import java.lang.instrument.Instrumentation;

//http://qaru.site/questions/15149/calculate-size-of-object-in-java
public class ObjectSizeCounter {
    private Instrumentation instrumentation;

    public static void main(String[] args) {
        ObjectSizeCounter objectSizeCounter = new ObjectSizeCounter();

        System.out.println(objectSizeCounter.getObjectSize(new Object()));
    }

    public void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
