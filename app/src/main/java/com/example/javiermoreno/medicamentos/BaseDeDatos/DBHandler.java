package com.example.javiermoreno.medicamentos.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier Moreno on 6 jul 2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarmasDB";

    private static final String TABLE_ALARMAS = "alarmas";

    private static final String KEY_ID = "id";
    private static final String KEY_MEDICAMENTO = "medicamento";
    private static final String KEY_CANTIDAD = "cantidad";
    private static final String KEY_HORA = "hora";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALARMAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MEDICAMENTO + " TEXT,"
                + KEY_CANTIDAD + " TEXT," + KEY_HORA + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMAS);
        onCreate(db);
    }

    public void addAlarma(Alarmas alarma) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEDICAMENTO, alarma.getMedicamento());
        values.put(KEY_CANTIDAD, alarma.getCantidad());
        values.put(KEY_HORA, alarma.getHora());
        db.insert(TABLE_ALARMAS, null, values);

    }

    public Alarmas getAlarma(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ALARMAS, new String[] { KEY_ID,
                        KEY_MEDICAMENTO, KEY_CANTIDAD, KEY_HORA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Alarmas alarma = new Alarmas(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return alarma;

    }

    public List<Alarmas> getAllAlarmas() {

        List<Alarmas> alarmaList = new ArrayList<Alarmas>();

        String selectQuery = "SELECT * FROM " + TABLE_ALARMAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Alarmas alarma = new Alarmas();
                alarma.setId(Integer.parseInt(cursor.getString(0)));
                alarma.setMedicamento(cursor.getString(1));
                alarma.setCantidad(cursor.getString(2));
                alarma.setHora(cursor.getString(3));

                alarmaList.add(alarma);
            } while (cursor.moveToNext());
        }

        return alarmaList;
    }

    public int getAlarmasCount() {

        String countQuery = "SELECT * FROM " + TABLE_ALARMAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();

    }

    public int updateAlarma(Alarmas alarma) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEDICAMENTO, alarma.getMedicamento());
        values.put(KEY_CANTIDAD, alarma.getCantidad());
        values.put(KEY_HORA, alarma.getHora());

        return db.update(TABLE_ALARMAS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(alarma.getId())});

    }

    public void deleteAlarma(Alarmas alarma) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARMAS, KEY_ID + " = ?",
                new String[] { String.valueOf(alarma.getId()) });
        db.close();

    }
}
