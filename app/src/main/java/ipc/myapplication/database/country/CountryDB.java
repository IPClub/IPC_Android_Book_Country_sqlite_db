package ipc.myapplication.database.country;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ipc.myapplication.database.LibHelper;
import ipc.myapplication.database.country.model.Country;
import ipc.myapplication.database.country.table.CountryTable;

/**
 * Created by haykc on 11/21/2017.
 */

public class CountryDB implements CountryDB_I {
    private SQLiteDatabase database;
    private LibHelper dbHelper;

    public CountryDB(Context context) {
        dbHelper = new LibHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void createCountry(Country c) {
        ContentValues values = new ContentValues();
        values.put(CountryTable.COLUMN_NAME, c.getName());
        database.insert(CountryTable.TABLE_NAME, null,values);
    }

    @Override
    public List<Country> getAllCounties() {
        List<Country> countries = new ArrayList<Country>();

        Cursor cursor = database.query(CountryTable.TABLE_NAME,
                CountryTable.allColumns(), null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Country country = cursorToCountry(cursor);
            countries.add(country);
            cursor.moveToNext();
        }
        cursor.close();
        return countries;
    }

    @Override
    public Country getCountryById(long id) {
        Cursor cursor = database.query(CountryTable.TABLE_NAME,
                CountryTable.allColumns(), "id=?", new String[] { ""+id }, null, null, null);

        cursor.moveToFirst();
        return cursorToCountry(cursor);
    }

    private Country cursorToCountry(Cursor cursor) {
        Country country = new Country();
        country.setId(cursor.getLong(0));
        country.setName(cursor.getString(1));
        return country;
    }
}
