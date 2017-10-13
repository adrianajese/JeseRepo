package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by adrii on 05/10/2017.
 */

public class MiDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mibasesitadatos";
    private static final int DB_VERSION = 2;
    public static final String [] COLUMS_CONTACTOS = {"_id","nombre","correo_electronico","twitter","telefono","fecha_nacimiento"};
    public static final String TABLE_CONTACTOS_NAME = "CONTACTOS";

    private final String TABLE_CONTACTOS_SCRIPT = "create table contactos (" +
            "_id integer primary key autoincrement,"+
            "nombre varchar(100) null,"+
            "correo_electronico varchar(100) not null,"+
            "twitter varchar(100) null,"+
            "telefono varchar(20) null,"+
            "fecha_nacimiento date null);";

    public MiDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.TABLE_CONTACTOS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
