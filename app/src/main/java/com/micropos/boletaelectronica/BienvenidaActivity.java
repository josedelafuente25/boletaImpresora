package com.micropos.boletaelectronica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.micropos.boletaelectronica.app.MainActivityNavDrawer;

public class BienvenidaActivity extends AppCompatActivity {
private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        btnEntrar = findViewById(R.id.btn_entrar);
    }
    public void ingresar(View view) {
        Intent fragment_boleta = new Intent(this, MainActivityNavDrawer.class);
        startActivity(fragment_boleta);

    }
}