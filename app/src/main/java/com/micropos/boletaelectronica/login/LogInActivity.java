package com.micropos.boletaelectronica.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.micropos.boletaelectronica.R;
import com.micropos.boletaelectronica.app.MainActivityNavDrawer;
import com.micropos.boletaelectronica.db.DBManager;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIngresar;
    private EditText editTextNombreUsuario;
    private EditText editTextConstrasena;
    private TextView textViewIncorrectPasswordUser;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        dbManager = new DBManager(this, "bd_usuarios", null, 1);

        textViewIncorrectPasswordUser = findViewById(R.id.text_view_usuario_contrasena_incorrecto);
        textViewIncorrectPasswordUser.setVisibility(View.INVISIBLE);
        btnIngresar = findViewById(R.id.btn_ingresar);
        btnIngresar.setOnClickListener(this);

        editTextNombreUsuario = findViewById(R.id.user_name);
        editTextConstrasena = findViewById(R.id.user_password);
    }

    @Override
    public void onClick(View v) {
        ContentValues credenciales = new ContentValues();
        switch (v.getId()) {
            case R.id.btn_ingresar:
                Intent intent = new Intent(LogInActivity.this, MainActivityNavDrawer.class);
                this.startActivity(intent);
                break;
                /* Codigo comentado para saltarse el LogIn
                credenciales.put(Utilidades.CAMPO_NOMBRE, editTextNombreUsuario.getText().toString());
                credenciales.put(Utilidades.CAMPO_CONSTRASENA, editTextConstrasena.getText().toString());
                if (!dbManager.existeUsuario(credenciales)) {
                    textViewIncorrectPasswordUser.setVisibility(View.VISIBLE);
                    break;
                }
                textViewIncorrectPasswordUser.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                this.startActivity(intent);
                break;
            */
        }

    }
}
