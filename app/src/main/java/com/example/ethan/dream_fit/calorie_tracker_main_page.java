package com.example.ethan.dream_fit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class calorie_tracker_main_page extends AppCompatActivity {


    DatabaseHelper_Main main_DB;
    ArrayList<Item> itemList;
    ListView mainListView;
    Item thisItem;
    LayoutInflater layoutinflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_main_page);

        mainListView = (ListView) findViewById(R.id.foodList);
        main_DB = new DatabaseHelper_Main(this);

        // add heading to the list view
        layoutinflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)layoutinflater.inflate(R.layout.item_header,mainListView,false);
        mainListView.addHeaderView(header);

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
            Toast.makeText(calorie_tracker_main_page.this, "database is currently empty", Toast.LENGTH_LONG).show();
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
               /* mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // get the current Index
                        Item thisItem = itemList.get(i);
                        String description = thisItem.getDescription();
                        String calorie = thisItem.getCalorie();
                        main_DB.addData(description,calorie);

                    }
                });
                */
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onAdd(View view){
        Intent intent = new Intent(this, calorie_tracker_add_list.class);
        startActivity(intent);
    }

    public void onRemove(View view){
      Intent intent = new Intent(this, calorie_tracker_remove_List.class);
      startActivity(intent);
    }
}
