package com.example.ethan.dream_fit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class calorie_tracker_add_item extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_add_item);
    }

    public void onSave(View view) {
        //Add functionality later
    }







   /* public void onCalculate(View view){
        EditText kilogramText = (EditText) findViewById(R.id.kilogram);
        EditText centimeterText = (EditText) findViewById(R.id.centimeter);
        TextView resultText = (TextView) findViewById(R.id.resultText);
        double kilogram, centimeter;
        double bmiResult;

        kilogram = Double.parseDouble(kilogramText.getText().toString());
        centimeter = Double.parseDouble(centimeterText.getText().toString());
        bmiResult = (kilogram/(centimeter/100))/(centimeter/100);

        resultText.setText(String.valueOf(bmiResult)); */
    }













