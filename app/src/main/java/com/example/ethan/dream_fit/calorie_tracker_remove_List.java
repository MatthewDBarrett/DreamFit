package com.example.ethan.dream_fit;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class calorie_tracker_remove_List extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_remove_list);

        //ListView mainListView = (ListView) findViewById(R.id.removeFood);
        //mainListView
    }

     public void onEmpty(View view){

         myDB = new DatabaseHelper(this);

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
