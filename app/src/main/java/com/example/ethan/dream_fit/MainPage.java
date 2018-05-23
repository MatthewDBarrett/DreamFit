package com.example.ethan.dream_fit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class MainPage extends AppCompatActivity {
    private Context context = this;
    Intent alarmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageButton profilePicture = (ImageButton) findViewById(R.id.profilePic);
        profilePicture.setScaleType(ImageView.ScaleType.FIT_XY);
        profilePicture.setAdjustViewBounds(true);

        alarmService = new Intent(context, alarmService.class);
        context.startService(alarmService);
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

    public void openSettings(View view){
        Intent intent = new Intent(this, settings_page.class);
        startActivity(intent);
    }

    public void openStats(View view){
        Intent intent = new Intent(this, stats.class);
        startActivity(intent);
    }

    public void openHeartRate(View view){
        Intent intent = new Intent(this, heart_rate.class);
        startActivity(intent);
    }
}
