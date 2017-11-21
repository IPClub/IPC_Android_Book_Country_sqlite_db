package ipc.myapplication.database.book.model;

import ipc.myapplication.database.country.model.Country;

/**
 * Created by haykc on 11/18/2017.
 */

public class Book {

    private long id;
    private String name;
    private String r_date;
    private Country country;


    public Book() {

    }

    public Book(long id, String name, String r_date) {
        this.id = id;
        this.name = name;
        this.r_date = r_date;
    }

    public Book(String name, String r_date, Country country) {
        this.name = name;
        this.country = country;
        this.r_date = r_date;
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

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "B{" + id + "," + name + "," + r_date + "," + country.toString() + "}";
    }
}
