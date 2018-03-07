package net.jese.blacklistpro.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import net.jese.blacklistpro.modelo.contactoBloqueado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adrii on 06/03/2018.
 */

public class ContactoBloqueadoDao {

    private static SQLiteDatabase db;
    private DataBase dbhelper;

    private String[] columnas = { DataBase.tablaBloqueos.COLUMNA_ID,
            DataBase.tablaBloqueos.COLUMNA_NOMBRE, DataBase.tablaBloqueos.COLUMNA_TELEFONO,
            DataBase.tablaBloqueos.COLUMNA_TIPO };

    public ContactoBloqueadoDao(Context context) {
        // TODO Auto-generated constructor stub
        dbhelper = new DataBase(context);

    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    // -------------Crear----------------------------------------------------
    // ----------------------------------------------------------------------
    public boolean crearContactoBloqueado(int id, String nombre,
                                          String telefono, int tipo) {
        ContentValues values = new ContentValues();

        values.put(DataBase.tablaBloqueos.COLUMNA_ID, id);
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        values.put("tipo", tipo);
        try {
            db.insert(DataBase.tablaBloqueos.TABLA_BLOQUEOS, null, values);
            return true;
        } catch (SQLiteException e) {
            // TODO: handle exception
            Log.v("Error", "-------------------------------------");
            return false;
        }
    }

    // -------------Consultar----------------------------------------------------
    // --------------------------------------------------------------------------

    public List<contactoBloqueado> getAllBloqueos() {
        List<contactoBloqueado> listaBloqueos = new ArrayList<contactoBloqueado>();
        Cursor cursor = db.query(DataBase.tablaBloqueos.TABLA_BLOQUEOS, columnas, null,
                null, null, null, DataBase.tablaBloqueos.COLUMNA_NOMBRE);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contactoBloqueado nuevoBloqueo = cursorToContactoBloqueado(cursor);
            listaBloqueos.add(nuevoBloqueo);
            Log.v("Contacto", nuevoBloqueo.getNombre());
            cursor.moveToNext();
        }
        Collections.reverse(listaBloqueos);
        return listaBloqueos;
    }

    public String existe(String numero) {
        Log.v("hola", numero);

        List<contactoBloqueado> listaBloqueos = new ArrayList<contactoBloqueado>();
        Cursor cursor = db.query(DataBase.tablaBloqueos.TABLA_BLOQUEOS, columnas,
                DataBase.tablaBloqueos.COLUMNA_TELEFONO + " = ? " , new String[]{numero}, null,
                null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contactoBloqueado nuevoBloqueo = cursorToContactoBloqueado(cursor);
            listaBloqueos.add(nuevoBloqueo);
            cursor.moveToNext();
        }
        Log.v("tamaño de la lista", listaBloqueos.size() + "");

        if (listaBloqueos.size() >= 1) {
            int tipo = listaBloqueos.get(0).getTipoBloqueo();
            return "Si@" + tipo;

        } else {
            return "No@0";
        }
    }

    // --------------------Mapear nota
    // ---------------------------------------------------
    private contactoBloqueado cursorToContactoBloqueado(Cursor cursor) {
        contactoBloqueado Bloqueo = new contactoBloqueado();
        Bloqueo.setId(cursor.getInt(0));
        Bloqueo.setNombre(cursor.getString(1));
        Bloqueo.setNumero(cursor.getString(2));
        Bloqueo.setTipoBloqueo(cursor.getInt(3));
        return Bloqueo;
    }
}
