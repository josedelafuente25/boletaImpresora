package com.micropos.boletaelectronica.modelos;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

public class Permisos {
    public static final int BLUETOOTH_CODE = 4;
    public static final int INTERNET_CODE = 2;
    public static final int READ_EXTERNAL_STORE_CODE = 1;
    public static final int READ_PHONE_STATE_CODE = 3;
    public static final int WRITE_EXTERNAL_STORE_CODE = 0;
    Activity activity;
    Context mContext;

    public Permisos(Activity activity2) {
        this.activity = activity2;
        this.mContext = activity2;
    }

    public boolean checkWriteExternalStore() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean checkReadExternalStore() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public boolean checkInternet() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.INTERNET") == 0;
    }

    public boolean checkReadPhoneState() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.READ_PHONE_STATE") == 0;
    }

    public boolean checkBluetooth() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.BLUETOOTH") == 0;
    }

    public void requestWriteExternalStore(int i) {
        String str = "android.permission.WRITE_EXTERNAL_STORAGE";
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            Toast.makeText(this.mContext.getApplicationContext(), "Se necesita permisos de escritura para grabar información y que la app funcione correctamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[]{str}, i);
    }

    public void requestReadExternalStore(int i) {
        String str = "android.permission.READ_EXTERNAL_STORAGE";
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            Toast.makeText(this.mContext.getApplicationContext(), "Se necesita permisos de lectura de la información de almacenamiento y que la app funcione correctamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[]{str}, i);
    }

    public void requestInternet(int i) {
        String str = "android.permission.INTERNET";
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            Toast.makeText(this.mContext.getApplicationContext(), "Se necesita permisos a internet para que la app funcione correctamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[]{str}, i);
    }

    public void requestReadPhoneState(int i) {
        String str = "android.permission.READ_PHONE_STATE";
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            Toast.makeText(this.mContext.getApplicationContext(), "Se necesita permisos al estado del telefono para que la app funcione correctamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[]{str}, i);
    }

    public void requestBluetooth(int i) {
        String str = "android.permission.BLUETOOTH";
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            Toast.makeText(this.mContext.getApplicationContext(), "Se necesita permisos a bluetooth para que la app funcione correctamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[]{str}, i);
    }

}
