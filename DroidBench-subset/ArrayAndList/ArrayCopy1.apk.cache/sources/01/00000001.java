package edu.mit.array_copy;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0000R.layout.activity_main);
        TelephonyManager mgr = (TelephonyManager) getSystemService("phone");
        String imei = mgr.getDeviceId();
        String[] array = {imei};
        String[] arraycopy = new String[1];
        System.arraycopy(array, 0, arraycopy, 0, 1);
        Log.i("DroidBench", arraycopy[0]);
    }
}