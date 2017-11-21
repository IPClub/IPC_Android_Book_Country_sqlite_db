package ipc.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ipc.myapplication.database.book.table.BookTable;
import ipc.myapplication.database.country.table.CountryTable;

/**
 * Created by haykc on 11/21/2017.
 */

public class LibHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 3;

    public LibHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookTable.CREATE_TABLE_BOOK);
        db.execSQL(CountryTable.CREATE_TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BookTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CountryTable.TABLE_NAME);
        onCreate(db);
    }
}

