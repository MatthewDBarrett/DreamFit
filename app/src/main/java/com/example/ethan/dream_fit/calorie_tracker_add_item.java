package com.example.ethan.dream_fit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class calorie_tracker_add_item extends AppCompatActivity {


    EditText description, calorie;
    Button saveBtn;

    DatabaseHelper calories_Tracker_DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_add_item);

        calories_Tracker_DB = new DatabaseHelper(this);

        //Typecast variables
        description = (EditText) findViewById(R.id.description);
        calorie = (EditText) findViewById(R.id.calorie);
        saveBtn = (Button) findViewById(R.id.saveBtn);

    }

    public void onSave(View v) {
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String thisDescription = description.getText().toString();
                String thisCalories = calorie.getText().toString();

                //call add data function in database helper
                boolean insertData = calories_Tracker_DB.addData(thisDescription, thisCalories);

                if (insertData == true) {
                    Toast.makeText(calorie_tracker_add_item.this, "yo! calories tracked", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(calorie_tracker_add_item.this, "Oops, u dogged wrong info", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}












