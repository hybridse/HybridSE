package org.arguslab.native_heap_modify;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    public static native void heapModify(Context context, Data data);

    static {
        System.loadLibrary("heap_modify");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0295R.layout.activity_main);
        if (checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            requestPermissions(new String[]{"android.permission.READ_PHONE_STATE"}, 1);
        }
    }

    private void leakImei() {
        Data data = new Data();
        heapModify(getApplicationContext(), data);
        Log.i("str", data.str);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        leakImei();
    }
}