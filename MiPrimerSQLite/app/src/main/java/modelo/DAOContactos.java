package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrii on 05/10/2017.
 */

public class DAOContactos {

    private Context _contexto;
    private SQLiteDatabase _midb;

    public DAOContactos(Context contexto) {
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }

    public long insert(Contacto c) {

        ContentValues cv = new ContentValues();

        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[1], c.getNombre());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[2], c.getCorreo_electronico());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[3], c.getTwitter());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[4], c.getTelefono());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[5], c.getFechaNac());


        return _midb.insert(MiDBOpenHelper.TABLE_CONTACTOS_NAME, null, cv);
    }

    public Contacto contacto (String s){
        Cursor c = _midb.query(MiDBOpenHelper.TABLE_CONTACTOS_NAME,MiDBOpenHelper.COLUMS_CONTACTOS,"nombre = ?", new String[]{s}, null, null, null);
        Contacto ct = null;

        if (c.moveToFirst()){
            ct = new Contacto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
        }

        return ct;
    }

    public List<Contacto> getAll() {

        List<Contacto> ls = new ArrayList<>();

        Cursor c = _midb.query(MiDBOpenHelper.TABLE_CONTACTOS_NAME, MiDBOpenHelper.COLUMS_CONTACTOS, null, null, null, null, null);

        if (c.moveToFirst()) {

            do {
                Contacto ctc = new Contacto();
                ctc.setId(c.getInt(c.getColumnIndex(MiDBOpenHelper.COLUMS_CONTACTOS[0])));
                ctc.setNombre(c.getString(1));
                ctc.setCorreo_electronico(c.getString(2));
                ctc.setTwitter(c.getString(3));
                ctc.setTelefono(c.getString(4));
                ctc.setFechaNac(c.getString(5));

                ls.add(ctc);


            } while (c.moveToNext());

        }

        return ls;
    }

    public long delete (String tel, String nombre){
        ContentValues cv = new ContentValues();
        return _midb.delete(MiDBOpenHelper.TABLE_CONTACTOS_NAME, "telefono = ? and nombre = ?", new String[]{tel, nombre});
    }

    public long update (Contacto c, String rnombre, String rtelefono){
        ContentValues cv = new ContentValues();
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[1],c.getNombre());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[2],c.getCorreo_electronico());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[3],c.getTwitter());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[4],c.getTelefono());
        cv.put(MiDBOpenHelper.COLUMS_CONTACTOS[5],c.getFechaNac());
        return _midb.update(MiDBOpenHelper.TABLE_CONTACTOS_NAME, cv, "nombre = ? and telefono = ?", new String[]{rnombre, rtelefono});
    }

    public List<Contacto> busquedaPorNombre(String nombre){
        Cursor cr =_midb.rawQuery("select * from " + MiDBOpenHelper.TABLE_CONTACTOS_NAME + " where nombre like '%"+nombre+"%'",null);
        List ls = new ArrayList<>();
        if (cr.moveToFirst()){
            do {
                Contacto ctc= new Contacto(cr.getInt(0),cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4), cr.getString(5));
                ls.add(ctc);
            }while(cr.moveToNext());
        }
        return ls;
    }
}
