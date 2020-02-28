package com.simonojok19.techman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

public class EnrollActivity extends AppCompatActivity {
    private EditText studentName;
    private EditText studentClass;
    private EditText studentSchool;
    private EditText studentDistrict;
    private TextView studentActionTitle;
    private Bitmap teacherImage;
    private Intent intent;

    public static final String STUDENT_NAME = "com.simonojok19.techman.STUDENT_NAME";
    public static final String STUDENT_CLASS = "com.simonojok19.techman.STUDENT_CLASS";
    public static final String STUDENT_SCHOOL = "com.simonojok19.techman.STUDENT_SCHOOL";
    public static final String STUDENT_DISTRICT = "com.simonojok19.techman.STUDENT_DISTRICT";
    public static final String STUDENT_TEMPLATE_BYTES = "com.simonojok19.techman.STUDENT_TEMPLATE_BYTES";
    public static final String STUDENT_IMAGE_BYTES = "com.simonojok19.techman.STUDENT_IMAGE_BYTES";
    public static boolean NEW_TEACTER = true;

    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //

    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private EnrollActivity mainContext;

    public final static String TAG = "BioMini Sample";
    private TextView mLogView;
    private ScrollView mScrollLog = null;
    public IBioMiniDevice.TemplateData teacherCapturedTemplate;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
    private CaptureResponder mCaptureResponseDefault = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {
            log("onCapture : Capture successful!");

            log(((IBioMiniDevice) context).popPerformanceLog());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(capturedImage != null) {
                        ImageView iv = (ImageView) findViewById(R.id.fingerImage);
                        if(iv != null) {
                            iv.setImageBitmap(capturedImage);
                        }
                    }
                }
            });
            teacherCapturedTemplate = capturedTemplate;
            teacherImage = capturedImage;

            return true;
        }

        @Override
        public void onCaptureError(Object contest, int errorCode, String error) {
            log("onCaptureError : " + error + " ErrorCode :" + errorCode);
        }
    };

    private CaptureResponder mCaptureResponsePrev = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {

            Log.d("CaptureResponsePrev", String.format(Locale.ENGLISH , "captureTemplate.size (%d) , fingerState(%s)" , capturedTemplate== null? 0 : capturedTemplate.data.length, String.valueOf(fingerState.isFingerExist)));
            byte[] pImage_raw =null;
            if( (mCurrentDevice!= null && (pImage_raw = mCurrentDevice.getCaptureImageAsRAW_8() )!= null)) {
                Log.d("CaptureResponsePrev ", String.format(Locale.ENGLISH, "pImage (%d) , FP Quality(%d)", pImage_raw.length , mCurrentDevice.getFPQuality(pImage_raw, mCurrentDevice.getImageWidth(), mCurrentDevice.getImageHeight(), 2)));
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(capturedImage != null) {
                        ImageView iv = (ImageView) findViewById(R.id.fingerImage);
                        if(iv != null) {
                            iv.setImageBitmap(capturedImage);
                        }
                    }
                }
            });
            return true;
        }

        @Override
        public void onCaptureError(Object context, int errorCode, String error) {
            log("onCaptureError : " + error);
            log(((IBioMiniDevice)context).popPerformanceLog());
        }
    };
    synchronized public void log(final String msg)
    {
        Log.d(TAG, msg);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if( mLogView == null){
                    mLogView = (TextView) findViewById(R.id.log_text );
                }
                if(mLogView != null) {
                    mLogView.append(msg + "\n");
                    if(mScrollLog != null) {
                        mScrollLog.fullScroll(mScrollLog.FOCUS_DOWN);
                    }else{
                        Log.d("Log " , "ScrollView is null");
                    }
                }
                else {
                    Log.d("", msg);
                }
            }
        });
    }

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
                            log(String.format(Locale.ENGLISH ,"Initialized device count- BioMiniFactory (%d)" , mBioMiniFactory.getDeviceCount() ));
                        }
                    }
                    else{
                        Log.d(TAG, "permission denied for device"+ device);
                    }
                }
            }
        }
    };

    public void checkDevice(){
        if(mUsbManager == null) return;
        log("checkDevice");
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
        setContentView(R.layout.activity_enroll);

        mainContext = this;

        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;
        mLogView = findViewById(R.id.log_text);
        mScrollLog = findViewById(R.id.enroll_scroll_view);
        studentName = findViewById(R.id.student_name);
        studentClass = findViewById(R.id.student_class);
        studentSchool = findViewById(R.id.student_school);
        studentDistrict = findViewById(R.id.student_district);
        studentActionTitle = findViewById(R.id.action_title);
        intent = getIntent();

        if (Objects.equals(intent.getAction(), MainActivity.ACTION_VERIFY)) {
            studentName.setEnabled(false);
            studentClass.setEnabled(false);
            studentSchool.setEnabled(false);
            studentDistrict.setEnabled(false);
            studentName.setText(intent.getStringExtra(STUDENT_NAME));
            studentClass.setText(intent.getStringExtra(STUDENT_CLASS));
            studentSchool.setText(intent.getStringExtra(STUDENT_SCHOOL));
            studentDistrict.setText(intent.getStringExtra(STUDENT_DISTRICT));
        }

        findViewById(R.id.capture_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.fingerImage)).setImageBitmap(null);
                if(mCurrentDevice != null) {
                    //mCaptureOptionDefault.captureTimeout = (int)mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
                    mCurrentDevice.captureSingle(
                            mCaptureOptionDefault,
                            mCaptureResponseDefault,
                            true);
                }
            }
        });

        findViewById(R.id.start_capturing).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mCurrentDevice != null) {
                    BioMiniFactory mBioMiniFactory = new BioMiniFactory(getApplicationContext()) {
                        @Override
                        public void onDeviceChange(DeviceChangeEvent event, Object dev) {
                        }
                    };
                    IBioMiniDevice mCurrentDeivce = null;
                    // Make BioMiniFactory instance, and get device handler(IBioMiniDevice).
                    //mCaptureOptionDefault.captureTemplate =true;
                    mCaptureOptionDefault.captureImage=true;
                    //mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.ELOW;
                    mCurrentDevice.startCapturing(
                            mCaptureOptionDefault,
                            mCaptureResponsePrev);
                }
            }
        });

        findViewById(R.id.abort_capturing).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mCurrentDevice != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mCurrentDevice.abortCapturing();
                            int nRetryCount =0;
                            while(mCurrentDevice != null && mCurrentDevice.isCapturing()){
                                SystemClock.sleep(10);
                                nRetryCount++;
                            }
                            Log.d("AbortCapturing" , String.format(Locale.ENGLISH ,
                                    "IsCapturing return false.(Abort-lead time: %dms) " ,
                                    nRetryCount* 10));
                        }
                    }).start();
                }
            }
        });

        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }

        if( !mbUsbExternalUSBManager ){
            Button btn_checkDevice = (Button)findViewById(R.id.check_device);
            btn_checkDevice.setClickable(false);
            btn_checkDevice.setEnabled(false);
        }else{
            ((Button)findViewById(R.id.check_device)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkDevice();
                }
            });
        }


        restartBioMini();

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(STUDENT_IMAGE_BYTES, teacherImage);
                intent.putExtra(STUDENT_TEMPLATE_BYTES, teacherCapturedTemplate.data);
                intent.putExtra(STUDENT_NAME, studentName.getText().toString());
                intent.putExtra(STUDENT_CLASS, studentClass.getText().toString());
                intent.putExtra(STUDENT_SCHOOL, studentSchool.getText().toString());
                intent.putExtra(STUDENT_DISTRICT, studentDistrict.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    void handleDevChange(IUsbEventHandler.DeviceChangeEvent event, Object dev) {
        if (event == IUsbEventHandler.DeviceChangeEvent.DEVICE_ATTACHED && mCurrentDevice == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (mBioMiniFactory == null && cnt < 20) {
                        SystemClock.sleep(1000);
                        cnt++;
                    }
                    if (mBioMiniFactory != null) {
                        mCurrentDevice = mBioMiniFactory.getDevice(0);
                        Log.d(TAG, "mCurrentDevice attached : " + mCurrentDevice);
                        if (mCurrentDevice != null /*&& mCurrentDevice.getDeviceInfo() != null*/) {
                            log(" DeviceName : " + mCurrentDevice.getDeviceInfo().deviceName);
                            log("         SN : " + mCurrentDevice.getDeviceInfo().deviceSN);
                            log("SDK version : " + mCurrentDevice.getDeviceInfo().versionSDK);
                        }
                    }
                }
            }).start();
        } else if (mCurrentDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
            Log.d(TAG, "mCurrentDevice removed : " + mCurrentDevice);
            mCurrentDevice = null;
        }
    }

    void restartBioMini() {
        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }
        if( mbUsbExternalUSBManager ){
            mUsbManager = (UsbManager)getSystemService(Context.USB_SERVICE);
            mBioMiniFactory = new BioMiniFactory(mainContext, mUsbManager){
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
                    log("----------------------------------------");
                    log("onDeviceChange : " + event + " using external usb-manager");
                    log("----------------------------------------");
                    handleDevChange(event, dev);
                }
            };
            //
            mPermissionIntent = PendingIntent.getBroadcast(this,0,new Intent(ACTION_USB_PERMISSION),0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            registerReceiver(mUsbReceiver, filter);
            checkDevice();
        }else {
            mBioMiniFactory = new BioMiniFactory(mainContext) {
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
                    log("----------------------------------------");
                    log("onDeviceChange : " + event);
                    log("----------------------------------------");
                    handleDevChange(event, dev);
                }
            };
        }
        //mBioMiniFactory.setTransferMode(IBioMiniDevice.TransferMode.MODE2);
    }

    @Override
    protected void onDestroy() {
        if (mBioMiniFactory != null) {
            mBioMiniFactory.close();
            mBioMiniFactory = null;
        }
        if( mbUsbExternalUSBManager ){
            unregisterReceiver(mUsbReceiver);
        }
        super.onDestroy();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},  REQUEST_WRITE_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            log("permission granted");
        }
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState){
        requestPermission();
        super.onPostCreate(savedInstanceState);
    }

}
