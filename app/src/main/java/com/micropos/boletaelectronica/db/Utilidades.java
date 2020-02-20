package com.micropos.boletaelectronica.db;

public class Utilidades {
    public static final String NOMBRE_TABLA_USUARIOS = "usuarios";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CONSTRASENA = "contrasena";

    static final String CREAR_TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA_USUARIOS + " (" + CAMPO_NOMBRE + " TEXT, " + CAMPO_CONSTRASENA + " TEXT)";
}
