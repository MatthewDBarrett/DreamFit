package com.example.ethan.dream_fit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class calorie_tracker_add_list extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_add_list);
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, calorie_tracker_add_item.class);
        startActivity(intent);
    }
}

