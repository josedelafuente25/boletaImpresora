package com.micropos.boletaelectronica.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.micropos.boletaelectronica.R;
import com.micropos.boletaelectronica.app.ActivityListaDispositivos;

import java.text.SimpleDateFormat;
import java.util.Date;

import HPRTAndroidSDK.HPRTPrinterHelper;

public class FragmentBoleta extends Fragment implements View.OnClickListener {

    private final String TAG = "MainActivity";
    private final int NUM_MAX_CARACTERES_POR_VALOR = 16;
    private final int NUM_MAX_CARACTERES_VALOR_TOTAL = 18;

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
    private TextView tvFolio;

    private String valor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_boleta, container, false);

        tvFolio = root.findViewById(R.id.tv_folio);
        tvFolio.setText("#1234");

        btnConectar = root.findViewById(R.id.btn_conectar);
        btnConectar.setOnClickListener(this);
        tvEstadoConexion = root.findViewById(R.id.tv_estado_conexion);
        tvEstadoConexion.setText(getResources().getString(R.string.desconectado));
        tvEstadoConexion.setTextColor(getResources().getColor(R.color.colorRed));

        btnImprimir = root.findViewById(R.id.btn_imprimir);
        btnImprimir.setOnClickListener(this);
        btnImprimir.setEnabled(false);
        btnImprimir.setAlpha(0.25f);

        imgBtnBorrar = root.findViewById(R.id.btn_borrar);
        imgBtnBorrar.setOnClickListener(this);

        btnCero = root.findViewById(R.id.btn_cero);
        btnCero.setOnClickListener(this);

        tvValor = root.findViewById(R.id.tv_ingreso_valor);
        valor = "";
        tvValor.setText("$0");

        btnUno = root.findViewById(R.id.btn_uno);
        btnUno.setOnClickListener(this);
        btnDos = root.findViewById(R.id.btn_dos);
        btnDos.setOnClickListener(this);
        btnTres = root.findViewById(R.id.btn_tres);
        btnTres.setOnClickListener(this);
        btnCuatro = root.findViewById(R.id.btn_cuatro);
        btnCuatro.setOnClickListener(this);
        btnCinco = root.findViewById(R.id.btn_cinco);
        btnCinco.setOnClickListener(this);
        btnSeis = root.findViewById(R.id.btn_seis);
        btnSeis.setOnClickListener(this);
        btnSiete = root.findViewById(R.id.btn_siete);
        btnSiete.setOnClickListener(this);
        btnOcho = root.findViewById(R.id.btn_ocho);
        btnOcho.setOnClickListener(this);
        btnNueve = root.findViewById(R.id.btn_nueve);
        btnNueve.setOnClickListener(this);

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        //TODO: Preguntar aquí si el modo calculadora está activado
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_conectar) {
            Intent serverIntent = new Intent(getActivity(), ActivityListaDispositivos.class);
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
                valor = "";
                tvValor.setText("$0");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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