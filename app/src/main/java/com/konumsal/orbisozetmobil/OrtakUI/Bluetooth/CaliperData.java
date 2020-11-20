package com.konumsal.orbisozetmobil.OrtakUI.Bluetooth;

/**
 * Created by isahin on 17.2.2017.
 */

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.konumsal.orbisozetmobil.OrtakUI.OrtakFunction;

import java.util.Date;


/**
 * Created by Matej on 4.5.2015.
 */
public class CaliperData extends Application {


    public static BluetoothHandler bluetoothHandler;
    private static CaliperMessages caliperMessages;
    private static Activity activity;
    //message send delay
    static int LONG_DELAY = 1000;
    static int SHORT_DELAY = 200;

    public CaliperData(Activity activity, CaliperMessages caliperMessages){
        this.caliperMessages = caliperMessages;
        this.activity = activity;
        bluetoothHandler = new BluetoothHandler();
    }

    public interface CaliperMessages {
        void deviceJoyLeft(int msg);
        void deviceJoyRight(int msg);
        void deviceJoyUp(int msg);
        void deviceJoyDown(int msg);
        void deviceJoyClick(int msg);
        void deviceFrontClick(double msg);
        void deviceBackClick(int msg);
        void deviceRef(int msg);
       // void isDisconnected();
    }

    public static class BluetoothHandler extends Handler {



        long lastJoyDownTime;
        long lastJoyUpTime;
        long lastJoyLeftTime;
        long lastJoyRightTime;



        @Override
        public void handleMessage(Message message)
        {
            ///if(activity.deviceActive) {
            switch (message.what) {
                // joy up
                case BluetoothConnection.JOY_UP:
                    joyUp(message);
                    break;
                // joy down
                case BluetoothConnection.JOY_DOWN:
                    joyDown(message);
                    break;
                // joy left
                case BluetoothConnection.JOY_LEFT:
                    joyLeft(message);
                    break;
                // joy right
                case BluetoothConnection.JOY_RIGHT:
                    joyRight(message, false);
                    break;
                // joy click
                case BluetoothConnection.JOY_CLICK:
                    double time = (new Date()).getTime();
                    Log.i("BluetoothConnection", "JOY_CLICK OK");
                    caliperMessages.deviceJoyClick(message.arg1);

                    break;
                // front click
                case BluetoothConnection.FRONT_CLICK:
                    time = (new Date()).getTime();
                    Log.i("BluetoothConnection", "FRONT CLICK OK");
                    if(OrtakFunction.BLUETOOTH_DEVICE_TYPE.equals("1"))
                        caliperMessages.deviceFrontClick(message.arg1);
                    else if(OrtakFunction.BLUETOOTH_DEVICE_TYPE.equals("0"))
                        caliperMessages.deviceFrontClick(setCaliperConversion(message.arg1,1));

                    break;
                // back click
                case BluetoothConnection.BACK_CLICK:
                    Log.i("BluetoothConnection", "BACK_CLICK: " + (new Date()).getTime());
                    time = (new Date()).getTime();
                    Log.i("BluetoothConnection", "BACK_CLICK OK");
                    caliperMessages.deviceBackClick(message.arg1);
                    break;
                // ref
                case BluetoothConnection.REF:
                    //Calliper need to pass reference point
                    caliperMessages.deviceRef(message.arg1);
                    break;
                case BluetoothConnection.TOAST:
                    Toast.makeText(activity, (String)message.obj, Toast.LENGTH_LONG).show();
                    break;
                case BluetoothConnection.BAT:
                    Toast toast = Toast.makeText(activity, "low_battery", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;

            }
        }
    }

    private static void joyDown(Message message) {
        long time;
        boolean doubleClick;
        time = (new Date()).getTime();
        Log.i("BluetoothConnection", "JOY_DOWN OK");
        caliperMessages.deviceJoyDown(message.arg1);

    }

    private static void joyUp(Message message) {
        long time = (new Date()).getTime();
        Log.i("BluetoothConnection", "JOY_UP OK");
        caliperMessages.deviceJoyUp(message.arg1);
    }

    private static boolean isDoubleClick(long time, long lastTime) {
        boolean res = false;
        //if difference is lower then delay time
        long diff = time - lastTime;
        Log.e("isDoubleClick", "Double click: " + diff);
        if(diff < 330){
            //Toast.makeText(activity, "Double click!" + diff, Toast.LENGTH_SHORT).show();
            res = true;
        }
        return res;
    }

    private static void joyRight(Message message, boolean forceDisableDoubleClick) {
        long time;
        time = (new Date()).getTime();
        Log.i("BluetoothConnection", "JOY_RIGHT OK");
        caliperMessages.deviceJoyRight(message.arg1);
    }

    private static void joyLeft(Message message) {
        long time;
        time = (new Date()).getTime();
        Log.i("BluetoothConnection", "JOY_LEFT OK");
        caliperMessages.deviceJoyLeft(message.arg1);
    }

    private static boolean isDelay(long timeNow, long lastTime, int delay, String message) {
        boolean res = false;
        //if difference is lower then delay time, activate delay - don't send message further
        if(timeNow - lastTime < delay){
            Log.e("BluetoothConnection", message + " to many requests!!!");
            res = true;
        }
        Log.i("BluetoothConnection", message + ": " + timeNow + " - " + lastTime + " = " + (timeNow - lastTime));
        return res;
    }
    //endregion

    public static double setCaliperConversion(int value, int caliperConversion) {
        /*
        caliper_conversion: 1 - meter
        caliper_conversion: 2 - centimeter
        caliper_conversion: 3 - millimeter
        caliper_conversion: 4 - foot
        caliper_conversion: 5 - inch
        */
        //mm to cm
        double res = value / 10.0;
        //to other
        switch (caliperConversion) {
            case 1:
                //to meter
                res /= 100;
                break;
            case 2:
                //to centimeter
                break;
            case 3:
                //to millimeter
                res *= 10;
                break;
            case 4:
                //to foot
                res /= 30.48;
                break;
            case 5:
                //to inch
                res /= 2.54;
                break;
        }
        return res;
    }
}
