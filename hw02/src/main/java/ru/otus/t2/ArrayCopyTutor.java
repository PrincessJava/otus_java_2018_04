package ru.otus.t2;

/**
 * Данный класс делает то же, что делает стандартный ArrayList:
 * увеличивает размер массива, когда массив заполнен.
 * <p>
 * Реализуйте метод deleteAnimal(int position)
 */
public class ArrayCopyTutor {
    private int animals_capacity = 5;
    private int animals_expand_by = 5;
    private int animals_size = 0;
    private String[] animals = new String[animals_capacity];

    public static void log(String s) {
        System.out.println(s);
    }

    public void addAnimal(String animal) {
        if (animals_size >= animals_capacity) {
            expandAnimalsArray();
        }
        animals[animals_size] = animal;
        animals_size++;
    }

    private void expandAnimalsArray() {
        int old_animals_size = animals_capacity;
        animals_capacity += animals_expand_by;
        String[] animals_new = new String[animals_capacity];
        System.arraycopy(animals, 0, animals_new, 0, old_animals_size);
        animals = animals_new;
    }

    public void insertAnimal(int position, String animal) {
        if (position < 0 || position > animals_size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (position == animals_size - 1) {
            addAnimal(animal);
        } else {
            if (animals_size >= animals_capacity) {
                expandAnimalsArray();
            }
            System.arraycopy(animals, position, animals, position + 1, animals_size - position);
            animals[position] = animal;
            animals_size++;
        }
    }

    public void deleteAnimal(int position) {
        for (int i = position; i < animals_size - 1; i++) {
            animals[i] = animals[i + 1];
        }
        animals_size--;
    }

    public void showAnimals() {
        for (int i = 0; i < animals_size; i++) {
            log(i + ") " + animals[i]);
        }
    }

}