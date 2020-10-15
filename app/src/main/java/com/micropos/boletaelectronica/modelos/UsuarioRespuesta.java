package com.micropos.boletaelectronica.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRespuesta {

    @SerializedName("success")
    @Expose
    private DatosUsuario success;

    public DatosUsuario getSuccess() {
        return success;
    }

    public void setSuccess(DatosUsuario success) {
        this.success = success;
    }



     }

