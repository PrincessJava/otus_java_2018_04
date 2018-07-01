package ru.otus.t1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JsonObjectWriterTest {
    JsonObjectWriter jsonObjectWriter = new JsonObjectWriter();

    @Test
    public void objectToJsonTest() {
        int[] arr = {1, 2, 3, 4, 5};
        List<String> list = Arrays.asList("LOH", "PIDDDDR!!");
        Object[] arrObj = {"123", "321"};
        Person person = new Person("Stasik", arr, list, arrObj);
        try {
            System.out.println(jsonObjectWriter.toJson(person));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
