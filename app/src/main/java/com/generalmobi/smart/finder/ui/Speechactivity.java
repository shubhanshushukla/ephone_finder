package com.generalmobi.smart.finder.ui;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;

import com.generalmobi.smart.finder.R;
import com.generalmobi.smart.finder.util.ShakeListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

/**
 * Created by admin on 4/12/2016.
 */
public class Speechactivity extends BootstrapActivity{

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

                }
            }
        }

    }
}
