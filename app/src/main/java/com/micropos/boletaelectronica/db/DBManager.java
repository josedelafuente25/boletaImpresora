package com.micropos.boletaelectronica.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UtilidadesDB.CREAR_TABLA_USUARIOS);
        sqLiteDatabase.execSQL(UtilidadesDB.CREAR_TABLA_ELECTRONICA_EMISOR);
        sqLiteDatabase.execSQL(UtilidadesDB.CREAR_TABLA_ELECTRONICA_BOLETA);
        sqLiteDatabase.execSQL(UtilidadesDB.CREAR_TABLA_ELECTRONICA_CAF);

    }

    public void insertarRegistro(String nameTable, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(nameTable, null, values);
        db.close();
    }

    //Obtener el ultimo registro
    public void consultarRegistro(String nombreTabla, String campo) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + nombreTabla
                , null);

        if (cursor.moveToLast()) {

            String str = cursor.getString(cursor.getColumnIndex(campo));
            Log.i("LOG", campo + ": " + str);
        }

        db.close();
    }

    public String obtenerSuma(String nombreTabla, String campoASumar
            , String campoARecorrer, String fechaYYYYMMDD) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + campoASumar + ") AS Suma"
                        + " FROM " + nombreTabla
                        + " WHERE " + campoARecorrer
                        + " BETWEEN '" + fechaYYYYMMDD + " 00:00:00' AND '" + fechaYYYYMMDD + " 23:59:59'"
                , null);
        if (cursor.moveToFirst()) {
            db.close();
            return cursor.getString(cursor.getColumnIndex("Suma"));
        }
        db.close();
        return null;
    }

    public String obtenerCantFoliosOcupados(String fechaYYYYMMDD) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) AS Cantidad"
                        + " FROM " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_BOLETA
                        + " WHERE " + UtilidadesDB.CAMPO_FECHA_EMISION
                        + " BETWEEN '" + fechaYYYYMMDD + " 00:00:00' AND '" + fechaYYYYMMDD + " 23:59:59'"
                , null);

        if (cursor.moveToFirst()) {
            db.close();
            return cursor.getString(cursor.getColumnIndex("Cantidad"));
        }

        db.close();
        return null;
    }

    public void descontarFolio() {

        //TODO: Descontar según id arrojaro por obtenerFolio()
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_CAF
                + " SET " + UtilidadesDB.CAMPO_CANTIDAD + " = " + UtilidadesDB.CAMPO_CANTIDAD + " - 1"
                + " WHERE " + UtilidadesDB.CAMPO_CANTIDAD + " > 0");
        db.close();
    }

    public String obtenerFolio() {

        String folio = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + UtilidadesDB.CAMPO_CANTIDAD
                        + " FROM " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_CAF
                        + " WHERE " + UtilidadesDB.CAMPO_CANTIDAD + " > 0"
                , null);

        //Si hay folios disponibles
        if (cursor.moveToFirst()) {

            //Utilizar el primer elemento arrojado por la query,
            // suponiendo que solo en el último registro estan los folios disponibles
            folio = cursor.getString(0);
        }

        db.close();
        return folio;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_EMISOR);
        sqLiteDatabase.execSQL("DROP TABLE " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_BOLETA);
        sqLiteDatabase.execSQL("DROP TABLE " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_CAF);
        onCreate(sqLiteDatabase);
    }
}
