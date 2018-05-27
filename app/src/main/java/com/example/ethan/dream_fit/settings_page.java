package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class settings_page extends AppCompatActivity {

    DatabaseHelper myDB;
    DatabaseHelper_Main main_DB;
    SharedPreferences prefs;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        prefs = context.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);
    }

    public void onEmpty(View view){

        myDB = new DatabaseHelper(this);
        main_DB = new DatabaseHelper_Main(this);

        //call the ->Cursor which will get all the contents of the database
        Cursor data = myDB.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(settings_page.this, "database is currently empty", Toast.LENGTH_LONG).show();

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
                            Toast.makeText(settings_page.this, "Database Deleted", Toast.LENGTH_LONG).show();
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

    public void onReset(View view){
        prefs.edit().putInt("BMI",0).apply();
        Toast.makeText(settings_page.this, "BMI Cleared", Toast.LENGTH_LONG).show();

    }
}
