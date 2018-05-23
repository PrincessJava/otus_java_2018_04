package ru.otus.t3;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.otus.t3.StringTutor.log;

public class TestStringTutor {

    StringTutor stringTutor = new StringTutor();

    /**
     * Замените все null в assertEquals на true или false
     */
    @Test
    public void testStringEquals() {
        String s1 = "aaa";
        String s2 = "aaa";
        s1.equals(s2);
        String s3 = new String("aaa");
        log("адрес объекта s1: " + System.identityHashCode(s1));
        log("адрес объекта s2: " + System.identityHashCode(s2));
        assertSame(s1, s2);
        assertEquals(s1, s2);
        log("адрес объекта s3: " + System.identityHashCode(s3));
        assertNotSame(s1, s3);
        // метод intern() позволяет получить строку из пула строк
        String s4 = s3.intern();
        log("адрес объекта s4: " + System.identityHashCode(s4));
        assertSame(s1, s4);
        // тестируем пересоздание объекта каждый раз при изменении
        s3 = s3 + "bbb";
        log("адрес измененного объекта s3: " + System.identityHashCode(s3));
        s3 = s3.substring(0, 3); // s3 снова "aaa"
        assertNotSame(s3, s1);
        assertEquals(s3, s1);
        assertSame(s3.intern(), s1);
    }

    @Test
    public void testCheckGreeting() {
        assertTrue(stringTutor.checkGreeting("Привет, Иван Иванов!"));
        assertTrue(stringTutor.checkGreeting("Привет,Петр Первый!"));
        assertTrue(stringTutor.checkGreeting("Привет, Петр Первый!"));
        assertTrue(stringTutor.checkGreeting("Привет, Петр Первый !"));

        assertFalse("В начале должно быть слово Привет и запятая",
                stringTutor.checkGreeting("Здравствуйте, Петр Первый!"));
        assertFalse("В конце должен быть восклицательный знак",
                stringTutor.checkGreeting("Привет, Петр Первый"));
        assertFalse("Имя слишком короткое",
                stringTutor.checkGreeting("Привет, Ли Сунь!"));
        assertFalse("Фамилия слишком короткая",
                stringTutor.checkGreeting("Привет, Куй Ли!"));
        assertFalse("Должны быть указаны и имя, и фамилия",
                stringTutor.checkGreeting("Привет, Петр!"));
        assertFalse("Первая буква имени должна быть заглавной",
                stringTutor.checkGreeting("Привет, петр Первый!"));
        assertFalse("Первая буква фамилии должна быть заглавной",
                stringTutor.checkGreeting("Привет, Петр первый!"));
    }
}
