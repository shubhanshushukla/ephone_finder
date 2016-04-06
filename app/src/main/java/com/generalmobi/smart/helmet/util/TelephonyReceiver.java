package com.generalmobi.smart.helmet.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class TelephonyReceiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context arg0, Intent intent) {

        try {
            if (intent != null && intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
//Toast.makeText(context, "Outgoign call", 1000).show();
                String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            } else {
//get the phone state
                String newPhoneState = intent.hasExtra(TelephonyManager.EXTRA_STATE) ? intent.getStringExtra(TelephonyManager.EXTRA_STATE) : null;
                Bundle bundle = intent.getExtras();

                if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//read the incoming call number
                    String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    String name=displayContacts(phoneNumber, arg0);

                    Toast.makeText(arg0, "Name: " + name + ", Phone No: " + phoneNumber, Toast.LENGTH_LONG).show();
                    Log.i("My-Name", "Name: " + name + ", Phone No: " + phoneNumber);


                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {

                    Log.i("PHONE RECEIVER", "Telephone is now idle");
                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

                    Log.i("PHONE RECEIVER", "Telephone is now busy");
                }

            }

        } catch (Exception ee) {
            Log.i("Telephony receiver", ee.getMessage());
        }
    }
    private String displayContacts(String mobile,Context context) {
        String search="";
        if (mobile !=null && mobile.startsWith("+91"))
        {
            search=mobile.substring(3);
        }
        else if (mobile !=null && mobile.startsWith("91") && mobile.length()==12)
        {
            search=mobile.substring(2);
        }
        else
        {
            search=mobile;
        }
        String result=null;
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        if (phoneNo.contains(search)) {
                            result=name;
                        }
                        Log.i("My-Name", "Name: " + name + ", Phone No: " + phoneNo);
                        Toast.makeText(context, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_LONG).show();


                    }
                    pCur.close();
                }
            }
        }

        if (result==null)
        {
            return mobile;
        }
        else
        {
            return result;
        }
    }
}


