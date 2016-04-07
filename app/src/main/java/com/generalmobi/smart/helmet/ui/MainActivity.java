package com.generalmobi.smart.helmet.ui;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.generalmobi.smart.helmet.BootstrapApplication;
import com.generalmobi.smart.helmet.BootstrapServiceProvider;

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


        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        IntentFilter filter3 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        this.registerReceiver(mReceiver, filter1);
        this.registerReceiver(mReceiver, filter2);
        this.registerReceiver(mReceiver, filter3);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Timber.i("TTS Status= "+status);
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);


        /*Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
*/
        moveTaskToBack (true);

    }




    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Timber.i("******************"+action+"******************");

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
             }
            else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action))
            {
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Timber.i("******************HELMET CONNECTED******************");
                if (helmetState==UNKNOWN || helmetState==DISCONNECTED)
                {
                    //Send sms to crbt
                }
                helmetState=CONNECTED;
                MainActivity.textToSpeech.speak("Smart Helmet Connected", TextToSpeech.QUEUE_FLUSH, null);
              }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
             }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
             }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Timber.i("******************HELMET DISCONNECTED******************");
                if (helmetState==UNKNOWN || helmetState==CONNECTED)
                {
                    //Send sms to crbt
                }
                helmetState=DISCONNECTED;
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                MainActivity.textToSpeech.speak("Smart Helmet Dissconnected", TextToSpeech.QUEUE_FLUSH, null);

            }
        }
    };


}
