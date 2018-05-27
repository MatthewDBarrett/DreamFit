package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class stats extends AppCompatActivity {

    /*
     *  Libraries Imported for the graphs -
     *  MPAndroidChart
     *  Created By - PhilJay
     *  URL - https://github.com/PhilJay/MPAndroidChart
     */

    BarChart thisBarChart ;
    SharedPreferences prefs;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        thisBarChart = (BarChart) findViewById(R.id.bargraph);

        //create an array list
        ArrayList<BarEntry> barEnteries= new ArrayList<>();

        prefs = context.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);

            barEnteries.add(new BarEntry(prefs.getInt("Monday_stepCountStat", 0),0));

            barEnteries.add(new BarEntry(prefs.getInt("Tuesday_stepCountStat", 0),1));

            barEnteries.add(new BarEntry( prefs.getInt("Wednesday_stepCountStat", 0),2));

            barEnteries.add(new BarEntry( prefs.getInt("Thursday_stepCountStat", 0),3));

            barEnteries.add(new BarEntry(prefs.getInt("Friday_stepCountStat", 0),4));

            barEnteries.add(new BarEntry(prefs.getInt("Saturday_stepCountStat", 0),5));

            barEnteries.add(new BarEntry( prefs.getInt("Sunday_stepCountStat", 0),6));



        //create bar data set

        BarDataSet barDataSet = new BarDataSet(barEnteries,"Daily Step Count");

        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        BarData theData = new BarData(days,barDataSet);
        thisBarChart.setData(theData);

        thisBarChart.setTouchEnabled(true);
        thisBarChart.setDragEnabled(true);
        thisBarChart.setScaleEnabled(true);

    }
}
