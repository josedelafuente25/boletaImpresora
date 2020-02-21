package com.micropos.boletaelectronica.db;

public class UtilidadesDB {

    public static final int DB_VERSION=2;

    public static final String NOMBRE_DB="db_boleta_electronica";

    public static final String CAMPO_ID = "id";

    public static final String NOMBRE_TABLA_ELECTRONICA_EMISOR = "electronica_emisor";
    public static final String CAMPO_RUT = "rut";
    public static final String CAMPO_GIRO = "giro";
    public static final String CAMPO_RAZON_SOCIAL = "razon_social";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_FONO = "fono";
    public static final String CAMPO_CORREO = "correo";
    public static final String CAMPO_NOMBRE_CERTIFICADO = "nombre_certificado";
    public static final String CAMPO_CIUDAD_SUCURSAL = "ciudad_sucursal";

    static final String CREAR_TABLA_ELECTRONICA_EMISOR = "CREATE TABLE IF NOT EXISTS "
            + NOMBRE_TABLA_ELECTRONICA_EMISOR + "("
            + CAMPO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_RUT + " TEXT(10) NOT NULL DEFAULT '', "
            + CAMPO_GIRO + " TEXT(80) NOT NULL DEFAULT '', "
            + CAMPO_RAZON_SOCIAL + " TEXT(100) NOT NULL DEFAULT '', "
            + CAMPO_DIRECCION + " TEXT(70) NOT NULL DEFAULT '', "
            + CAMPO_FONO + " TEXT(20) NOT NULL DEFAULT '', "
            + CAMPO_CORREO + " TEXT(80) NOT NULL DEFAULT '', "
            + CAMPO_NOMBRE_CERTIFICADO + " TEXT(250) NOT NULL DEFAULT '', "
            + CAMPO_CIUDAD_SUCURSAL + " TEXT(20) NOT NULL DEFAULT '')";

    public static final String NOMBRE_TABLA_ELECTRONICA_BOLETA = "electronica_boleta";
    public static final String CAMPO_FOLIO = "folio";
    public static final String CAMPO_FECHA_EMISION = "fecha_emision";
    public static final String CAMPO_DIA = "dia";
    public static final String CAMPO_MES = "mes";
    public static final String CAMPO_ANO = "ano";
    public static final String CAMPO_IVA = "iva";
    public static final String CAMPO_NETO = "neto";
    public static final String CAMPO_TOTAL = "total";
    public static final String CAMPO_ENVIADO = "enviado";

    static final String CREAR_TABLA_ELECTRONICA_BOLETA = "CREATE TABLE IF NOT EXISTS "
            + NOMBRE_TABLA_ELECTRONICA_BOLETA + "("
            + CAMPO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_FOLIO + " INTEGER(11) NOT NULL DEFAULT 0, "
            + CAMPO_FECHA_EMISION + " TEXT(19) NOT NULL DEFAULT '', "
            + CAMPO_DIA + " INTEGER(2) NOT NULL DEFAULT 0, "
            + CAMPO_MES + " TEXT(9) NOT NULL DEFAULT '', "
            + CAMPO_ANO + " INTEGER(4) NOT NULL DEFAULT 0, "
            + CAMPO_IVA + " INTEGER(18) NOT NULL DEFAULT 0, "
            + CAMPO_NETO + " INTEGER(18) NOT NULL DEFAULT 0, "
            + CAMPO_TOTAL + " INTEGER(18) NOT NULL DEFAULT 0, "
            + CAMPO_ENVIADO + " INTEGER(1) NOT NULL DEFAULT 0)";

    public static final String NOMBRE_TABLA_ELECTRONICA_CAF = "electronica_caf";
    public static final String CAMPO_CAF = "caf";
    public static final String CAMPO_DESDE = "desde";
    public static final String CAMPO_HASTA = "hasta";
    public static final String CAMPO_FECHA_ULTIMA_PETICION = "fecha_ultima_peticion";
    public static final String CAMPO_CANTIDAD = "cantidad";

    static final String CREAR_TABLA_ELECTRONICA_CAF = "CREATE TABLE IF NOT EXISTS "
            + NOMBRE_TABLA_ELECTRONICA_CAF + "("
            + CAMPO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_CAF + " TEXT NOT NULL DEFAULT '', "
            + CAMPO_DESDE + " INTEGER(11) NOT NULL DEFAULT 0, "
            + CAMPO_HASTA + " INTEGER(11) NOT NULL DEFAULT 0, "
            + CAMPO_FECHA_ULTIMA_PETICION + " TEXT(19) NOT NULL DEFAULT '', "
            + CAMPO_CANTIDAD + " INTEGER(11) NOT NULL DEFAULT 0)";

    public static final String NOMBRE_TABLA_USUARIOS = "usuarios";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CONSTRASENA = "contrasena";

    static final String CREAR_TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS "
            + NOMBRE_TABLA_USUARIOS + " ("
            + CAMPO_NOMBRE + " TEXT, "
            + CAMPO_CONSTRASENA + " TEXT)";


}
