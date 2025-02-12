package org.arguslab.native_leak;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    public static native void send(String str);

    static {
        System.loadLibrary("leak");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0009R.layout.activity_main);
        if (checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            requestPermissions(new String[]{"android.permission.READ_PHONE_STATE"}, 1);
        }
    }

    private void leakImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return;
        }
        send(telephonyManager.getDeviceId());
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        leakImei();
    }
}