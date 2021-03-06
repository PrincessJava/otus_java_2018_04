package ru.otus.t3;

public class StringTutor {
    public static void log(String s) {
        System.out.println(s);
    }


    /**
     * Убедитесь, что приветствие greeting имеет вид
     * Привет, Иван Иванов!
     * или
     * Привет,Петр Первый!
     * т.е. начинается на Привет, заканчивается на !
     * и содержит 2 слова для имени и фамилии,
     * причем имя и фамилия не короче 3 букв
     * и начинаются с большой буквы
     */
    public boolean checkGreeting(String greeting) {

        return greeting.matches("^(Привет,\\s*([А-ЯЁ][а-яё]{2,}\\s*){2}\\s*!)$");
    }

}
