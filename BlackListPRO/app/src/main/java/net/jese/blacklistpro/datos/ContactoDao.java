package net.jese.blacklistpro.datos;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import net.jese.blacklistpro.modelo.Contacto;

import java.util.ArrayList;

/**
 * Created by adrii on 06/03/2018.
 */

public class ContactoDao {

    public ArrayList<Contacto> obtenerContactos(Context con) {
        ArrayList<Contacto> lst = new ArrayList<Contacto>();
        Cursor c = con.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[] { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER }, null, null,
                ContactsContract.Contacts.DISPLAY_NAME);

        while (c.moveToNext()) {
            lst.add(cursorToContacto(c, con));
        }

        return lst;
    }

    public Contacto cursorToContacto(Cursor c, Context con) {
        Contacto cc = new Contacto();
        cc.setId(c.getInt(0));
        cc.setNombre(c.getString(1));
        cc.setNumero(obtenerNumero(c.getInt(2), c.getInt(0), con));
        return cc;
    }

    public String obtenerNumero(int tiene, int id, Context con) {
        if (tiene == 1) {
            String phoneNo = "";
            Cursor numeros = con.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + id, null, null);
            while (numeros.moveToNext()) {
                phoneNo = numeros
                        .getString(numeros
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            return phoneNo;
        } else {
            return "";
        }
    }
}
