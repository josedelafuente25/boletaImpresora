package com.micropos.boletaelectronica.app;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import com.micropos.boletaelectronica.R;

public class MainActivityNavDrawer extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_boleta, R.id.nav_conf, R.id.nav_boleta_calculadora, R.id.nav_rcof
                , R.id.nav_solicitar_folios)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        encenderBluetooth();
    }

    private void encenderBluetooth() {
        if (mBluetoothAdapter != null)
            if (!mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.enable();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter != null)
            if (mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.disable();
    }


}
