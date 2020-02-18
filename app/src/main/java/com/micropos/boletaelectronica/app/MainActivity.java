package com.micropos.boletaelectronica.app;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.micropos.boletaelectronica.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import HPRTAndroidSDK.HPRTPrinterHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    private final int NUM_MAX_CARACTERES_POR_VALOR = 16;
    private final int NUM_MAX_CARACTERES_VALOR_TOTAL = 18;
    private final int NUM_MAX_CARACTERES_POR_LINEA_EN_IMPRESORA = 32;

    private Button btnCero;
    private Button btnUno;
    private Button btnDos;
    private Button btnTres;
    private Button btnCuatro;
    private Button btnCinco;
    private Button btnSeis;
    private Button btnSiete;
    private Button btnOcho;
    private Button btnNueve;
    private ImageButton imgBtnBorrar;
    private Button btnImprimir;
    private Button btnConectar;
    private TextView tvValor;
    private TextView tvEstadoConexion;

    BluetoothAdapter mBluetoothAdapter;

    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConectar = findViewById(R.id.btn_conectar);
        btnConectar.setOnClickListener(this);
        tvEstadoConexion = findViewById(R.id.tv_estado_conexion);
        tvEstadoConexion.setText(getResources().getString(R.string.desconectado));
        tvEstadoConexion.setTextColor(getResources().getColor(R.color.colorRed));

        btnImprimir = findViewById(R.id.btn_imprimir);
        btnImprimir.setOnClickListener(this);
        btnImprimir.setEnabled(false);
        btnImprimir.setAlpha(0.25f);

        imgBtnBorrar = findViewById(R.id.btn_borrar);
        imgBtnBorrar.setOnClickListener(this);

        btnCero = findViewById(R.id.btn_cero);
        btnCero.setOnClickListener(this);

        tvValor = findViewById(R.id.tv_valor);
        valor = "";
        tvValor.setText("$0");

        btnUno = findViewById(R.id.btn_uno);
        btnUno.setOnClickListener(this);
        btnDos = findViewById(R.id.btn_dos);
        btnDos.setOnClickListener(this);
        btnTres = findViewById(R.id.btn_tres);
        btnTres.setOnClickListener(this);
        btnCuatro = findViewById(R.id.btn_cuatro);
        btnCuatro.setOnClickListener(this);
        btnCinco = findViewById(R.id.btn_cinco);
        btnCinco.setOnClickListener(this);
        btnSeis = findViewById(R.id.btn_seis);
        btnSeis.setOnClickListener(this);
        btnSiete = findViewById(R.id.btn_siete);
        btnSiete.setOnClickListener(this);
        btnOcho = findViewById(R.id.btn_ocho);
        btnOcho.setOnClickListener(this);
        btnNueve = findViewById(R.id.btn_nueve);
        btnNueve.setOnClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        encenderBluetooth();
    }

    private void encenderBluetooth() {
        if (mBluetoothAdapter != null)
            if (!mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.enable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBluetoothAdapter != null)
            if (mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.disable();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_conectar) {
            Intent serverIntent = new Intent(this, ActivityListaDispositivos.class);
            startActivityForResult(serverIntent, HPRTPrinterHelper.ACTIVITY_CONNECT_BT);
        }

        if (v.getId() == R.id.btn_borrar) {

            if (valor.length() > 0) {
                //Quitar el último valor
                valor = valor.substring(0, valor.length() - 1);

                if (valor.length() == 0)
                    tvValor.setText("$0");
                else
                    tvValor.setText(formatearValor(valor));

            }
        }

        if (v.getId() == R.id.btn_imprimir) {

            try {
                imprimir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (valor.length() < NUM_MAX_CARACTERES_POR_VALOR) {

            switch (v.getId()) {
                case R.id.btn_cero:
                    if (valor.length() > 0) {
                        valor += "0";
                        tvValor.setText(formatearValor(valor));
                    }
                    break;

                case R.id.btn_uno:
                    valor += "1";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_dos:
                    valor += "2";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_tres:
                    valor += "3";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_cuatro:
                    valor += "4";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_cinco:
                    valor += "5";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_seis:
                    valor += "6";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_siete:
                    valor += "7";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_ocho:
                    valor += "8";
                    tvValor.setText(formatearValor(valor));
                    break;

                case R.id.btn_nueve:
                    valor += "9";
                    tvValor.setText(formatearValor(valor));
                    break;
            }
        }

    }

    private String formatearValor(String str) {

        if (str.length() > 3) {
            StringBuilder strBuilder;

            strBuilder = new StringBuilder(str);
            for (int i = str.length() - 3; i > 0; i -= 3)
                strBuilder.insert(i, ".");

            return "$" + strBuilder.toString();

        } else {
            return "$" + str;
        }

    }

    private void imprimir() throws Exception {

        String texto = "";

        texto += "R.U.T: " + "12.345.678-9\n";
        texto += "BOLETA ELECTRONICA N " + "99.999\n";
        texto += "SII - TEMUCO\n\n";

        texto += "Giro: \n";
        texto += "Direccion: \n";
        texto += "Fono: \n";
        texto += "Correo: \n\n";

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy\nHH:mm:ss");
        Date date = new Date();
        texto += "Fecha de Emision: " + format.format(date) + "\n\n";
        HPRTPrinterHelper.PrintText(texto);

        texto = "";
        texto += "Total: " + formatearValor(valor) + "\n\n";
        HPRTPrinterHelper.PrintText(texto, 0, 2, 0);

        texto = "";
        texto += "Timbre Electronico SII\n";
        texto += "Res 80 de 2014 Verfique Documento\n";
        texto += "www.sii.cl\n";
        texto += "www.micropos.cl/eboleta.html\n\n\n";
        HPRTPrinterHelper.PrintText(texto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            String strConectado;
            switch (resultCode) {

                case HPRTPrinterHelper.ACTIVITY_CONNECT_BT:
                    strConectado = data.getExtras().getString("is_connected");
                    if (strConectado.equals("OK")) {
                        tvEstadoConexion.setText(R.string.conectado);
                        tvEstadoConexion.setTextColor(getResources().getColor(R.color.colorGreen));
                        btnImprimir.setEnabled(true);
                        btnImprimir.setAlpha(1);
                        break;
                    }
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}