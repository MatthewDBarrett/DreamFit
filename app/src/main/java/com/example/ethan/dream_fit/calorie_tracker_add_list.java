package com.example.ethan.dream_fit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class calorie_tracker_add_list extends AppCompatActivity {

    DatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_add_list);

        ListView mainListView = (ListView) findViewById(R.id.foodList);
        myDB = new DatabaseHelper(this);

        //1. create an array list and
        //2. populate it through database
        //3. adapt that through list adapter

        ArrayList<String> thisList = new ArrayList<>();

        //call the ->Cursor which will get all the contents of the database
        Cursor data = myDB.getListContents();

        //populate the array list with the data now
        if (data.getCount() == 0)
            Toast.makeText(calorie_tracker_add_list.this, "database is currently empty", Toast.LENGTH_LONG).show();
        else{
            while(data.moveToNext()){
                //continue looping through

                thisList.add(data.getString(1));
                // '1' here means the 'column no' in the database

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, thisList);
                mainListView.setAdapter(listAdapter);

                mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
            }
        }
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, calorie_tracker_add_item.class);
        startActivity(intent);
    }




}

