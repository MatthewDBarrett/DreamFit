package com.example.ethan.dream_fit;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class calorie_tracker_consumption_History extends AppCompatActivity{

    DatabaseHelper historyDB;
    DatabaseHelper_Main main_DB;
    ArrayList<Item> itemList;
    ListView mainListView;
    Item thisItem;
    LayoutInflater layoutinflater;
    EditText description, calorie;
    Button saveBtn;
    final Context context = this;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_add_list);

        mainListView = (ListView) findViewById(R.id.foodList);
        historyDB = new DatabaseHelper(this);
        main_DB = new DatabaseHelper_Main(this);


        //Typecast variables
        description = (EditText) findViewById(R.id.description_d);
        calorie = (EditText) findViewById(R.id.calorie_d);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        // add heading to the list view

        //1. create an array list and
        //2. populate it through database
        //3. adapt that through list adapter

        //ArrayList<String> thisList = new ArrayList<>();
        //Instead of the above code; we will use an arrayList of items
        itemList = new ArrayList<>();

        //call the ->Cursor which will get all the contents of the database
        Cursor data = historyDB.getListContents();

        //populate the array list with the data now
        if (data.getCount() == 0)
            Toast.makeText(calorie_tracker_consumption_History.this, "database is currently empty", Toast.LENGTH_LONG).show();
        else{
            while(data.moveToNext()){
                //continue looping through

                //We will store the user's food item.
                thisItem = new Item(data.getString(1),data.getString(2));

                //-> '1' here means the 'column no' for 'description 'in the database
                //-> '2' here means the 'column no' for 'calorie 'in the database

                //Now add items to the array_List called 'itemList'
                itemList.add(thisItem);

                two_Column_ListAdapter adapter = new two_Column_ListAdapter(this,R.layout.adapter_view_layout, itemList);
                mainListView.setAdapter(adapter);
            }
        }
    }



}
