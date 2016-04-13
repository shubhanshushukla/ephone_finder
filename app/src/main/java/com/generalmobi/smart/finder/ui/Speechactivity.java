package com.generalmobi.smart.finder.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.generalmobi.smart.finder.R;
import com.generalmobi.smart.finder.util.ShakeListener;
import com.generalmobi.smart.finder.util.ShakeService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

/**
 * Created by admin on 4/12/2016.
 */
public class Speechactivity extends BootstrapActivity{
    public static Camera cam = null;
    private boolean isFlashOn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Phone Are you there");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 4);
        startActivityForResult(intent, 1001);

    }
    public void flashLightOn() {

    try {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            cam = Camera.open();
            Camera.Parameters p = cam.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(p);
            cam.startPreview();
        }
    } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(getBaseContext(), "Exception flashLightOn()",
                Toast.LENGTH_SHORT).show();
    }
}
    public void flashLightOff() {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            //If Voice recognition is successful then it returns RESULT_OK
            if (resultCode == RESULT_OK)
            {
                ArrayList<String> textMatchList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (textMatchList.contains("phone"))
                {
                    final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(1000);
                    MediaPlayer mp = MediaPlayer.create(this, R.raw.iamhere);
                    mp.start();
                    for(int i=0;i<10;i++){
                        flashLightOn();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flashLightOff();
                    }


                }



                }
            }
        }

    }

