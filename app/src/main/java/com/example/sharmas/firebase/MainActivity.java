package com.example.sharmas.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
    DatabaseHelper databaseHelper;
    ArrayList arrayList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.line1);
        databaseHelper=new DatabaseHelper(MainActivity.this);

        try {
            arrayList=databaseHelper.show();
            if(arrayList!=null){
                ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
                listView.setAdapter(arrayAdapter);
            }
            else{
                Toast.makeText(getApplicationContext(), "No Data Found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}
