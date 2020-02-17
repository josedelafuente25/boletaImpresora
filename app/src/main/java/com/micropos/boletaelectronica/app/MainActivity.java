package com.micropos.boletaelectronica.app;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.micropos.boletaelectronica.R;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Button btnMulitplicar;
    private Button btnBorrar;
    private Button btnImprimir;
    private Button btnAgregar;
    private Button btnEliminar;
    private Button btnConectar;
    private TextView tvValor;
    private TextView tvListaValores;
    private TextView tvTotal;
    private TextView tvEstadoConexion;
    private ArrayList<String> listaValoresIngresados;
    private ScrollView svListaValoresIngresados;

    BluetoothAdapter mBluetoothAdapter;

    private long total;
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

        tvTotal = findViewById(R.id.tv_total);
        total = 0;
        tvTotal.setText(String.valueOf(total));

        svListaValoresIngresados = findViewById(R.id.sv_lista_valores_ingresados);

        tvListaValores = findViewById(R.id.tv_valores_ingresados);

        listaValoresIngresados = new ArrayList<>();

        btnEliminar = findViewById(R.id.btn_eliminar);
        btnEliminar.setOnClickListener(this);

        btnAgregar = findViewById(R.id.btn_agregar);
        btnAgregar.setOnClickListener(this);

        btnImprimir = findViewById(R.id.btn_imprimir);
        btnImprimir.setOnClickListener(this);
        btnImprimir.setEnabled(false);
        btnImprimir.setAlpha(0.5f);

        btnBorrar = findViewById(R.id.btn_borrar);
        btnBorrar.setOnClickListener(this);

        btnCero = findViewById(R.id.btn_cero);
        btnCero.setOnClickListener(this);

        tvValor = findViewById(R.id.tv_ingreso_valores);
        valor = "";
        tvValor.setText("0");

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

    //TODO: Crear método para pasar el valor a palabra

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
                    tvValor.setText("0");
                else
                    formatearValor(valor, tvValor);

            }
        }

        if (v.getId() == R.id.btn_imprimir) {

            try {
                HPRTPrinterHelper.PrintText(hacerTexto());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (valor.length() < NUM_MAX_CARACTERES_POR_VALOR) {

            switch (v.getId()) {
                case R.id.btn_cero:
                    if (valor.length() > 0) {
                        valor += "0";
                        formatearValor(valor, tvValor);
                    }
                    break;

                case R.id.btn_uno:
                    valor += "1";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_dos:
                    valor += "2";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_tres:
                    valor += "3";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_cuatro:
                    valor += "4";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_cinco:
                    valor += "5";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_seis:
                    valor += "6";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_siete:
                    valor += "7";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_ocho:
                    valor += "8";
                    formatearValor(valor, tvValor);
                    break;

                case R.id.btn_nueve:
                    valor += "9";
                    formatearValor(valor, tvValor);
                    break;
            }
        }

        if ((v.getId() == R.id.btn_agregar) && !valor.equals("")
                && valor.length() <= NUM_MAX_CARACTERES_POR_VALOR) {

            total += Long.parseLong(valor);
            formatearValor(String.valueOf(total), tvTotal);

            listaValoresIngresados.add(valor);

            String unionValores = "";
            for (int i = 0; i < listaValoresIngresados.size() - 1; i++)
                unionValores += listaValoresIngresados.get(i) + "\n";
            unionValores += listaValoresIngresados.get(listaValoresIngresados.size() - 1);

            tvListaValores.setLines(listaValoresIngresados.size());
            formatearValor(unionValores, tvListaValores);

            valor = "";
            tvValor.setText("0");

            svListaValoresIngresados.post(new Runnable() {
                @Override
                public void run() {
                    svListaValoresIngresados.fullScroll(View.FOCUS_DOWN);
                }
            });
        }

        if (v.getId() == R.id.btn_eliminar && listaValoresIngresados.size() > 0) {

            total -= Long.parseLong(listaValoresIngresados.get(listaValoresIngresados.size() - 1));
            formatearValor(String.valueOf(total), tvTotal);

            listaValoresIngresados.remove(listaValoresIngresados.size() - 1);

            if (listaValoresIngresados.size() == 0) {
                tvListaValores.setText("");

            } else {

                String unionValores = "";
                for (int i = 0; i < listaValoresIngresados.size() - 1; i++)
                    unionValores += listaValoresIngresados.get(i) + "\n";
                unionValores += listaValoresIngresados.get(listaValoresIngresados.size() - 1);

                tvListaValores.setLines(listaValoresIngresados.size());
                formatearValor(unionValores, tvListaValores);
            }

            svListaValoresIngresados.post(new Runnable() {
                @Override
                public void run() {
                    svListaValoresIngresados.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }

    private void formatearValor(String str, TextView tv) {

        if (str.length() > 3) {
            StringBuilder strBuilder;

            //Si el str es una lista
            if (str.contains("\n")) {

                String[] arrStr = str.split("\n");
                String listaValores = "";

                //Recorrer la lista de String
                for (int i = 0; i < arrStr.length; i++) {

                    //Recorrer un solo string de la lista
                    strBuilder = new StringBuilder(arrStr[i]);
                    if (arrStr[i].length() > 3) {
                        for (int j = arrStr[i].length() - 3; j > 0; j -= 3)
                            strBuilder.insert(j, ".");

                    }
                    listaValores += strBuilder.toString() + "\n";
                }

                //Eliminar el último salto de linea
                listaValores = listaValores.substring(0, listaValores.length() - 1);

                tv.setText(listaValores);

            } else {

                strBuilder = new StringBuilder(str);
                for (int i = str.length() - 3; i > 0; i -= 3)
                    strBuilder.insert(i, ".");

                tv.setText(strBuilder.toString());
            }
        } else {
            tv.setText(str);
        }
    }

    private String hacerTexto() throws Exception {

        String texto = "";

        texto += "R.U.T: " + "12.345.678-9\n";
        texto += "BOLETA ELECTRONICA N " + "99.999\n";
        texto += "SII - TEMUCO\n\n";

        texto += "Giro: \n";
        texto += "Direccion: \n";
        texto += "Fono: \n";
        texto += "Correo: \n\n";

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        texto += "Fecha de Emision: " + format.format(date) + "\n";
        texto += "Medio Pago: \n\n";

        texto += "Total: " + total + "\n\n";

        texto += "Timbre Electronico SII\n";
        texto += "Res 80 de 2014 Verfique Documento\n";
        texto += "www.sii.cl\n";
        texto += "www.micropos.cl/eboleta.html\n\n\n";

        return texto;
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