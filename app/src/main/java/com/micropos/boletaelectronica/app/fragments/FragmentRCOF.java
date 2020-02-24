package com.micropos.boletaelectronica.app.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.micropos.boletaelectronica.R;
import com.micropos.boletaelectronica.db.DBManager;
import com.micropos.boletaelectronica.db.UtilidadesDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentRCOF extends Fragment implements View.OnClickListener {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView tvFecha;
    private TextView tv_total_neto;
    private TextView tv_total_iva;
    private TextView tv_folios_ocupados;
    private TextView tv_total_general;
    private Button btnBuscar;
    private String fecha;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rcof, container, false);

        btnBuscar = root.findViewById(R.id.btn_buscar);
        btnBuscar.setOnClickListener(this);

        tv_total_neto = root.findViewById(R.id.tv_total_neto);
        tv_total_iva = root.findViewById(R.id.tv_total_iva);
        tv_folios_ocupados = root.findViewById(R.id.tv_folios_ocupados);
        tv_total_general = root.findViewById(R.id.tv_total_general);

        tvFecha = root.findViewById(R.id.tv_fecha);
        tvFecha.setOnClickListener(this);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        tvFecha.setText(format.format(date));
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

                mes++;
                String strAno = String.valueOf(ano);
                String strMes = String.valueOf(mes);
                String strDia = String.valueOf(dia);

                if (strDia.length() == 1) {
                    strDia = "0" + strDia;
                }

                if (strMes.length() == 1) {
                    strMes = "0" + strMes;
                }

                tvFecha.setText(strAno + "-" + strMes + "-" + strDia);
            }
        };

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fecha:
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity()
                        , android.R.style.Theme_DeviceDefault_Dialog,
                        mDateSetListener
                        , ano, mes, dia
                );
                dialog.show();
                break;

            case R.id.btn_buscar:

                fecha=tvFecha.getText().toString();

                DBManager db = new DBManager(getActivity()
                        , UtilidadesDB.NOMBRE_DB, null, UtilidadesDB.DB_VERSION);

                String totalNeto = db.obtenerSuma(UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_BOLETA
                        , UtilidadesDB.CAMPO_NETO, UtilidadesDB.CAMPO_FECHA_EMISION, fecha);
                //TODO: Preguntar si se podría dar el caso en que se encuentre solo el total iva
                // y el resto sea null. Si es que en algún momento se daría esa situación
                if (totalNeto != null) {
                    tv_total_neto.setText(totalNeto);
                    String totalIva=db.obtenerSuma(
                            UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_BOLETA
                            ,UtilidadesDB.CAMPO_IVA
                            ,UtilidadesDB.CAMPO_FECHA_EMISION,fecha);
                    tv_total_iva.setText(totalIva);


                } else {
                    Toast.makeText(getActivity()
                            , getResources().getString(R.string.sin_registros_encontrados)
                            , Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;


        }
    }
}
