package com.example.ethan.dream_fit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStart extends BroadcastReceiver
{
    MyAlarmReceiver alarm = new MyAlarmReceiver();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            alarm.SetAlarm(context);
        }
    }
}