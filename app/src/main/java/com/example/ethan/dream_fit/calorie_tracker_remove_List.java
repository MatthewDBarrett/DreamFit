package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class calorie_tracker_remove_List extends AppCompatActivity {

    DatabaseHelper myDB;
    DatabaseHelper_Main main_DB;
    ArrayList<Item> itemList;
    ListView mainListView;
    Item thisItem;
    final Context context = this;
    LayoutInflater layoutinflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_remove_list);


        mainListView = (ListView) findViewById(R.id.foodList);
        main_DB = new DatabaseHelper_Main(this);

        //1. create an array list and
        //2. populate it through database
        //3. adapt that through list adapter

        //ArrayList<String> thisList = new ArrayList<>();
        //Instead of the above code; we will use an arrayList of items
        itemList = new ArrayList<>();

        //call the ->Cursor which will get all the contents of the database
        Cursor data = main_DB.getListContents();

        //populate the array list with the data now
        if (data.getCount() == 0)
            Toast.makeText(calorie_tracker_remove_List.this, "database is currently empty", Toast.LENGTH_LONG).show();
        else {
            while (data.moveToNext()) {
                //continue looping through

                //We will store the user's food item.
                thisItem = new Item(data.getString(1), data.getString(2));

                //-> '1' here means the 'column no' for 'description 'in the database
                //-> '2' here means the 'column no' for 'calorie 'in the database

                //Now add items to the array_List called 'itemList'
                itemList.add(thisItem);

                final two_Column_ListAdapter adapter = new two_Column_ListAdapter(this,R.layout.adapter_view_layout, itemList);

                mainListView.setAdapter(adapter);

                mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //create an alert dialog
                        final int itemToRemove = i ;
                        AlertDialog.Builder thisAlertDialog = new AlertDialog.Builder(context);
                        thisAlertDialog.setCancelable(false);
                        thisAlertDialog.setMessage("Delete Item? ");

                        thisAlertDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //----------Delete The item--------------------------
                                  String thisDescription ;
                                  thisDescription = itemList.get(itemToRemove).getDescription();
                                    //remove from main_db
                                    main_DB.removeItem(thisDescription);
                                    //remove item from the array list containing all the items
                                    adapter.remove(itemList.get(itemToRemove));
                                    //Notify the adpater
                                     adapter.notifyDataSetChanged();
                                //----------------------------------------------------

                             // Update the array List
                             // itemList.addAll(getShitMix());
                             //adapter.notifyDataSetChanged();
                            }
                        });

                        thisAlertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        thisAlertDialog.show();
                    }

                });

            }

        }

    }





}
