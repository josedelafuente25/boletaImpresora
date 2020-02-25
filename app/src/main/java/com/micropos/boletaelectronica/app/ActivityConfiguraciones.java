package com.micropos.boletaelectronica.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.micropos.boletaelectronica.R;

public class ActivityConfiguraciones extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInfoComercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_activity_configuraciones);

        Toolbar toolbar = findViewById(R.id.toolbar_configuraciones);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvInfoComercio = findViewById(R.id.tv_info_comercio);
        tvInfoComercio.setOnClickListener(this);

    }

    //Cerrar el activity cuando se presione el botón atras en el ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_info_comercio:
                //TODO: Lanzar un fragment donde se mostrará la información del comercio
                break;
        }
    }
}