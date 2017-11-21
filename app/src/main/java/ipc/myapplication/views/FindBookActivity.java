package ipc.myapplication.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import ipc.myapplication.R;
import ipc.myapplication.database.LibDB;
import ipc.myapplication.database.book.BookDB;
import ipc.myapplication.database.book.model.Book;
import ipc.myapplication.database.country.model.Country;

public class FindBookActivity extends AppCompatActivity {
    EditText search_et;
    ListView books_lv;
    RadioGroup by;
    private List<Country> countries;
    int selectedCountry = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_book);
        search_et = (EditText) findViewById(R.id.search_et);
        books_lv = (ListView) findViewById(R.id.books_lv);
        by = (RadioGroup) findViewById(R.id.by);
        countries = LibDB.getInstance(this).getAllCounties();
        by.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(group.getWindowToken(), 0);
                }
                search_et.setText("");
                selectedCountry = -1;
                search_et.setError(null);
                if(checkedId == R.id.by_name){
                    search_et.setFocusable(true);
                    search_et.setFocusableInTouchMode(true);
                }else{
                    search_et.setFocusable(false);
                }
            }
        });
        search_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_et.hasFocusable()){

                }else{
                    if (countries.size() == 0) {
                        Toast.makeText(FindBookActivity.this, "Please add at least one country", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String[] names = new String[countries.size()];
                    for (int i = 0; i < countries.size(); i++) {
                        names[i] = countries.get(i).getName();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(FindBookActivity.this);
                    builder.setTitle("Choose Country")
                            .setItems(names, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    search_et.setText(countries.get(which).getName());
                                    selectedCountry = which;
                                }
                            });
                    builder.create().show();
                }
                Toast.makeText(FindBookActivity.this, "asasd", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchByCountry(){
        if(search_et.getText().toString().equals("")){
            search_et.setError("Choose country");
        }else{
            List<Book> books;
            books = LibDB.getInstance(this).searchBooksByCountryId(countries.get(selectedCountry).getId());
            ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                    android.R.layout.simple_list_item_1, books);
            books_lv.setAdapter(adapter);
        }

    }

    public void searchByName(){
        String search = search_et.getText().toString();
        List<Book> books;

        if (search.equals("")) {
            books = LibDB.getInstance(this).getAllBooks();
        } else {
            books = LibDB.getInstance(this).searchBooks(search);
        }


        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_list_item_1, books);
        books_lv.setAdapter(adapter);
    }
    public void searchBook(View view) {
        if(search_et.hasFocusable()){
            searchByName();
        }else{
            searchByCountry();
        }

    }
}
