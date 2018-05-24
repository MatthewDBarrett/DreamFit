package com.example.ethan.dream_fit;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class calorie_tracker_add_list extends AppCompatActivity {

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
            Toast.makeText(calorie_tracker_add_list.this, "database is currently empty", Toast.LENGTH_LONG).show();
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



    public void onAdd(View view){


        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.alertpropmt_add_item, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //Create and Intialize edit text
        final EditText description = (EditText) promptsView.findViewById(R.id.description_d);
        final EditText calorie = (EditText) promptsView.findViewById(R.id.calorie_d);

        //create and Intialize button
        Button saveButton = (Button) promptsView.findViewById(R.id.saveBtn);

        // set dialog message


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // btnAdd1 has been clicked
                String thisDescription = description.getText().toString();
                String thisCalories = calorie.getText().toString();
                String thisNumberPattern = "[0-9]+";


                //call add data function in database helper
                if (description.length() != 0 && thisCalories.length() != 0 && thisCalories.matches(thisNumberPattern) && (!(thisDescription.matches(thisNumberPattern))) ) {

                    //This will only works if user enters everything in the edit text fields
                    addData(thisDescription,thisCalories);

                    //adding new data to the historyListView
                    itemList.add(new Item(thisDescription,thisCalories));

                    //clear out the text fields
                    description.setText("");
                    calorie.setText("");


                }else if (description.length() == 0 && calorie.length() == 0) {
                    Toast.makeText(calorie_tracker_add_list.this, "Please! add food item to track ", Toast.LENGTH_LONG).show();
                }else if (description.length() == 0 && calorie.length() != 0) {
                    Toast.makeText(calorie_tracker_add_list.this, "You! forgot to add description", Toast.LENGTH_LONG).show();
                }else if (description.length() != 0 && calorie.length() == 0) {
                    Toast.makeText(calorie_tracker_add_list.this, "You! forgot to add calories", Toast.LENGTH_LONG).show();
                }else if (!(thisCalories.matches(thisNumberPattern))) {
                    Toast.makeText(calorie_tracker_add_list.this, "Please! enter valid amount of calories", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(calorie_tracker_add_list.this, "Please! enter some description of the food item", Toast.LENGTH_LONG).show();

            }

        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void addData(String newDescription, String newCalorie){
        // this will add data to the database

        boolean insertData = main_DB.addData(newDescription, newCalorie);
        boolean insertData1 = historyDB.addData(newDescription, newCalorie);


        if (insertData == true && insertData1 == true) {
            Toast.makeText(calorie_tracker_add_list.this, "yo! calories tracked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(calorie_tracker_add_list.this, "Oops, u dogged wrong info", Toast.LENGTH_LONG).show();
        }
        //Intent intent  = new Intent(calorie, calorie_tra)
     }



}

