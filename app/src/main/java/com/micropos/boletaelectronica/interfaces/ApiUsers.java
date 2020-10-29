package com.micropos.boletaelectronica.interfaces;
import com.micropos.boletaelectronica.modelos.Post;
import com.micropos.boletaelectronica.modelos.UsuarioRespuesta;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiUsers {

    @GET("api/login/?email=jose.dlf25@gmail.com&password=jose199425&dispositivo_id=3")
    Call<UsuarioRespuesta> obtenerDatos();

    @FormUrlEncoded
    @POST("oauth/token")
    Call<Post> enviarDatos(@Field("username") String username,
                           @Field("grant_type") String grant_type,
                           @Field ("client_secret") String client_secret,
                           @Field("client_id") String client_id,
                           @Field("password") String password);

}
