package ipc.myapplication.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ipc.myapplication.R;
import ipc.myapplication.database.LibDB;
import ipc.myapplication.database.country.model.Country;

public class AddCountryActivity extends AppCompatActivity {

    EditText c_name;
    ListView c_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
        c_name = (EditText) findViewById(R.id.c_name);
        c_lv = (ListView) findViewById(R.id.c_lv);
    }

    public void all(View view) {
        List<Country> countries = LibDB.getInstance(this).getAllCounties();
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(this,android.R.layout.simple_list_item_1,countries);
        c_lv.setAdapter(adapter);
    }

    public void save(View view) {
        if(c_name.getText().toString().equals("")){
            c_name.setError("Required");
        }else{
            LibDB.getInstance(this).createCountry(new Country((c_name.getText().toString())));
            c_name.setText("");
        }
    }
}
