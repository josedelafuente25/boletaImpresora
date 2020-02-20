package com.micropos.boletaelectronica.app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.micropos.boletaelectronica.R;

import java.util.ArrayList;
import java.util.List;

import HPRTAndroidSDK.HPRTPrinterHelper;

public class ActivityListaDispositivos extends Activity implements AdapterListaDispositivos.OnClickTextViewListener {

    private RecyclerView rvDispDisponibles;
    private RecyclerView.Adapter mAdapterDispDisponibles;
    private RecyclerView.LayoutManager lmDispDisponibles;

    public BluetoothAdapter mBluetoothAdapter;
    private Thread thread;
    private List<String> listDispDisponibles;
    private String direccionFisica;

    private Button btnEscanear;
    private ProgressBar pbDispDisponibles;
    private TextView tvDispDisponibles;
    private TextView tvConectando;

    private String nombreDisp;
    private String toothAddress = null;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            intent.putExtra("is_connected", ((msg.what == 0) ? "OK" : "NO"));
            intent.putExtra("BTAdress", toothAddress);
            setResult(HPRTPrinterHelper.ACTIVITY_CONNECT_BT, intent);
            finish();
        }
    };
    private Message msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);
        setResult(Activity.RESULT_CANCELED);

        tvConectando=findViewById(R.id.tv_conectando);
        tvConectando.setText(getResources().getString(R.string.conectando));
        tvConectando.setTextColor(Color.WHITE);
        tvConectando.setVisibility(View.INVISIBLE);

        pbDispDisponibles = findViewById(R.id.pb_dispositivos_disponibles);
        pbDispDisponibles.setVisibility(View.INVISIBLE);

        tvDispDisponibles = findViewById(R.id.tv_dispositivos_disponibles);
        tvDispDisponibles.setVisibility(View.INVISIBLE);

        btnEscanear = findViewById(R.id.btn_escanear);
        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnEscanear.getText().equals(getResources().getString(R.string.escanear))) {
                    tvDispDisponibles.setVisibility(View.VISIBLE);
                    pbDispDisponibles.setVisibility(View.VISIBLE);
                    btnEscanear.setText(R.string.detener);
                    direccionFisica = "";
                    doDiscovery();
                } else {
                    btnEscanear.setText(R.string.escanear);
                    pbDispDisponibles.setVisibility(View.INVISIBLE);
                    cancelDiscovering();
                }
            }
        });

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        rvDispDisponibles = findViewById(R.id.rv_dispositivos_disponibles);
        lmDispDisponibles = new LinearLayoutManager(this);
        rvDispDisponibles.setLayoutManager(lmDispDisponibles);

        listDispDisponibles = new ArrayList<>();
        mAdapterDispDisponibles = new AdapterListaDispositivos(listDispDisponibles, this);
        rvDispDisponibles.setAdapter(mAdapterDispDisponibles);

        String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intent.addAction(ACTION_PAIRING_REQUEST);
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceptor, intent);
    }

    public void doDiscovery() {
        if (mBluetoothAdapter.isDiscovering())
            mBluetoothAdapter.cancelDiscovery();

        int intStartCount = 0;
        while (!mBluetoothAdapter.startDiscovery() && intStartCount < 5) {
            intStartCount++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelDiscovering() {
        mBluetoothAdapter.cancelDiscovery();
    }

    public final BroadcastReceiver mReceptor = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device;
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                    if (device.getBluetoothClass().getMajorDeviceClass() == 1536) {
                        if (!direccionFisica.contains(device.getAddress())) {
                            direccionFisica += device.getAddress() + ",";
                            listDispDisponibles.add(device.getName() + "\n" + device.getAddress());
                            mAdapterDispDisponibles.notifyItemInserted(0);

                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter != null)
            mBluetoothAdapter.cancelDiscovery();
        if (thread != null) {
            Thread dummy = thread;
            thread = null;
            dummy.interrupt();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (thread != null) {
            Thread dummy = thread;
            thread = null;
            dummy.interrupt();
        }
    }

    @Override
    public void onClickTextView(CharSequence tvText) {

        try {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
                pbDispDisponibles.setVisibility(View.INVISIBLE);
                btnEscanear.setText(R.string.escanear);
                tvConectando.setVisibility(View.VISIBLE);
            }

            String info = tvText.toString();
            toothAddress = info.substring((info.indexOf("\n") + 1));
            nombreDisp=info.substring(0,info.indexOf("\n"));

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int portOpen = HPRTPrinterHelper.PortOpen("Bluetooth," + toothAddress);
                        msg = new Message();
                        msg.what = portOpen;
                        handler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
