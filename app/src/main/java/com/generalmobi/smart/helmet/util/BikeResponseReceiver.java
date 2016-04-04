package com.generalmobi.smart.helmet.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by shubhanshu on 19/2/16.
 */
public class BikeResponseReceiver extends BroadcastReceiver {

    private Intent  intent;
    private Activity activity;
    private Class<?> activityClass;
    public BikeResponseReceiver(Intent  intent,Activity activity,Class<?> activityClass)
    {
        this.intent=intent;
        this.activity=activity;
        this.activityClass=activityClass;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle b = intent.getExtras();
        Intent i = new Intent(activity, activityClass);
        String message = intent.getStringExtra("message");
        i.putExtra("message", message);
        if (message.contains("map"))
        {
            i = new Intent(activity, activityClass);
            i.putExtra("message", message);

        }
        else
        {
            i = new Intent(activity, activityClass);
            i.putExtra("message", message);
        }
        activity.startActivity(i);
    }
}
