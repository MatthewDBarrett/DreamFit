package com.example.ethan.dream_fit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class alarmService extends Service
{
    MyAlarmReceiver alarm = new MyAlarmReceiver();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Context context = getApplicationContext();
        alarm.SetAlarm(context);
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}