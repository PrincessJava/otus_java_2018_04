package ru.otus.t1;

import java.util.List;

public class Person {
    private String name;
    private int[] intArr;
    private List<String> list;
    private Object[] arr;

    public Person(String name, int[] intArr, List<String> list, Object[] arr) {
        this.name = name;
        this.intArr = intArr;
        this.list = list;
        this.arr = arr;
    }

    public int[] getIntArr() {
        return intArr;
    }

    public void setIntArr(int[] intArr) {
        this.intArr = intArr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Object[] getArr() {
        return arr;
    }

    public void setArr(Object[] arr) {
        this.arr = arr;
    }

}
