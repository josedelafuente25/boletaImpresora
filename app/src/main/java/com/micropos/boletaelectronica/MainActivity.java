package com.micropos.boletaelectronica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    private Button btnBorrar;
    private Button btnImprimir;
    private Button btnAgregar;
    private Button btnEliminar;
    private TextView tvIngresoValores;
    private TextView tvValoresIngresados;
    private ArrayList<String> listaValoresIngresados;
    private ScrollView svListaValoresIngresados;

    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svListaValoresIngresados = findViewById(R.id.sv_lista_valores_ingresados);

        tvValoresIngresados = findViewById(R.id.tv_valores_ingresados);

        listaValoresIngresados = new ArrayList<>();

        btnEliminar = findViewById(R.id.btn_eliminar);
        btnEliminar.setOnClickListener(this);

        btnAgregar = findViewById(R.id.btn_agregar);
        btnAgregar.setOnClickListener(this);

        btnImprimir = findViewById(R.id.btn_imprimir);
        btnImprimir.setOnClickListener(this);

        btnBorrar = findViewById(R.id.btn_borrar);
        btnBorrar.setOnClickListener(this);

        btnCero = findViewById(R.id.btn_cero);
        btnCero.setOnClickListener(this);

        tvIngresoValores = findViewById(R.id.tv_ingreso_valores);
        valor = "";
        tvIngresoValores.setText("0");

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

    }

    //TODO: Crear método para pasar el valor a palabra

    @Override
    public void onClick(View v) {
        //TODO: Corregir el error que no permite agregar un valor con la cantidad máxima
        // de caracteres permitido

        if (v.getId() == R.id.btn_borrar) {

            if (valor.length() > 0) {
                //Quitar el último valor
                valor = valor.substring(0, valor.length() - 1);

                if (valor.length() == 0) {
                    tvIngresoValores.setText("0");
                } else {
                    tvIngresoValores.setText(valor);
                }
            }
        }

        if (valor.length() <= 16) {

            if (valor.length() < 16) {
                switch (v.getId()) {
                    case R.id.btn_cero:
                        valor += "0";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_uno:
                        valor += "1";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_dos:
                        valor += "2";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_tres:
                        valor += "3";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_cuatro:
                        valor += "4";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_cinco:
                        valor += "5";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_seis:
                        valor += "6";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_siete:
                        valor += "7";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_ocho:
                        valor += "8";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_nueve:
                        valor += "9";
                        tvIngresoValores.setText(valor);
                        break;

                    case R.id.btn_imprimir:


                        break;

                }
            }

            if ((v.getId() == R.id.btn_agregar) && !valor.equals("")) {

                listaValoresIngresados.add(valor);

                String unionValores = "";
                for (int i = 0; i < listaValoresIngresados.size() - 1; i++)
                    unionValores += listaValoresIngresados.get(i) + "\n";
                unionValores += listaValoresIngresados.get(listaValoresIngresados.size() - 1);

                tvValoresIngresados.setLines(listaValoresIngresados.size());
                tvValoresIngresados.setText(unionValores);

                valor = "";
                tvIngresoValores.setText("0");
                svListaValoresIngresados.post(new Runnable() {
                    @Override
                    public void run() {
                        svListaValoresIngresados.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }

            if (v.getId() == R.id.btn_eliminar && listaValoresIngresados.size() > 0) {

                listaValoresIngresados.remove(listaValoresIngresados.size() - 1);

                if (listaValoresIngresados.size() == 0) {
                    tvValoresIngresados.setText("");

                } else {

                    String unionValores = "";
                    for (int i = 0; i < listaValoresIngresados.size() - 1; i++)
                        unionValores += listaValoresIngresados.get(i) + "\n";
                    unionValores += listaValoresIngresados.get(listaValoresIngresados.size() - 1);

                    tvValoresIngresados.setLines(listaValoresIngresados.size());
                    tvValoresIngresados.setText(unionValores);
                }

                svListaValoresIngresados.post(new Runnable() {
                    @Override
                    public void run() {
                        svListaValoresIngresados.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }

        }

        formatearValor();
    }

    private void formatearValor() {

        if (valor.length() > 3) {
            StringBuilder strBuilder = new StringBuilder(valor);

            for (int i = valor.length() - 3; i > 0; i -= 3) {
                strBuilder.insert(i, ".");
            }
            tvIngresoValores.setText(strBuilder.toString());
        }
    }
}
