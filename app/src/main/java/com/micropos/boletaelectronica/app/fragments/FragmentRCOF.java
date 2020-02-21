package com.micropos.boletaelectronica.app.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.micropos.boletaelectronica.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentRCOF extends Fragment implements View.OnClickListener {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView tvFecha;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rcof, container, false);

        tvFecha = root.findViewById(R.id.tv_fecha);
        tvFecha.setOnClickListener(this);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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
                tvFecha.setText(strDia + "-" + strMes + "-" + strAno);
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

                break;
        }
    }
}
