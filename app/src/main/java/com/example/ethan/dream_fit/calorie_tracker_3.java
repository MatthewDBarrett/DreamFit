package com.example.ethan.dream_fit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class calorie_tracker_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_3);
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, calorie_tracker_2.class);
        startActivity(intent);
    }

    public void onRemove(View view){

    }
}
