package com.micropos.boletaelectronica.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micropos.boletaelectronica.R;
import com.micropos.boletaelectronica.db.DBManager;
import com.micropos.boletaelectronica.interfaces.ApiUsers;
import com.micropos.boletaelectronica.modelos.DatosUsuario;
import com.micropos.boletaelectronica.modelos.UsuarioRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;


public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIngresar;
    private EditText editTextNombreUsuario;
    private EditText editTextConstrasena;
    private TextView textViewIncorrectPasswordUser;
    private DBManager dbManager;
    ProgressDialog pd;
    private Retrofit retrofit;

    private static final String TAG ="data";
    private Object DatosUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textViewIncorrectPasswordUser = findViewById(R.id.text_view_usuario_contrasena_incorrecto);
        textViewIncorrectPasswordUser.setVisibility(View.INVISIBLE);
        btnIngresar = findViewById(R.id.btn_ingresar);
        btnIngresar.setOnClickListener(this);
        editTextNombreUsuario = findViewById(R.id.user_name);
        editTextConstrasena = findViewById(R.id.user_password);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://157.245.190.100/api/login/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



       obtenerDatosUsuario();
    }
    private void obtenerDatosUsuario() {

        ApiUsers service = retrofit.create(ApiUsers.class);
        Call<UsuarioRespuesta> usuarioRespuestaCall = service.obtenerDatos();

        usuarioRespuestaCall.enqueue(new Callback<UsuarioRespuesta>() {
            @Override
            public void onResponse(Call<UsuarioRespuesta> call, Response<UsuarioRespuesta> response) {

                if (response.isSuccessful()) {
                    String result =  " email= " + response.body().getSuccess().getEmail() + "\n device_id= " +
                            response.body().getSuccess().getDevice_id();
                    Log.i(TAG, result);

                }else {
                    Log.e(TAG, "OnResponse" + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<UsuarioRespuesta> call, Throwable t) {
            Log.e(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

        //  ContentValues credenciales = new ContentValues();
        switch (v.getId()) {
            case R.id.btn_ingresar:
                //   credenciales.put(UtilidadesDB.CAMPO_NOMBRE, editTextNombreUsuario.getText().toString());
                // credenciales.put(UtilidadesDB.CAMPO_CONSTRASENA, editTextConstrasena.getText().toString());
                Toast.makeText(getApplicationContext(), "probando probando", Toast.LENGTH_SHORT).show();
                //   Intent intent = new Intent(LogInActivity.this, MainActivityNavDrawer.class);
                //this.startActivity(intent);
                break;
        }

    }


}