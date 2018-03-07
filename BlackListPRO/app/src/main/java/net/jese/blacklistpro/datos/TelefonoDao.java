package net.jese.blacklistpro.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.jese.blacklistpro.modelo.Llamada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrii on 06/03/2018.
 */

public class TelefonoDao {
    ContentValues valor;

    private static SQLiteDatabase db;
    private DataBase dbhelper;

    private String[] columnas = { DataBase.tablaLlamadas.COLUMNA_ID,
            DataBase.tablaLlamadas.COLUMNA_TELEFONO, DataBase.tablaLlamadas.COLUMNA_FECHA,
            DataBase.tablaLlamadas.COLUMNA_HORA };

    public TelefonoDao(Context context) {
        // TODO Auto-generated constructor stub
        dbhelper = new DataBase(context);
    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public void agregarLlamada(String telefono, String fecha, String hora) {

        valor = new ContentValues();
        valor.put(DataBase.tablaLlamadas.COLUMNA_TELEFONO, telefono);
        valor.put(DataBase.tablaLlamadas.COLUMNA_FECHA, fecha);
        valor.put(DataBase.tablaLlamadas.COLUMNA_HORA, hora);

        db.insert(DataBase.tablaLlamadas.TABLA_LLAMADAS, null, valor);
        valor.clear();

    }

    public List<Llamada> getAllLlamadas() {
        List<Llamada> listaLlamadas = new ArrayList<Llamada>();
        Cursor cursor = db.query(DataBase.tablaLlamadas.TABLA_LLAMADAS, columnas, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Llamada nuevaLlamada = cursorToLamada(cursor);
            listaLlamadas.add(nuevaLlamada);
            Log.v("Llamada ", "Telefono " + nuevaLlamada.getTelefono()
                    + " fecha y hora " + nuevaLlamada.getFecha() + " "
                    + nuevaLlamada.getHora());

            cursor.moveToNext();
        }
        return listaLlamadas;
    }

    // --------------------Mapear Llamada
    // ---------------------------------------------------
    private Llamada cursorToLamada(Cursor cursor) {
        Llamada llamada = new Llamada();
        llamada.setId(cursor.getInt(0));
        llamada.setTelefono(cursor.getString(1));
        llamada.setFecha(cursor.getString(2));
        llamada.setHora(cursor.getString(3));
        return llamada;
    }
}
