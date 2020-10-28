package com.micropos.boletaelectronica.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micropos.boletaelectronica.R;
import com.micropos.boletaelectronica.interfaces.ApiUsers;
import com.micropos.boletaelectronica.modelos.UsuarioRespuesta;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIngresar;
    private EditText editTextNombreUsuario;
    private EditText editTextConstrasena;
    private TextView textViewIncorrectPasswordUser;
    private Retrofit retrofit;

    private static final String TAG ="data";
    private String name;
    private String email;
    private String client_id;
    private String client_secret;
    private String device_id;
    private String password;
    private String grant_type;
    private String username;



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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://157.245.190.100/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        enviarDatosUsuario();
        obtenerDatosUsuario();
    }


    private void obtenerDatosUsuario() {
        ApiUsers service = retrofit.create(ApiUsers.class);
        Call<UsuarioRespuesta> usuarioRespuestaCall = service.obtenerDatos();
        usuarioRespuestaCall.enqueue(new Callback<UsuarioRespuesta>() {
            @Override
            public void onResponse(Call<UsuarioRespuesta> call, Response<UsuarioRespuesta> response) {

                if (response.isSuccessful()) {
                    name =  "name= " + response.body().getSuccess().getName();
                    email =  "email= " + response.body().getSuccess().getEmail();
                    client_secret = "client_secret= " + response.body().getSuccess().getClient_secret();
                    client_id =  "client_id= " + response.body().getSuccess().getClient_id();
                    device_id =  "device_id= " + response.body().getSuccess().getDevice_id();

                    Log.i(TAG, name);
                    Log.i(TAG, email);
                    Log.i(TAG, client_id);
                    Log.i(TAG, client_secret);
                    Log.i(TAG, device_id);

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

    private void enviarDatosUsuario() {

        ApiUsers service = retrofit.create(ApiUsers.class);

        Call<UsuarioRespuesta> usuarioRespuestaCall = service.enviarDatos("jose.dlf25@gmail.com", "password", "gcuKQfgtH0dIikRnuR1WQ7brcgnRYL6qLYQHLWpm","2","jose199425");


            usuarioRespuestaCall.enqueue(new Callback<UsuarioRespuesta>() {
            @Override
            public void onResponse(Call<UsuarioRespuesta> call, Response<UsuarioRespuesta> response) {

                if (response.isSuccessful()) {

                  //  response.body();

                    /*

                    client_secret = response.body().getSuccess().getClient_secret();
                    client_id = response.body().getSuccess().getClient_id().toString();
                    username=response.body().getSuccess().getEmail();
                    password="jose199425";
                    grant_type="password";

              */

                    Log.i("POST", name);
                    Log.i("POST", email);
                    Log.i("POST", client_id);
                    Log.i("POST", client_secret);
                    Log.i("POST", device_id);

                }else {
                    Log.e(TAG, "OnResponse " + response.errorBody());
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