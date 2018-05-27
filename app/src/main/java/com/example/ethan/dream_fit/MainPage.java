package com.example.ethan.dream_fit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainPage extends AppCompatActivity {
    private static final String TAG = "IMAGE LOADING:" ;
    private Context context = this;
    Intent alarmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageButton profilePicture = findViewById(R.id.profilePic);
        ImageButton bmiButton = findViewById(R.id.bmiCalculator);
        ImageButton stepButton = findViewById(R.id.stepCounter);
        ImageButton calorieButton = findViewById(R.id.calorieCounter);
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        ImageButton statsButton = findViewById(R.id.stats);
        ImageButton heartButton = findViewById(R.id.heart_rate);

        profilePicture.setScaleType(ImageView.ScaleType.FIT_XY);
        profilePicture.setAdjustViewBounds(true);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        File file = new File("sdcard/Android/data/com.example.ethan.dream_fit/files", "profile.png");
        try {
            FileInputStream streamIn = new FileInputStream(file);
            profilePicture.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            profilePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
            streamIn.close();
        }
        catch (Exception e) {
            Log.v(TAG, "FAILED");
        }
        alarmService = new Intent(context, alarmService.class);
        context.startService(alarmService);

        profilePicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Profile Page",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        bmiButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "BMI Calculator",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        stepButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Step Counter",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        calorieButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Calorie Counter",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        settingsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Settings",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        statsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Statistics History",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        heartButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Heart Rate Monitor",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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
