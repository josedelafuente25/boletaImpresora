package com.micropos.boletaelectronica.interfaces;


import com.micropos.boletaelectronica.modelos.DatosUsuario;
import com.micropos.boletaelectronica.modelos.UsuarioRespuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiUsers {

    @GET("?email=jose.dlf25@gmail.com&password=jose199425&dispositivo_id=3")
    Call<UsuarioRespuesta> obtenerDatos();
}
