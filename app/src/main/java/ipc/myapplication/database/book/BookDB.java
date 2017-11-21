package ipc.myapplication.database.book;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ipc.myapplication.database.LibDB;
import ipc.myapplication.database.LibHelper;
import ipc.myapplication.database.book.model.Book;
import ipc.myapplication.database.book.table.BookTable;

public class BookDB implements BookDB_I{
    private SQLiteDatabase database;
    private LibHelper dbHelper;
    Context context;

    public BookDB(Context context) {
        this.context = context;
        dbHelper = new LibHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void createBook(Book b) {
        ContentValues values = new ContentValues();
        values.put(BookTable.COLUMN_NAME, b.getName());
        values.put(BookTable.COLUMN_DATE, b.getR_date());
        values.put(BookTable.COLUMN_COUNTRY_ID, b.getCountry().getId());
        database.insert(BookTable.TABLE_NAME, null,values);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();

        Cursor cursor = database.query(BookTable.TABLE_NAME,
                BookTable.allColumns(), null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }

    private Book cursorToBook(Cursor cursor) {
        Book book = new Book();
        book.setId(cursor.getLong(0));
        book.setName(cursor.getString(1));
        book.setR_date(cursor.getString(2));
        book.setCountry(LibDB.getInstance(context).getCountryById(cursor.getLong(3)));
        return book;
    }

    @Override
    public List<Book> searchBooks(String search) {
        String query  = "select * from books where b_name like ?;";
        List<Book> books = new ArrayList<Book>();

        Cursor cursor = database.rawQuery(query,new String[]{"%"+search+"%"});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }

    @Override
    public List<Book> searchBooksByCountryId(long c_id) {

        List<Book> books = new ArrayList<Book>();

        Cursor cursor = database.query(BookTable.TABLE_NAME,
                BookTable.allColumns(), "c_id=?", new String[]{""+c_id}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }
}