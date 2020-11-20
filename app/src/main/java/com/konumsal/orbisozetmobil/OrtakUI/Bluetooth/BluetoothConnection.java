package com.konumsal.orbisozetmobil.OrtakUI.Bluetooth;

/**
 * Created by isahin on 17.2.2017.
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.konumsal.orbisozetmobil.OrtakUI.OrtakFunction;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * Created by Matej on 18.6.2015.
 */
public class BluetoothConnection {

    public static final String APP_UUID = "00001101-0000-1000-8000-00805f9b34fb";

    public BluetoothAdapter mBluetoothAdapter;
    public HashMap<String, BluetoothDevice> bluetoothDevices;
    public List<BluetoothDevice> bluetoothDevicesList;
    public Activity activity;

    public static ConnectedThread bluetoothConnectedThread;
    public static ConnectThread bluetoothConnectionThread;
    public BluetoothDevice connectedDevice;

    public boolean deviceDetected = false;

    public static final int JOY_UP = 1;
    public static final int JOY_DOWN = 2;
    public static final int JOY_LEFT = 3;
    public static final int JOY_RIGHT = 4;
    public static final int JOY_CLICK = 5;
    public static final int FRONT_CLICK = 6;
    public static final int BACK_CLICK = 7;
    public static final int TOAST = 8;
    public static final int BAT = 21;
    public static final int REF = 22;

    String messageText = "";
    int byteCounter = 0;
    private SearchListener searchListener;
    private String deviceName;
    private boolean enableBluetooth;
    private boolean deviceConnected = false;
    public static Activity activity_;

    public BluetoothConnection(final Activity activity, SearchListener searchListener, CaliperData.CaliperMessages caliperMessages) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.activity = activity;
        activity_ = activity;
        this.searchListener = searchListener;
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        //activity.registerReceiver(mReceiver, filter);


