package com.example.ethan.dream_fit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class bmi_calculator extends AppCompatActivity {

    private SeekBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        progressBar = findViewById(R.id.seekBarBMI);
        progressBar.setProgress(0);
        progressBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void onCalculate(View view){
        EditText kilogramText = (EditText) findViewById(R.id.kilogram);
        EditText centimeterText = (EditText) findViewById(R.id.centimeter);
        TextView resultText = (TextView) findViewById(R.id.resultText);

        double kilogram, centimeter;
        float bmiResult;

        //(kilogramText.getText().equals(0) && centimeterText.getText().equals(0)){

        try{
                kilogram = Double.parseDouble(kilogramText.getText().toString());
                centimeter = Double.parseDouble(centimeterText.getText().toString());
                bmiResult = (float) ((kilogram/(centimeter/100))/(centimeter/100));
                resultText.setText(String.valueOf(bmiResult));


            if (isBetween(bmiResult, 0, 18.5)) {
                progressBar.setProgress(1);
            } else if (isBetween(bmiResult, 18.5, 25)) {
                progressBar.setProgress(2);
            } else if (isBetween(bmiResult, 25, 30)) {
                progressBar.setProgress(3);
            } else if (isBetween(bmiResult, 30,Double.POSITIVE_INFINITY )) {
                progressBar.setProgress(4);
            }

        }catch(NumberFormatException thisException){
            if((kilogramText.getText().toString()).matches("") && (centimeterText.getText().toString()).matches(""))
                Toast.makeText(bmi_calculator.this,"Please Enter your weight and height",Toast.LENGTH_SHORT).show();

            else if ((kilogramText.getText().toString()).matches(""))
                Toast.makeText(bmi_calculator.this,"Please Enter your weight !",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(bmi_calculator.this,"Please Enter your height !",Toast.LENGTH_SHORT).show();
        }

        //To close the virtual keyboard after user clicks the 'On calculate'
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public static boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x <= upper;
    }
}
