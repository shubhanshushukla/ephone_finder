package com.generalmobi.smart.helmet.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.generalmobi.smart.helmet.ui.MainActivity;

import timber.log.Timber;

public class TelephonyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        try {
            if (intent != null && intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                 String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            } else {
                //get the phone state
                String newPhoneState = intent.hasExtra(TelephonyManager.EXTRA_STATE) ? intent.getStringExtra(TelephonyManager.EXTRA_STATE) : null;
                Bundle bundle = intent.getExtras();

                 if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    //read the incoming call number
                    String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    String name=displayContacts(phoneNumber, context);
                    Timber.i("Current state is : "+MainActivity.helmetState);
                    if (MainActivity.helmetState==MainActivity.CONNECTED)
                    {
                        //Do speech to text
                        if (MainActivity.textToSpeech!=null)
                        {
                            MainActivity.textToSpeech.speak(name, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
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
                        phoneNo=phoneNo.replaceAll(" ","");
                        if (phoneNo.contains(search)) {
                            result=name;
                            Log.i("Phone-Found", "Name: " + name + ", Phone No: " + phoneNo);

                        }


                    }
                    pCur.close();
                }
            }
        }

        try {
            cur.close();;
        }
        catch (Exception ex)
        {

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


