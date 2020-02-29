package co.planetsystems.tela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //

    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private MainActivity mainContext;

    public final static String TAG = "Tela Log";
    private TextView mLogView;
    private ScrollView mScrollLog = null;
    private CardView backgroundCard;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
    private CaptureResponder mCaptureResponseDefault = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {
            log("Capture Fingerprint : Capture successful!");
            printState(getResources().getText(R.string.capture_single_ok));

            log(((IBioMiniDevice) context).popPerformanceLog());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundSuccess));
                    if(capturedImage != null) {
                        ImageView iv = (ImageView) findViewById(R.id.finger_image);
                        if(iv != null) {
                            iv.setImageBitmap(capturedImage);
                        }
                    }
                }
            });
            return true;
        }

        @Override
        public void onCaptureError(Object contest, int errorCode, String error) {
            backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundError));
            log("onCaptureError : " + error + " ErrorCode :" + errorCode);
            if( errorCode != IBioMiniDevice.ErrorCode.OK.value())
                printState(getResources().getText(R.string.capture_single_fail) + "("+error+")");
        }
    };

    private CaptureResponder mCaptureResponsePrev = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {

            Log.d("CaptureResponsePrev", String.format(Locale.ENGLISH , "captureTemplate.size (%d) , fingerState(%s)" , capturedTemplate== null? 0 : capturedTemplate.data.length, String.valueOf(fingerState.isFingerExist)));
            printState(getResources().getText(R.string.start_capture_ok));
            byte[] pImage_raw =null;
            if( (mCurrentDevice!= null && (pImage_raw = mCurrentDevice.getCaptureImageAsRAW_8() )!= null)) {
                Log.d("CaptureResponsePrev ", String.format(Locale.ENGLISH, "pImage (%d) , FP Quality(%d)", pImage_raw.length , mCurrentDevice.getFPQuality(pImage_raw, mCurrentDevice.getImageWidth(), mCurrentDevice.getImageHeight(), 2)));
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundSuccess));
                    if(capturedImage != null) {
                        ((ImageView) findViewById(R.id.finger_image)).setImageBitmap(null);
                        ImageView iv = (ImageView) findViewById(R.id.finger_image);
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
            backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundError));
            log("onCaptureError : " + error);
            log(((IBioMiniDevice)context).popPerformanceLog());
            if( errorCode != IBioMiniDevice.ErrorCode.OK.value())
                printState(getResources().getText(R.string.start_capture_fail));
        }
    };

    synchronized public void printState(final CharSequence str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ((TextView)findViewById(R.id.textStatus)).setText(str);
            }
        });

    }
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

    synchronized public void printRev(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ((TextView) findViewById(R.id.revText)).setText(msg);
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
                        Log.d(TAG, "Permission Denied"+ device);
                    }
                }
            }
        }
    };

    public void checkDevice(){
        if(mUsbManager == null) return;
        log("Check Fingerprint Scanner");
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
        setContentView(R.layout.activity_main_2);

        mainContext = this;

        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;
        backgroundCard = findViewById(R.id.card_background);

        findViewById(R.id.capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundCapturing));
                ((ImageView) findViewById(R.id.finger_image)).setImageDrawable(getDrawable(R.drawable.ic_fingerprint_black_24dp));
                if(mCurrentDevice != null) {
                    //mCaptureOptionDefault.captureTimeout = (int)mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
                    mCurrentDevice.captureSingle(
                            mCaptureOptionDefault,
                            mCaptureResponseDefault,
                            true);
                }
            }
        });

        findViewById(R.id.start_capture).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                backgroundCard.setCardBackgroundColor(getResources().getColor(R.color.colorBackgroundCapturing));
                ((ImageView) findViewById(R.id.finger_image)).setImageDrawable(getDrawable(R.drawable.ic_fingerprint_black_24dp));
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

        findViewById(R.id.abort_capture).setOnClickListener(new View.OnClickListener(){
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
                ((ImageView) findViewById(R.id.finger_image)).setImageDrawable(getDrawable(R.drawable.ic_fingerprint_black_24dp));
            }
        });

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

        printRev(""+mBioMiniFactory.getSDKInfo());

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
                    log("Fingerprint Scanner Changed : " + event + " using external usb-manager");
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
                    log("Fingerprint Scanner Changed : " + event);
                    log("----------------------------------------");
                    handleDevChange(event, dev);
                }
            };
        }
        //mBioMiniFactory.setTransferMode(IBioMiniDevice.TransferMode.MODE2);
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
                        printState(getResources().getText(R.string.device_attached));
                        Log.d(TAG, "Hardware Attached : " + mCurrentDevice);
                        if (mCurrentDevice != null /*&& mCurrentDevice.getDeviceInfo() != null*/) {
                            log(" DeviceName : " + mCurrentDevice.getDeviceInfo().deviceName);
                            log("         SN : " + mCurrentDevice.getDeviceInfo().deviceSN);
                            log("SDK version : " + mCurrentDevice.getDeviceInfo().versionSDK);
                        }
                    }
                }
            }).start();
        } else if (mCurrentDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
            printState(getResources().getText(R.string.device_detached));
            Log.d(TAG, "Fingerprint Scanner removed : " + mCurrentDevice);
            mCurrentDevice = null;
        }
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
            log("Permission Granted");
        }
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState){
        requestPermission();
        super.onPostCreate(savedInstanceState);
    }
}
