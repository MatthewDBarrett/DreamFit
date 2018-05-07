package com.example.ethan.dream_fit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageButton profilePicture = (ImageButton) findViewById(R.id.profilePic);
        profilePicture.setScaleType(ImageView.ScaleType.FIT_XY);
        profilePicture.setAdjustViewBounds(true);
    }

    public void openStepCounter(View view){
        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);
    }

    public void openBMICalculator(View view){
        Intent intent = new Intent(this, bmi_calculator.class);
        startActivity(intent);
    }

    public void openProfilePage(View view){
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }

    public void openCalorieTracker(View view){
        Intent intent = new Intent(this, calorie_tracker_main_page.class);
        startActivity(intent);
    }
}
