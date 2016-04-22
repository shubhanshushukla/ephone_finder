package com.experiment.smart.finder.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;

import com.experiment.smart.finder.R;
import com.experiment.smart.finder.ui.Speechactivity;
import com.experiment.smart.finder.ui.Speechactivity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by admin on 4/12/2016.
 */
public class ShakeService extends Service implements ShakeListener.OnShakeListener {


    private ShakeListener mShaker;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public static volatile boolean blink=true;
    public  static   MediaPlayer mp =null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp =MediaPlayer.create(this, R.raw.iamhere);
        this.mSensorManager = ((SensorManager)getSystemService(Context.SENSOR_SERVICE));
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
        mShaker = new ShakeListener(this);
    }

    @Override
    public void onShake() {
        // startActivityForResult(intent, 1001);
        /*final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(1000);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.iamhere);
        mp.start();
        Toast.makeText(this, "Vibrating", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //super.onStartCommand(intent, flags, startId);

        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
/*
                vibe.vibrate(1000);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.iamhere);
                mp.start();
*/
                Intent intent1 =new Intent(ShakeService.this, Speechactivity.class);

                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);



                Toast.makeText(getApplicationContext(), "SpeechActivity Started", Toast.LENGTH_SHORT).show();
            }
        });
        return Service.START_STICKY ;
    }
}