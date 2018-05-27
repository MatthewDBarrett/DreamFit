package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class stats extends AppCompatActivity {

    /*
     *  Libraries Imported for the graphs -
     *  MPAndroidChart
     *  Created By - PhilJay
     *  URL - https://github.com/PhilJay/MPAndroidChart
     */

    BarChart thisBarChart ;
    PieChart pieChart;
    SharedPreferences prefs;
    final Context context = this;
    private String[] xDataPie = {"maxStep","minStep","maxCalBurnt","maxCalConsumed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        thisBarChart = (BarChart) findViewById(R.id.bargraph);
        thisBarChart.getDescription().setEnabled(false);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        //create an array list
        ArrayList<BarEntry> barEnteries= new ArrayList<>();

        prefs = context.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);

            barEnteries.add(new BarEntry(0f,prefs.getInt("Monday_stepCountStat", 0)));
            barEnteries.add(new BarEntry(1f,prefs.getInt("Tuesday_stepCountStat", 0)));
            barEnteries.add(new BarEntry( 2f,prefs.getInt("Wednesday_stepCountStat", 0)));
            barEnteries.add(new BarEntry( 3f,prefs.getInt("Thursday_stepCountStat", 0)));
            barEnteries.add(new BarEntry(4f,prefs.getInt("Friday_stepCountStat", 0)));
            barEnteries.add(new BarEntry(5f,prefs.getInt("Saturday_stepCountStat", 0)));
            barEnteries.add(new BarEntry(6f, prefs.getInt("Sunday_stepCountStat", 0)));

        //create bar data set

        BarDataSet barDataSet = new BarDataSet(barEnteries,"Daily Step Count");
        barDataSet.setDrawValues(true);
        ArrayList<String> days = new ArrayList<>();
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thurs");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");

        BarData theData = new BarData(barDataSet);

         theData.setBarWidth(0.9f);
        thisBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(days));
        thisBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        thisBarChart.setFitBars(true);
        thisBarChart.setData(theData);
        thisBarChart.setTouchEnabled(true);
        thisBarChart.setDragEnabled(true);
        thisBarChart.setScaleEnabled(true);
        thisBarChart.animateY(1300);
        thisBarChart.invalidate();


        //---------------------------PIE CHART------------------------------------
        pieChart.setRotationEnabled(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(prefs.getString("name", "<nonamespecified>" )+ ": 's health Stats ");
        pieChart.setCenterTextSize(10);

                                             // Max Step count
        Integer minStep = prefs.getInt("Min_Step_count",0);                                        // Min Step count

        ArrayList<PieEntry> entries = new ArrayList<>();

        //populate yData
        entries.add(new PieEntry(prefs.getInt("left",0),"Calories To Burn "));
        entries.add(new PieEntry(prefs.getInt("BMI",0),"Body to Mass Index"));
        entries.add(new PieEntry(prefs.getInt("Max_calorie_burnt",0),"Calories Burnt"));
        entries.add(new PieEntry(prefs.getInt("Max_calorie_consumed",0),"Calories Consumed"));

        //Create data set
        PieDataSet thisDataSet = new PieDataSet(entries,"");
        thisDataSet.setSliceSpace(4);
        thisDataSet.setValueTextSize(10);

        //add Colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(105,189,226));
        colors.add(Color.rgb(48,164,215));
        colors.add(Color.rgb(34,115,151));
        colors.add(Color.rgb(188, 188, 188));

        //set the colors to pie data set
        thisDataSet.setColors(colors);
        pieChart.setEntryLabelColor(Color.BLACK);

        //add legend to the pie chart
        Legend legend = pieChart.getLegend();
        pieChart.getLegend().setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.CIRCLE);
        //Set piedata
        PieData pieData = new PieData(thisDataSet);
        pieChart.setData(pieData);
        pieChart.animateY(1300);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();


    }

}
