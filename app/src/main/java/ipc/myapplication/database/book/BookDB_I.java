package ipc.myapplication.database.book;

import java.util.List;

import ipc.myapplication.database.book.model.Book;

/**
 * Created by haykc on 11/21/2017.
 */

public interface BookDB_I {
    void createBook(Book b);
    List<Book> getAllBooks();
    List<Book> searchBooks(String search);
    List<Book> searchBooksByCountryId(long c_id);
}
