package ru.otus.t1.model;

public class UserDataSet extends DataSet {

    private String name;

    private Integer age;

    public UserDataSet() {
        //empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
