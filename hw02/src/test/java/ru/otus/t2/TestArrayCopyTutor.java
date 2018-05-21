package ru.otus.t2;

import org.junit.Test;

public class TestArrayCopyTutor {

    ArrayCopyTutor arrayCopyTutor = new ArrayCopyTutor();

    @Test
    public void testAnimals() {
        arrayCopyTutor.addAnimal("Лошадь");
        arrayCopyTutor.addAnimal("Носорог");
        arrayCopyTutor.addAnimal("Собака");
        arrayCopyTutor.addAnimal("Змея");
        arrayCopyTutor.addAnimal("Обезьяна");
        arrayCopyTutor.addAnimal("Индюк");
        arrayCopyTutor.addAnimal("Косуля");
        arrayCopyTutor.addAnimal("Лев");
        arrayCopyTutor.addAnimal("Тигр");
        arrayCopyTutor.addAnimal("Кошка");
        arrayCopyTutor.addAnimal("Черепаха");
        arrayCopyTutor.insertAnimal(1, "Человек");
        arrayCopyTutor.deleteAnimal(2);
        arrayCopyTutor.showAnimals();
    }

}