        new CaliperData(activity, caliperMessages);
    }

    public boolean connect(String deviceName) {
        boolean connecting = false;
        if(deviceName != null && deviceName.length() > 0) {
            try {
                List<BluetoothDevice> pairedDevices = getPairedDevices(deviceName);
                Log.v("acik devices","=>"+pairedDevices.size());

                if (pairedDevices.size() > 0) {
                    Log.v("acik devices","=>"+pairedDevices.get(0).getName());
                    connecting = true;
                    resetConnections();
                    connectToDevice(pairedDevices.get(0));
                }
            } catch (Exception ex) {
                Log.e("connect", ex.toString());
            }
        }
        return connecting;
    }

    public interface SearchListener {
        void searchDevicesFinished(List<BluetoothDevice> bluetoothDevicesList);
        void searchDevicesStarted();
        void deviceConnected(BluetoothDevice connectedDevice);
    }

    public void searchBluetoothDevices(Activity activity, boolean enableBluetooth, SearchListener searchListener, String deviceName) {

        this.enableBluetooth = enableBluetooth;
        this.deviceName = deviceName;
        bluetoothDevices = new HashMap<>();
        bluetoothDevicesList = new ArrayList<>();
        this.activity = activity;
        this.searchListener = searchListener;

        // declare bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Log.i("DEVICES", "searchBluetoothDevices(activity)");

        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Log.i("DEVICES", "Device does not support Bluetooth");

            Toast.makeText(activity, "Bluetooth not supported", Toast.LENGTH_LONG).show();
        } else {
            // check if Bluetooth is enabled
            if (!mBluetoothAdapter.isEnabled()) // Bluetooth is disabled
            {
                Log.i("DEVICES", "Bluetooth is disabled");

                if(activity != null && this.enableBluetooth) {
                    // show popup to enable bluetooth
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    activity.startActivityForResult(enableBtIntent, 1001);
                }
            } else {
                Log.i("DEVICES", "searchBluetoothDevices(activity activity) - 2 : REGISTER DEVICE");

                bluetoothDevices.clear();

                // Register the BroadcastReceiver
                IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                IntentFilter filter3 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                IntentFilter filter4 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
                IntentFilter filter5 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
                IntentFilter filter6 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);


                activity.registerReceiver(mReceiver, filter1);
                activity.registerReceiver(mReceiver, filter2);
                activity.registerReceiver(mReceiver, filter3);// Don't forget to unregister during onDestroy
                activity.registerReceiver(mReceiver, filter4);
                activity.registerReceiver(mReceiver, filter5);
                activity.registerReceiver(mReceiver, filter6);

                // Start for searching the devices
                mBluetoothAdapter.startDiscovery();
            }
        }
    }


    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            Log.v("INTERRUPTED2","KOOOOO==>"+intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1));
            if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF)
            {
                Log.v("INTERRUPTED","KOOOOO");
            }

                // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the device to show in a ListView
                if(device != null && device.getName() != null){
                    if(deviceName == null){
                        deviceName = "";
                    }
                    Log.e("DEVICE NAME: ", device.getName().toUpperCase());
                    if (device.getName().toUpperCase().startsWith(deviceName.toUpperCase()) && !bluetoothDevices.containsKey(device.getAddress())) {
                        bluetoothDevices.put(device.getAddress(), device);
                        bluetoothDevicesList.add(device);
                    }
                    Log.i("DEVICES", device.getName() + "\n" + device.getAddress());
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction())) {
                if (searchListener != null)
                    searchListener.searchDevicesFinished(bluetoothDevicesList);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(intent.getAction())) {
                if (searchListener != null)
                    searchListener.searchDevicesStarted();
            } else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(intent.getAction())) {
                //Device is now connected
                Log.v("DEVICES", "CONNECTED1");
                deviceConnected = true;
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(intent.getAction())) {
                //Device is about to disconnect
                Log.v("DEVICES", "DISCONNECTED1");
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(intent.getAction())) {
                //Device has disconnected
                deviceConnected = false;
                Log.v("DEVICES", "DISCONNECTED2");
            }


        }
    };

    public void unregisterReceiver(Activity activity) {
        if (mReceiver != null)
            activity.unregisterReceiver(mReceiver);
    }


    public void connectToDevice(BluetoothDevice bluetoothDevice) {
        bluetoothConnectionThread = new ConnectThread(bluetoothDevice);
        Log.i("DEVICES", "connectToDevice() - 1");
        bluetoothConnectionThread.start();
    }

    public class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {

            Log.i("DEVICES", "ConnectThread - 1");

            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmpSocket = null;
            mmDevice = device;

            Log.i("DEVICES", "ConnectThread - 2");

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                UUID uuid = UUID.fromString(APP_UUID);
                Log.i("DEVICES", "ConnectThread - 3");

                if(device == null)
                    Log.i("DEVICES", "ConnectThread - 4 : DEVICE NULL");

                // MY_UUID is the app's UUID string, also used by the server code
                Log.v("uid ","=>"+uuid);
                tmpSocket = device.createInsecureRfcommSocketToServiceRecord(uuid); // only insecure is working
                //tmp = device.createRfcommSocketToServiceRecord(MY_UUID);


                Log.d("BLUETOOTH APP UUID", uuid.toString());

            } catch (IOException e) {
                Log.e("BluetoothConnection", e.getMessage());
            }
            mmSocket = tmpSocket;
        }

        public void run() {

            Log.i("DEVICES", "ConnectThread - 5 : RUN");

            // Cancel discovery because it will slow down the connection
            if(mBluetoothAdapter.isDiscovering())
                mBluetoothAdapter.cancelDiscovery();

            Log.i("DEVICES", "ConnectThread - 6 : SLEEP AND TRY CONNECT");

            try {

                //sleep for code recommendations because it is too fast
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Log.i("DEVICES", "ConnectThread -17 : mmSocket.disconectedconnect()");
                    e.printStackTrace();
                }

                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception

                Log.i("DEVICES", "ConnectThread - 7 : mmSocket.connect()");
                if (!mmSocket.isConnected()) {
                    Log.i("DEVICES", "ConnectThread - 8 : IS NOT CONNECTED");
                    mmSocket.connect();
                }

            } catch (IOException connectException) {
                Log.d("BLUETOOTH CONNECTION", "Unable to connect to device: " + mmDevice.getName() + "; " + mmDevice.getAddress());
                Log.e("BLUETOOTH ERROR1", connectException.toString());
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "device_not_connected", Toast.LENGTH_SHORT).show();
                            if(searchListener != null){
                                searchListener.deviceConnected(null);
                            }
                            deviceConnected = false;
                            Log.v("DEVICE","DISCONECCTED3");
                        }
                    });
                }
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("DEVICES", "ConnectThread - 9 : ERROR CLOSING" + e);
                }
                return;
            }

            Log.i("DEVICES", "ConnectThread - 10 : manageConnectedSocket(mmSocket)");
            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket(mmSocket);
            Log.i("DEVICES", "ConnectThread - 11 : manageConnectedSocket(mmSocket) AFTER");
            connectedDevice = mmDevice;
        }

        // Will cancel an in-progress connection, and close the socket
        public void cancel() {

            Log.i("DEVICES", "DEVICE CLOSE - 1");

            if (mmSocket.isConnected()) {
                try {
                    mmSocket.close();
                } catch (Exception e) {
                    Log.e("DEVICES", "ConnectThread - 12 : ERROR CLOSING" + e);
                }
            }
            Log.i("DEVICES", "DEVICE CLOSE - 2");
        }
    }

    public void manageConnectedSocket(BluetoothSocket mmSocket) {
        bluetoothConnectedThread = new ConnectedThread(mmSocket);
        bluetoothConnectedThread.start();
    }

    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private InputStream mmInStream;
        private OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.i("DEVICES", "ConnectThread - 27 : mmSocket.disconnect()");
                Log.e("err", e.toString());
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {

            Log.i("BLUETOOTH SAMPLING", "Start sampling for inputs");
            Log.i("DEVICES", "CONNECTED");

            if (connectedDevice != null) {
                //LoggingBaseActivity.st.setLastConnectedDevice(connectedDevice);
                if(activity != null){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(activity, "device_connected", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            searchListener.deviceConnected(connectedDevice);
                            deviceConnected = true;
                        }
                    });
                }
            }
            byte[] buffer = new byte[6];

            deviceDetected = true;

            // Keep listening to the InputStream until an exception occurs
            while (true) {

                if (mmSocket != null  && mmInStream != null && mmSocket.isConnected())
                {

                    try {

                        // Read from the InputStream
                        if (mmInStream.available() > 0) {

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                Log.e("DEVICES", "ConnectedThread INTERRUPT: " + e);
                                e.printStackTrace();
                            }

                            mmInStream.read(buffer);
                            dataReader(buffer);
                            mmInStream.reset();
                        }

                    } catch (IOException e) {
                        Log.e("DEVICES", "ConnectedThread IO: " + e);
                    }
                }

                //*/
            }


        }

        // Call this from the main activity to send data to the remote device
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Log.e("BluetoothConnection", "Exception during write", e);
            }
        }


        // Call this from the main activity to shutdown the connection
        public void cancel() {


            Log.i("DEVICES", "DEVICE CLOSE - 3");
            if (mmInStream != null) {
                try {mmInStream.close();} catch (Exception e) {
                    Log.e("err", e.toString());
                }
                mmInStream = null;
            }
            Log.i("DEVICES", "DEVICE CLOSE - 4");
            if (mmOutStream != null) {
                try {mmOutStream.close();} catch (Exception e) {
                    Log.e("err", e.toString());
                }
                mmOutStream = null;
            }

            Log.i("DEVICES", "DEVICE CLOSE - 5");
            if (mmSocket.isConnected()) {
                try {
                    mmSocket.close();
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("DEVICES", "ConnectedThread - 5.5 : ERROR CLOSING" + e);
                }
            }
            Log.i("DEVICES", "DEVICE CLOSE - 6");

        }
    }

    private void dataReader(Object obj) {

        // create byte array
        byte[] buffer = (byte[]) obj;
        int bufferSize = buffer.length; //message.arg1;


        // construct a string from the valid bytes in the buffer ()
        messageText = messageText + new String(buffer, 0, bufferSize);

        // prepare int array
        int dataInt[] = new int[6];

        // transform character to int value and fill array
        for (int j = 0; j < bufferSize; j++) {
            dataInt[byteCounter] = buffer[j];
            Log.i("BT INT" + byteCounter, dataInt[byteCounter] + "");
            byteCounter++;

            if (byteCounter > 5)
                break;
        }

        Log.i("BT LENGTH", bufferSize + "");

        // if whole message was received
        if (messageText.length() >= 5) {

            try {
                Log.i("BT", messageText);
                //Toast.makeText(MainActivity.this, messageText, Toast.LENGTH_SHORT).show();

                String newTextLogString = messageText;


                if(OrtakFunction.BLUETOOTH_DEVICE_TYPE.equals("1")) {
                    if (!messageText.startsWith("O")) {
                        Log.v("not olcum", "ok");
                        Log.v("****not***", "basta - geldigi icin substring 2 yapildi");
                        String sbprint = messageText.replaceAll("(\\r|\\n)", "");
                        Log.v("sbprint","=>"+sbprint);
                        float res = Float.valueOf(sbprint);
                        int result_float = Math.round(res / 100);
                        //res = res / 100;
                        Log.v("res float", "=>" + result_float);
                        CaliperData.bluetoothHandler.obtainMessage(FRONT_CLICK, result_float, 0, null).sendToTarget();
                        newTextLogString += "; " + res + "mm";
                    }
                }

                else {
                    if (messageText.endsWith("REF")) {
                        newTextLogString += "; REF";
                        CaliperData.bluetoothHandler.obtainMessage(REF, 0, 0, null).sendToTarget();
                    } else if (messageText.endsWith("BAT")) {
                        newTextLogString += "; BAT";
                        CaliperData.bluetoothHandler.obtainMessage(BAT, 0, 0, null).sendToTarget();
                    } else if (messageText.startsWith("P")) {
                        //if(currentActivity != null)
                        //    currentActivity.deviceFrontClick((int) Long.parseLong(messageText.substring(1), 16));
                        CaliperData.bluetoothHandler.obtainMessage(FRONT_CLICK, (int) Long.parseLong(messageText.substring(1), 16), 0, null).sendToTarget();
                        newTextLogString += "; " + Long.parseLong(messageText.substring(1), 16) + "mm";
                    } else if (messageText.startsWith("S")) {
                        CaliperData.bluetoothHandler.obtainMessage(BACK_CLICK, (int) Long.parseLong(messageText.substring(1), 16), 0, null).sendToTarget();
                        newTextLogString += "; MEASUREMENT";
                    } else if (messageText.startsWith("Y")) {

                        if (dataInt.length >= 6) {
                            switch (dataInt[5]) {
                                case -1:
                                    newTextLogString += "; NEUTRAL";
                                    break;
                                case -2:
                                    CaliperData.bluetoothHandler.obtainMessage(JOY_UP, 0, 0, null).sendToTarget();
                                    newTextLogString += "; UP";
                                    break;
                                case -3:
                                    CaliperData.bluetoothHandler.obtainMessage(JOY_DOWN, 0, 0, null).sendToTarget();
                                    newTextLogString += "; DOWN";
                                    break;
                                case -5:
                                    CaliperData.bluetoothHandler.obtainMessage(JOY_LEFT, 0, 0, null).sendToTarget();
                                    newTextLogString += "; LEFT";
                                    break;
                                case -17:
                                    CaliperData.bluetoothHandler.obtainMessage(JOY_RIGHT, 0, 0, null).sendToTarget();
                                    newTextLogString += "; RIGHT";
                                    break;
                                case -9:
                                    CaliperData.bluetoothHandler.obtainMessage(JOY_CLICK, 0, 0, null).sendToTarget();
                                    newTextLogString += "; CLICK";
                                    break;
                            }
                        } else {
                            newTextLogString += dataInt.length;
                        }
                    }
                }

                //String textLogString = textLog.getText().toString();
                //newTextLogString += "\n" + textLogString;

                Log.i("BLUETOOTH OUT", "TEXT: " + newTextLogString);

                //textLog.setText(newTextLogString);

            }catch(Exception e){
                Log.e("BLUETOOTH", "Exception: " + e.toString());
            }

            // Clear message
            messageText = "";
            byteCounter = 0;
        }

    }

    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;

        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (!isConnected()) return;
            r = bluetoothConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    public boolean isConnected() {
        //TODO fix
        return true;
    }

    public BluetoothDevice findBluetoothDeviceByAddress(String address){
        BluetoothDevice result = null;
        if(bluetoothDevicesList != null && address != null){
            for(BluetoothDevice bluetoothDevice : bluetoothDevicesList){
                if(bluetoothDevice != null && bluetoothDevice.getAddress() != null && bluetoothDevice.getAddress().equals(address)){
                    result = bluetoothDevice;
                }
            }
        }
        return result;
    }

    public List<BluetoothDevice> getPairedDevices(String name) {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> pairedDevicesList = new ArrayList<>();
        for (BluetoothDevice bt : pairedDevices) {
            if (bt != null && bt.getName() != null && bt.getName().toUpperCase().contains(name)) {
                pairedDevicesList.add(bt);
            }
        }
        return pairedDevicesList;
    }

    public boolean isDeviceConnected(){
        return deviceConnected;
    }

    public static void resetConnections(){
        if (bluetoothConnectedThread != null) {
            bluetoothConnectedThread.cancel();
        }

        if (bluetoothConnectionThread != null) {
            bluetoothConnectionThread.cancel();
        }
    }
}
