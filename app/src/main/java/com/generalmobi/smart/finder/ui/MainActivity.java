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
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.generalmobi.smart.finder.BootstrapApplication;
import com.generalmobi.smart.finder.BootstrapServiceProvider;
import com.generalmobi.smart.finder.R;
import com.generalmobi.smart.finder.util.ShakeListener;
import com.generalmobi.smart.finder.util.ShakeService;
import com.generalmobi.smart.finder.util.Toaster;

import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;


/**

 */
public class MainActivity extends BootstrapActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar seekBar;
   TextView textview;
    private ShakeListener mShaker;
    @Inject
    BootstrapServiceProvider serviceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        BootstrapApplication.component().inject(this);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textview =(TextView) findViewById(R.id.sensor_value);
        startService();
        seekBar.setOnSeekBarChangeListener(this);



    }
    public void startService() {
        startService(new Intent(this, ShakeService.class));
    }

    @Override
    public void onResume()
    {
        //mShaker.resume();
        super.onResume();
    }
    @Override
    public void onPause()
    {
        //mShaker.pause();
        super.onPause();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        ShakeListener.FORCE_THRESHOLD=progress*100;
        textview.setText("Sensitivity :"+progress);


       // Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}




