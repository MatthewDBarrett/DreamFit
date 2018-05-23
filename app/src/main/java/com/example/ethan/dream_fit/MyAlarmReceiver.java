package com.example.ethan.dream_fit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;


public class MyAlarmReceiver extends BroadcastReceiver {
    SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        prefs = context.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);
        prefs.edit().putInt("stepAmnt", 0).apply();
        Toast.makeText(context, "What the heck", Toast.LENGTH_SHORT).show();
        Log.w("com.example.ethan.dream_fit", "NANI");
    }

    public void SetAlarm(Context context)
    {
        Calendar calendar = Calendar.getInstance();
        //set the time to 6AM
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MyAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pi);
    }
}
