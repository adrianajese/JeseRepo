package net.jese.blacklistpro.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adrii on 06/03/2018.
 */

public class DataBase extends SQLiteOpenHelper {
    // Declaracion de la version y nombre de la base de datos.
    private static final String DATABASE_NAME = "listaNegra";
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public static class tablaBloqueos {
        public static String TABLA_BLOQUEOS = "bloqueos";
        public static String COLUMNA_ID = "_id";
        public static String COLUMNA_NOMBRE = "nombre";
        public static String COLUMNA_TELEFONO = "telefono";
        public static String COLUMNA_TIPO = "tipo";

    }

    public static class tablaMensajes {
        public static String TABLA_MENSAJES = "mensajes";
        public static String COLUMNA_ID = "_id";
        public static String COLUMNA_TELEFONO = "telefono";
        public static String COLUMNA_CUERPO = "cuerpo";
        public static String COLUMNA_FECHA = "fecha";
        public static String COLUMNA_HORA = "hora";

    }

    public static class tablaLlamadas {
        public static String TABLA_LLAMADAS = "llamadas";
        public static String COLUMNA_ID = "_id";
        public static String COLUMNA_TELEFONO = "telefono";
        public static String COLUMNA_FECHA = "fecha";
        public static String COLUMNA_HORA = "hora";

    }

    // SQL Creacion de la tabla bloqueos
    private static final String DATABASE_CREATE_Bloqueos = "create table "
            + tablaBloqueos.TABLA_BLOQUEOS + "(" + tablaBloqueos.COLUMNA_ID
            + " Integer primary key, " + tablaBloqueos.COLUMNA_NOMBRE
            + " text, " + tablaBloqueos.COLUMNA_TELEFONO + " text,"
            + tablaBloqueos.COLUMNA_TIPO + " Integer )";

    // SQL Creacion de la tabla Mensajes
    private static final String DATABASE_CREATE_MENSAJES = "create table "
            + tablaMensajes.TABLA_MENSAJES + "(" + tablaMensajes.COLUMNA_ID
            + " Integer primary key autoincrement, "
            + tablaMensajes.COLUMNA_TELEFONO + " text, "
            + tablaMensajes.COLUMNA_CUERPO + " text,"
            + tablaMensajes.COLUMNA_FECHA + " text,"
            + tablaMensajes.COLUMNA_HORA + " text )";

    // SQL Creacion de la tabla Llamadas
    private static final String DATABASE_CREATE_LLAMADAS = "create table "
            + tablaLlamadas.TABLA_LLAMADAS + "(" + tablaLlamadas.COLUMNA_ID
            + " Integer primary key autoincrement, "
            + tablaLlamadas.COLUMNA_TELEFONO + " text, "
            + tablaLlamadas.COLUMNA_FECHA + " text,"
            + tablaLlamadas.COLUMNA_HORA + " text )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DATABASE_CREATE_Bloqueos);
        db.execSQL(DATABASE_CREATE_MENSAJES);
        db.execSQL(DATABASE_CREATE_LLAMADAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("delete table if exist " + tablaBloqueos.TABLA_BLOQUEOS);
        db.execSQL("delete table if exist " + tablaMensajes.TABLA_MENSAJES);
        db.execSQL("delete table if exist " + tablaLlamadas.TABLA_LLAMADAS);

        onCreate(db);
    }

}

