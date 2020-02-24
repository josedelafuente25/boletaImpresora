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

    public void consultarRegistro(String nombreTabla, String campo) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + nombreTabla
                , null);

        if (cursor.moveToFirst()) {
            int count = 0;
            while (!cursor.isAfterLast()) {
                String str = cursor.getString(cursor.getColumnIndex(campo));
                count++;
                Log.i("LOG", campo + " n° " + count + ": " + str);
                cursor.moveToNext();
            }
        }

        db.close();
    }

    public String obtenerSuma(String nombreTabla, String campoASumar, String campoARecorrer, String fechaYYYYMMDD) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + campoASumar + ") AS Suma"
                + " FROM " + nombreTabla
                + " WHERE " + campoARecorrer
                + " BETWEEN '" + fechaYYYYMMDD + " 00:00:00' AND '" + fechaYYYYMMDD + " 23:59:59'", null);
        if (cursor.moveToFirst()) {
            cursor.getString(0);
            return cursor.getString(cursor.getColumnIndex("Suma"));
        }
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DELETE FROM " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_EMISOR);
            sqLiteDatabase.execSQL("DELETE FROM " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_BOLETA);
            sqLiteDatabase.execSQL("DELETE FROM " + UtilidadesDB.NOMBRE_TABLA_ELECTRONICA_CAF);
        }

    }
}
