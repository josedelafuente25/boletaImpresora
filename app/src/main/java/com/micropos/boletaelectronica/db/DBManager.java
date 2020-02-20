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
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_USUARIOS);

    }

    public void registrarUsuario(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Utilidades.NOMBRE_TABLA_USUARIOS, null, values);
        db.close();
        Log.d("Boton registrarse", "Registrado");
    }

    public boolean existeUsuario(ContentValues values) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] datoAConsultar = {values.get(Utilidades.CAMPO_NOMBRE).toString()};
        String[] camposAObtener = {Utilidades.CAMPO_CONSTRASENA};
        Cursor cursor = db.query(Utilidades.NOMBRE_TABLA_USUARIOS, camposAObtener, Utilidades.CAMPO_NOMBRE + "=?", datoAConsultar, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount()==0){
            cursor.close();
            return false;
        }
        if (cursor.getString(0).equals(values.get(Utilidades.CAMPO_CONSTRASENA))) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
