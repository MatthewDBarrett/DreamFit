package com.example.ethan.dream_fit;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    LayoutInflater layoutinflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_remove_list);

        mainListView = (ListView) findViewById(R.id.foodList);
        myDB = new DatabaseHelper(this);


        // add heading to the list view
        layoutinflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)layoutinflater.inflate(R.layout.item_header,mainListView,false);
        // ListView disable clicks on header view
        mainListView.addHeaderView(header,null,false);

        //1. create an array list and
        //2. populate it through database
        //3. adapt that through list adapter

        //ArrayList<String> thisList = new ArrayList<>();
        //Instead of the above code; we will use an arrayList of items
        itemList = new ArrayList<>();

        //call the ->Cursor which will get all the contents of the database
        Cursor data = myDB.getListContents();

        //populate the array list with the data now
        if (data.getCount() == 0)
            Toast.makeText(calorie_tracker_remove_List.this, "database is currently empty", Toast.LENGTH_LONG).show();
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


                //THis is what Carlos suggested
                mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        // get the current Index
                        Item thisItem = itemList.get(i-1);

                        //Note- there is a glitch here, due to the addition of a header in list view
                        //the count of the list view becomes 'off' by one
                        //Fix for noe is too get the previous item than that the one is selected


                        Toast.makeText(calorie_tracker_remove_List.this, "Item, removed dogged!", Toast.LENGTH_LONG).show();

                        String description = thisItem.getDescription();
                        String calorie = thisItem.getCalorie();

                    }
                });
            }
        }

    }

     public void onEmpty(View view){

         myDB = new DatabaseHelper(this);
         main_DB = new DatabaseHelper_Main(this);

         //call the ->Cursor which will get all the contents of the database
         Cursor data = myDB.getListContents();

         if (data.getCount() == 0) {
             Toast.makeText(calorie_tracker_remove_List.this, "database is currently empty", Toast.LENGTH_LONG).show();

         }else{

             AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
             builder1.setMessage("Are you sure you want to dump current database ?");
             builder1.setCancelable(true);

             builder1.setPositiveButton(
                     "Yes",

                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             myDB.clearDatabase();
                             main_DB.clearDatabase();
                             Toast.makeText(calorie_tracker_remove_List.this, "Database Deleted", Toast.LENGTH_LONG).show();
                         }
                     });

             builder1.setNegativeButton(
                     "No",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int id) {
                             dialog.cancel();
                         }
                     });

             AlertDialog alert11 = builder1.create();
             alert11.show();
         }
     }

}
