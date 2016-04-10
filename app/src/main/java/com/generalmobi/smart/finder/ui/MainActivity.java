package com.generalmobi.smart.finder.ui;


import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.generalmobi.smart.finder.BootstrapApplication;
import com.generalmobi.smart.finder.BootstrapServiceProvider;
import com.generalmobi.smart.finder.util.Toaster;

import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;


/**

 */
public class MainActivity extends BootstrapActivity {

    public static volatile   int helmetState=1;
    public static final  int CONNECTED=3;
    public static  int DISCONNECTED=2;
    public static  int UNKNOWN=1;
    public static TextToSpeech textToSpeech;
    AudioManager am;
    @Inject
    BootstrapServiceProvider serviceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);


        Timber.i("Sdk version : " + Build.VERSION.RELEASE);



    }




}
