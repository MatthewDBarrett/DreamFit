package com.example.ethan.dream_fit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class calorie_tracker extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);
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













