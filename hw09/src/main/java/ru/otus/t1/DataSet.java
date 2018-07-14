package main.java.ru.otus.t1;

public abstract class DataSet {

    private long id;

    public DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
