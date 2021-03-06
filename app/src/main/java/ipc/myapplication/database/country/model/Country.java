package ipc.myapplication.database.country.model;

/**
 * Created by haykc on 11/21/2017.
 */

public class Country {
    private long id;
    private String name;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {

    }
    public Country(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "C{" + id + "," + name +"}";
    }
}
