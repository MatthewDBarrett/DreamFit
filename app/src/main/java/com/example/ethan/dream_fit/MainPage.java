package com.example.ethan.dream_fit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

    }

    public void openStepCounter(View view){
        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);
    }
}
