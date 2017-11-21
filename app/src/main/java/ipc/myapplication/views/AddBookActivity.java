package ipc.myapplication.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ipc.myapplication.R;
import ipc.myapplication.database.LibDB;
import ipc.myapplication.database.book.BookDB;
import ipc.myapplication.database.book.model.Book;
import ipc.myapplication.database.country.model.Country;

public class AddBookActivity extends AppCompatActivity {
    EditText name_et;
    EditText date_et;
    EditText country_et;

    private int year;
    private int month;
    private int day;
    private List<Country> countries;
    int selectedCountry = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        name_et = (EditText) findViewById(R.id.b_name);
        date_et = (EditText) findViewById(R.id.r_date);
        country_et = (EditText) findViewById(R.id.country);
        countries = LibDB.getInstance(this).getAllCounties();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            String mm = (month + 1) < 10 ? "0" + (month + 1) : "" + (month + 1);
            date_et.setText(new StringBuilder().append(year));

        }
    };

    public void save(View view) {
        if (validate()) {
            String name = name_et.getText().toString();
            String date = date_et.getText().toString();
            Book b = new Book(name, date, countries.get(selectedCountry));

            LibDB.getInstance(this).createBook(b);
            finish();
        }


    }

    private boolean validate() {
        boolean isCorrect = true;
        if (name_et.getText().toString().equals("")) {
            isCorrect = false;
            name_et.setError("Required");
        }

        if (date_et.getText().toString().equals("")) {
            isCorrect = false;
            date_et.setError("Required");
        }

        if (country_et.getText().toString().equals("")) {
            isCorrect = false;
            country_et.setError("Required");
        }
        return isCorrect;
    }

    public void showCalendar(View view) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, datePickerListener,
                year, month, day).show();
    }

    public void showCountries(View view) {

        if (countries.size() == 0) {
            Toast.makeText(this, "Please add at least one country", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] names = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
            names[i] = countries.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Country")
                .setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCountry = which;
                        country_et.setText(countries.get(which).getName());
                    }
                });
        builder.create().show();
    }
}
