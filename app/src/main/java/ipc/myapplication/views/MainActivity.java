package ipc.myapplication.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import ipc.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        Intent i = new Intent(this,AddBookActivity.class);
        startActivity(i);
    }


    public void find(View view) {
        Intent i = new Intent(this,FindBookActivity.class);
        startActivity(i);
    }

    public void addCountry(View view) {
        Intent i = new Intent(this,AddCountryActivity.class);
        startActivity(i);
    }
}
