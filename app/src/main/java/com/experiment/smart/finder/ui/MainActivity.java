package com.experiment.smart.finder.ui;


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
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.experiment.smart.finder.BootstrapApplication;
import com.experiment.smart.finder.BootstrapServiceProvider;
import com.experiment.smart.finder.R;
import com.experiment.smart.finder.util.ShakeListener;
import com.experiment.smart.finder.util.ShakeService;
import com.experiment.smart.finder.util.Toaster;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;


/**

 */
public class MainActivity extends BootstrapActivity implements SeekBar.OnSeekBarChangeListener {


    @Bind(R.id.seekBar)
     SeekBar seekBar;

    @Bind(R.id.sensor_value)
   TextView textview;

    @Bind(R.id.m_ok_button)
    Button button;
    private ShakeListener mShaker;
    @Inject
    BootstrapServiceProvider serviceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        BootstrapApplication.component().inject(this);


        startService();
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(5);
        ShakeListener.FORCE_THRESHOLD=5*100;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    moveTaskToBack(true);
            }

        });

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
        textview.setText("Sensitivity :" + progress);

       // Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}




