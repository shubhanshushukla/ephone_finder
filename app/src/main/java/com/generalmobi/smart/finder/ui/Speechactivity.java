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
import android.os.RemoteException;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.generalmobi.smart.finder.R;
import com.generalmobi.smart.finder.util.SafeAsyncTask;
import com.generalmobi.smart.finder.util.ShakeListener;
import com.generalmobi.smart.finder.util.ShakeService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by admin on 4/12/2016.
 */
public class Speechactivity extends BootstrapActivity{
    public static Camera cam = null;
    private boolean isFlashOn;


    @Bind(R.id.ok_button)
    public Button okButton;

    @Bind(R.id.result_layout)
    public FrameLayout result_layout;

private   SafeAsyncTask<String> stringSafeAsyncTask=null;
    private Thread blinkThread=null;

static boolean state=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Phone Are you there");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 4);
        startActivityForResult(intent, 1001);
        setContentView(R.layout.result_view);
        ButterKnife.bind(this);
        ShakeService.mp.setLooping(true);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (stringSafeAsyncTask!=null)
                {
                    try {
                        stringSafeAsyncTask.cancel(true);
                    }
                    catch (Exception ex){

                    }
                    try {
                         ShakeService.mp.pause();
                     }
                    catch (Exception ex){

                    }


                }
                moveTaskToBack(true);
            }
        });

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
                Timber.i("STT:"+textMatchList);
                boolean phone =textMatchList.contains("phone") || textMatchList.contains("fone");
                boolean are =textMatchList.contains("are")||textMatchList.contains("r");
                boolean you =textMatchList.contains("you")||textMatchList.contains("u");
                boolean there =textMatchList.contains("there")||textMatchList.contains("dere")||textMatchList.contains("deer") ||textMatchList.contains("beer")||textMatchList.contains("bear");

                for (String line : textMatchList)
                {
                    if (phone==false) {
                        phone = line.contains("phone") || line.contains("fone");
                    }
                    if (are==false) {
                        are =line.contains("are")||line.contains("r");
                    }
                    if (you==false) {
                        you= line.contains("you")||line.contains("u");
                    }
                    if (there==false) {
                        there =line.contains("there")||line.contains("dere")||line.contains("deer") ||line.contains("beer")||line.contains("bear");
                    }
                }



                if (phone && (are || you || there))
                {
                    final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(1000);
                    ShakeService.mp.start();

                    stringSafeAsyncTask = new SafeAsyncTask<String>() {
                        @Override
                        public String call() throws Exception {

                            for (int i = 0; i < 30; i++) {
                                flashLightOn();

                                 try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    flashLightOff();
                                     break;
                                }
                                flashLightOff();
                            }
                            return "OK";
                         }
                    } ;
                    stringSafeAsyncTask.execute();


                }
                else
                {
                    finish();;
                }



                }
            }
        }

    }

