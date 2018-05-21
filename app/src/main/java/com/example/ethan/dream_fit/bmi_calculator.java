package com.example.ethan.dream_fit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bmi_calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
    }

    public void onCalculate(View view){
        EditText kilogramText = (EditText) findViewById(R.id.kilogram);
        EditText centimeterText = (EditText) findViewById(R.id.centimeter);
        TextView resultText = (TextView) findViewById(R.id.resultText);

        double kilogram, centimeter;
        int bmiResult;

        //(kilogramText.getText().equals(0) && centimeterText.getText().equals(0)){

        try{
                kilogram = Double.parseDouble(kilogramText.getText().toString());
                centimeter = Double.parseDouble(centimeterText.getText().toString());
                bmiResult = (int) ((kilogram/(centimeter/100))/(centimeter/100));
                resultText.setText(String.valueOf(bmiResult));

        }catch(NumberFormatException thisException){
            if((kilogramText.getText().toString()).matches("") && (centimeterText.getText().toString()).matches(""))
                Toast.makeText(bmi_calculator.this,"Please Enter your weight and height",Toast.LENGTH_SHORT).show();

            else if ((kilogramText.getText().toString()).matches(""))
                Toast.makeText(bmi_calculator.this,"Please Enter your weight !",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(bmi_calculator.this,"Please Enter your height !",Toast.LENGTH_SHORT).show();
        }

    }
}
