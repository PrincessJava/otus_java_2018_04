package ru.otus.t1.model;

import javax.persistence.Transient;

public abstract class DataSet {

    @Transient
    private long id;

    public DataSet() {
        //empty
    }

    public DataSet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
