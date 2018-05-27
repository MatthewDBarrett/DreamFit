package com.example.ethan.dream_fit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyAlarmReceiver extends BroadcastReceiver {
    SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        prefs = context.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);                            //STEP COUNTER

        //check the day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);


        //Before Resetting to zero, add it to stepCountStat
        Integer stepAmnt = prefs.getInt("stepAmnt",0);

        //-------------------------------------ADDING DAILY STEP COUNTS-----------------------------

        if(dayOfTheWeek.toLowerCase().contains("Monday".toLowerCase()))
            prefs.edit().putInt("Monday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Tuesday".toLowerCase()))
            prefs.edit().putInt("Tuesday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Wednesday".toLowerCase()))
            prefs.edit().putInt("Wednesday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Thursday".toLowerCase()))
            prefs.edit().putInt("Thursday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Friday".toLowerCase()))
            prefs.edit().putInt("Friday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Saturday".toLowerCase()))
            prefs.edit().putInt("Saturday_stepCountStat", stepAmnt).apply();

        if(dayOfTheWeek.toLowerCase().contains("Sunday".toLowerCase()))
            prefs.edit().putInt("Sunday_stepCountStat", stepAmnt).apply();

        //------------------------------------------------------------------------------------------

        //Check Max step Count and DAY
        //Check Min Step Count and DAY
        //Check Max Calories Burnt
        //Check Max Calories consumed

        // Resetting step count to zero
        prefs.edit().putInt("stepAmnt", 0).apply();
        Log.w("com.example.ethan.dream_fit", "NANI");                                         //STEP COUNTER


    }

    public void SetAlarm(Context context)
    {
        Calendar calendar = Calendar.getInstance();
        //set the time to 6AM
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MyAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pi);
    }

    public static String getFullDayName(int day) {
        /*
         * STACK OVERFLOW
         * URL- https://stackoverflow.com/questions/7651221/android-how-to-get-the-current-day-of-the-week-monday-etc-in-the-users-l
         */

        Calendar c = Calendar.getInstance();
        // date doesn't matter - it has to be a Monday
        // I new that first August 2011 is one ;-)
        c.set(2011, 7, 1, 0, 0, 0);
        c.add(Calendar.DAY_OF_MONTH, day);
        return String.format("%tA", c);
    }
}
