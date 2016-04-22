package com.experiment.smart.finder.util;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shubhanshu on 19/2/16.
 */
public class SmsUtil
{

    public static void sendSMS(Context context, String message) {
        Log.i("Send SMS", message);
        SmsManager smsManager = SmsManager.getDefault();
        try {
            smsManager.sendTextMessage("53010", null, message, null, null);
            Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "SMS failed, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
