package com.generalmobi.smart.finder.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.generalmobi.smart.finder.ui.MainActivity;

/**
 * Created by shubhanshu on 7/4/16.
 */
public class BootReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent myIntent = new Intent(context, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
