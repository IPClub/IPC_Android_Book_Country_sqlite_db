package ipc.myapplication.database;

import android.content.Context;

import java.util.List;

import ipc.myapplication.database.book.BookDB;
import ipc.myapplication.database.book.BookDB_I;
import ipc.myapplication.database.book.model.Book;
import ipc.myapplication.database.country.CountryDB;
import ipc.myapplication.database.country.CountryDB_I;
import ipc.myapplication.database.country.model.Country;

/**
 * Created by haykc on 11/21/2017.
 */

public class LibDB implements BookDB_I,CountryDB_I{

    private BookDB bookDB;
    private CountryDB countryDB;
    private static LibDB db;


    private LibDB(Context context){
        bookDB = new BookDB(context);
        countryDB = new CountryDB(context);
    }

    public static LibDB getInstance(Context context){
        if(db == null){
            db = new LibDB(context);
        }
        return db;
    }

    @Override
    public void createBook(Book b) {
        bookDB.open();
        bookDB.createBook(b);
        bookDB.close();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books;
        bookDB.open();
        books = bookDB.getAllBooks();
        bookDB.close();
        return books;
    }

    @Override
    public List<Book> searchBooks(String search) {
        List<Book> books;
        bookDB.open();
        books = bookDB.searchBooks(search);
        bookDB.close();
        return books;
    }

    @Override
    public List<Book> searchBooksByCountryId(long c_id) {
        List<Book> books;
        bookDB.open();
        books = bookDB.searchBooksByCountryId(c_id);
        bookDB.close();
        return books;
    }

    @Override
    public void createCountry(Country c) {
        countryDB.open();
        countryDB.createCountry(c);
        countryDB.close();
    }

    @Override
    public List<Country> getAllCounties() {
        List<Country> countries;
        countryDB.open();
        countries = countryDB.getAllCounties();
        countryDB.close();
        return countries;
    }

    @Override
    public Country getCountryById(long id) {
        Country country;
        countryDB.open();
        country = countryDB.getCountryById(id);
        countryDB.close();
        return country;
    }
}
