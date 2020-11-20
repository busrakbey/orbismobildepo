package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.konumsal.orbisozetmobil.OrtakUI.Bluetooth.BluetoothConnection;
import com.konumsal.orbisozetmobil.R;

import java.util.List;

import DataLayer.Ortak.ConfigData;
import ToolLayer.MessageBox;

/**
 * Created by konumsalpc5 on 21.10.2018.
 */



public class MyBlueToothReceiver extends BroadcastReceiver {

    private BluetoothConnection bc;
    void tryToConnect()
    {
            bc = new BluetoothConnection(BluetoothConnection.activity_, new BluetoothConnection.SearchListener() {

            @Override
            public void searchDevicesFinished(List<BluetoothDevice> bluetoothDevicesList) {
                //TODO show list of all founded devices - we will do it later
            }

            @Override
            public void searchDevicesStarted() {

            }

            @Override
            public void deviceConnected(BluetoothDevice connectedDevice) {
                if(connectedDevice != null){
                    Log.v("BAGLANTI","DIRILDI");
                }
            }
        },null);
    }


    Context context_;
    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        context_ = context;
        // When discovery finds a device
        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {

            BluetoothDevice device = intent
                    .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            Log.v("BAGLANDI","......");

        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
             tryToConnect();
            Log.v("BAGLANTI","KOPTU");
            final Dialog dialog = new Dialog(context_);
            dialog.setTitle("Cihaz Ayarı");
            dialog.setContentView(R.layout.bluetooth_device_dialog);


            final RadioButton caliper_rb = (RadioButton) dialog.findViewById(R.id.caliper_rb);
            final RadioButton other_rb = (RadioButton) dialog.findViewById(R.id.other_rb);


            final EditText cihaz_adi_edittext = (EditText) dialog.findViewById(R.id.device_name);
            final ConfigData configData = new ConfigData(context_);
            if(configData.getBluetoothDeviceName() != null)
                cihaz_adi_edittext.setText(configData.getBluetoothDeviceName());

            if(configData.getBLUETOOTHDEVICETYPE() != null)
            {
                if(configData.getBLUETOOTHDEVICETYPE().equals("0"))
                {
                    caliper_rb.setChecked(true);
                }
                else if(configData.getBLUETOOTHDEVICETYPE().equals("1"))
                {
                    other_rb.setChecked(true);
                }
            }

            dialog.show();

            Button declineButton = (Button) dialog.findViewById(R.id.bluetooth_kapat_btn);
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!caliper_rb.isChecked() && !other_rb.isChecked()) {
                        MessageBox.showAlert(context_,"Cihaz tipi seçiniz..",false);
                        return;
                    }

                    Log.v("kaydedilen","cihaz adi=>"+cihaz_adi_edittext.getText().toString());
                    configData.setBluetoothDeviceName(cihaz_adi_edittext.getText().toString());

                    if(caliper_rb.isChecked())
                    {
                        configData.setBLUETOOTHDEVICETYPE("0");
                        OrtakFunction.BLUETOOTH_DEVICE_TYPE = "0";
                    }
                    else if (other_rb.isChecked()) {
                        configData.setBLUETOOTHDEVICETYPE("1");
                        OrtakFunction.BLUETOOTH_DEVICE_TYPE = "1";
                    }

                    dialog.dismiss();

                    if(!cihaz_adi_edittext.getText().toString().equals(""))
                    {
                        boolean connecting = bc.connect(cihaz_adi_edittext.getText().toString());
                        if(connecting){
                            Toast.makeText(context_, "BAĞLANIYOR... ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context_, "BAGLANTI HATASI...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

           /* final ConfigData configData = new ConfigData(context);
            if(configData.getBluetoothDeviceName() != null) {

                boolean yeniden_baglan = true;
                boolean connecting = false;
                OrtakFunction.BLUETOOTH_STATE = "KOPTU";
                */

                /*tryToConnect();
                while(yeniden_baglan)

                    try {
                        Thread.sleep(5000);
                        connecting = bc.connect(configData.getBluetoothDeviceName());
                        if (connecting) {
                            yeniden_baglan = false;
                            Log.v("YENIDEN BAGLANDI", "=========");
                        } else {
                            Log.v("LOOKING FOR", "NEW CONNECTION");
                        }

                    } catch (InterruptedException e) {
                        Log.e("DEVICES", "ConnectedThread INTERRUPT: " + e);
                        e.printStackTrace();
                    }
                */
            //}


        }
    }
}
