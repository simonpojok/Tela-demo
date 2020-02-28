package com.simonojok19.techman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class FingerPrintActivity extends AppCompatActivity {
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //

    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
    private CaptureResponder mCaptureResponseDefault = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FingerPrintActivity.this, "Success in Capture", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        @Override
        public void onCaptureError(Object contest, int errorCode, String error) {
            Toast.makeText(FingerPrintActivity.this, "Error in Capture", Toast.LENGTH_SHORT).show();
        }
    };

    private CaptureResponder mCaptureResponsePrev = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FingerPrintActivity.this, "Success in Capture 2", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        @Override
        public void onCaptureError(Object context, int errorCode, String error) {
            Toast.makeText(FingerPrintActivity.this, "Error in Capture 2", Toast.LENGTH_SHORT).show();
        }
    };

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver(){
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            if(ACTION_USB_PERMISSION.equals(action)){
                synchronized(this){
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)){
                        if(device != null){
                            if( mBioMiniFactory == null) return;
                            mBioMiniFactory.addDevice(device);
                            Toast.makeText(FingerPrintActivity.this, "Device Connected well", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(FingerPrintActivity.this, "No device connected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    public void checkDevice(){
        if(mUsbManager == null) return;
        HashMap<String , UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIter = deviceList.values().iterator();
        while(deviceIter.hasNext()){
            UsbDevice _device = deviceIter.next();
            if( _device.getVendorId() ==0x16d1 ){
                //Suprema vendor ID
                mUsbManager.requestPermission(_device , mPermissionIntent);
            }else{
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
    }
}
