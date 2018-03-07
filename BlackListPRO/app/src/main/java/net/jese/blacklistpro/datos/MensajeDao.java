package net.jese.blacklistpro.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.jese.blacklistpro.modelo.modeloMensaje;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrii on 06/03/2018.
 */

public class MensajeDao {

    private static SQLiteDatabase db;
    private DataBase dbhelper;

    private String[] columnas = { DataBase.tablaMensajes.COLUMNA_ID,
            DataBase.tablaMensajes.COLUMNA_TELEFONO, DataBase.tablaMensajes.COLUMNA_CUERPO,
            DataBase.tablaMensajes.COLUMNA_FECHA, DataBase.tablaMensajes.COLUMNA_HORA };

    public MensajeDao(Context context) {
        // TODO Auto-generated constructor stub
        dbhelper = new DataBase(context);
    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public void agregarMensaje(String telefono, String cuerpo, String fecha,
                               String hora) {

        ContentValues values = new ContentValues();
        values.put(DataBase.tablaMensajes.COLUMNA_TELEFONO, telefono);
        values.put(DataBase.tablaMensajes.COLUMNA_CUERPO, cuerpo);
        values.put(DataBase.tablaMensajes.COLUMNA_FECHA, fecha);
        values.put(DataBase.tablaMensajes.COLUMNA_HORA, hora);

        db.insert(DataBase.tablaMensajes.TABLA_MENSAJES, null, values);
    }

    public List<modeloMensaje> getAllMensajes() {
        List<modeloMensaje> listaMensajes = new ArrayList<modeloMensaje>();
        Cursor cursor = db.query(DataBase.tablaMensajes.TABLA_MENSAJES, columnas, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            modeloMensaje nuevoMensaje = cursorToMensaje(cursor);
            listaMensajes.add(nuevoMensaje);
            Log.v("Mensaje en la lista", nuevoMensaje.getId() + "");
            Log.v("Mensaje en la lista", nuevoMensaje.getTelefono() + "");
            Log.v("Mensaje en la lista", nuevoMensaje.getCuerpo() + "");
            Log.v("Mensaje en la lista", nuevoMensaje.getFecha() + "");
            Log.v("Mensaje en la lista", nuevoMensaje.getHora() + "");

            cursor.moveToNext();
        }
        return listaMensajes;
    }

    public List<modeloMensaje> getUno(int id) {
        List<modeloMensaje> listaMensajes = new ArrayList<modeloMensaje>();
        Cursor cursor = db.query(DataBase.tablaMensajes.TABLA_MENSAJES, columnas,
                "_id = " + id, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            modeloMensaje nuevoMensaje = cursorToMensaje(cursor);
            listaMensajes.add(nuevoMensaje);
            cursor.moveToNext();
        }
        return listaMensajes;
    }

    // --------------------Mapear Mensaje
    // ---------------------------------------------------
    private modeloMensaje cursorToMensaje(Cursor cursor) {
        modeloMensaje modeloMensaje = new modeloMensaje();
        modeloMensaje.setId(cursor.getInt(0));
        modeloMensaje.setTelefono(cursor.getString(1));
        modeloMensaje.setCuerpo(cursor.getString(2));
        modeloMensaje.setFecha(cursor.getString(3));
        modeloMensaje.setHora(cursor.getString(4));
        return modeloMensaje;
    }

}

