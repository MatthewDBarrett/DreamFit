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
        //saveBtn.setOnClickListener(new View.OnClickListener() {

            //@Override
            //public void onClick(View v) {

                String thisDescription = description.getText().toString();
                String thisCalories = calorie.getText().toString();

                //call add data function in database helper
                if (description.length() != 0 && calorie.length() != 0) {

                    //This will only works if user enters everything in the edit text fields
                    addData(thisDescription,thisCalories);

                    //clear out the text fields
                    description.setText("");
                    calorie.setText("");


                } else if (description.length() == 0 && calorie.length() == 0) {
                    Toast.makeText(calorie_tracker_add_item.this, "Please! add food item to track ", Toast.LENGTH_LONG).show();
                }
                  else if (description.length() == 0 && calorie.length() != 0) {
                    Toast.makeText(calorie_tracker_add_item.this, "You! forgot to add description", Toast.LENGTH_LONG).show();
                } else if (description.length() != 0 && calorie.length() == 0) {
                    Toast.makeText(calorie_tracker_add_item.this, "You! forgot to add calories", Toast.LENGTH_LONG).show();
                }
            //}
        //});
    }

    public void addData(String newDescription, String newCalorie){
        // this will add data to the database
        boolean insertData = calories_Tracker_DB.addData(newDescription, newCalorie);
        if (insertData == true) {
            Toast.makeText(calorie_tracker_add_item.this, "yo! calories tracked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(calorie_tracker_add_item.this, "Oops, u dogged wrong info", Toast.LENGTH_LONG).show();
        }

    }
}













