package com.generalmobi.smart.helmet.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.generalmobi.smart.helmet.R;
import com.generalmobi.smart.helmet.ui.MainActivity;

public class AndroidBluetooth extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_PAIRED_DEVICE = 2;


    Button btnListPairedDevices;
    TextView stateBluetooth;
    BluetoothAdapter bluetoothAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnListPairedDevices = (Button)findViewById(R.id.listpaireddevices);

        stateBluetooth = (TextView)findViewById(R.id.bluetoothstate);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        CheckBlueToothState();
        btnListPairedDevices.setOnClickListener(btnListPairedDevicesOnClickListener);
    }



    private void CheckBlueToothState(){
        if (bluetoothAdapter == null){
            stateBluetooth.setText("Bluetooth NOT support");
            Toast.makeText(getApplicationContext(), "BT Not Support", Toast.LENGTH_SHORT).show();
        }else{
            if (bluetoothAdapter.isEnabled()){
                stateBluetooth.setText("Bluetooth is Enabled.");
                btnListPairedDevices.setEnabled(true);
                }
            else {
                stateBluetooth.setText("Bluetooth is Disabled!");
               
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Toast.makeText(getApplicationContext(), "BT Disabled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Button.OnClickListener btnListPairedDevicesOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent();
            intent.setClass(AndroidBluetooth.this, MainActivity.class);
            startActivityForResult(intent, REQUEST_PAIRED_DEVICE);
        }};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode == REQUEST_ENABLE_BT){
            CheckBlueToothState();
        }if (requestCode == REQUEST_PAIRED_DEVICE){
            if(resultCode == RESULT_OK){

            }
        }
    }
}
