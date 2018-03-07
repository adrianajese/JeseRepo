package net.jese.blacklistpro.Receivers;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import net.jese.blacklistpro.datos.ContactoBloqueadoDao;
import net.jese.blacklistpro.datos.MensajeDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by adrii on 06/03/2018.
 */

public class SMS extends BroadcastReceiver {
    public static boolean aux = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Bundle bundle = intent.getExtras();

        SmsMessage[] smss;
        String Numero = "";
        String Cuerpo = "";

        SmsManager smsm = SmsManager.getDefault();

        ContactoBloqueadoDao con = new ContactoBloqueadoDao(context);
        MensajeDao dao = new MensajeDao(context);

        if (bundle != null) {
            Object[] opdus = (Object[]) bundle.get("pdus");
            smss = new SmsMessage[opdus.length];

            for (int i = 0; i < smss.length; i++) {
                smss[i] = SmsMessage.createFromPdu((byte[]) opdus[i]);

                Numero = smss[i].getOriginatingAddress();
                Cuerpo = smss[i].getMessageBody();

            }
        }
        con.open();
        String test = con.existe(Numero);
        con.close();

        if (test.equals("Si@1") || test.equals("Si@2")) {

            Calendar c = Calendar.getInstance();

            SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
            String fechahora = df3.format(c.getTime());

            String fecha = fechahora.substring(0, fechahora.indexOf(" "));
            String hora = fechahora.substring(fechahora.indexOf(" "),
                    fechahora.length());
            dao.open();
            dao.agregarMensaje(Numero, Cuerpo, fecha, hora);
            dao.close();
            try {
                Thread.sleep(3000);
                eliminasSMS(context, Numero);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void eliminasSMS(Context context, String numero) {
        Log.v("Estoy ", "en eliminar");

        ContentResolver cr = context.getContentResolver();
        Uri uriConv = Uri.parse("content://sms/conversations");
        Uri uriSms = Uri.parse("content://sms/");

        Cursor cConv = cr.query(uriConv, null, null, null, "date DESC");

        while (cConv.moveToNext()) {
            Cursor cSms = cr.query(
                    uriSms,
                    null,
                    "thread_id = "
                            + cConv.getInt(cConv.getColumnIndex("thread_id")),
                    null, "date DESC");
            int count = cSms.getCount();
            Log.v("Cantidad de mensajes", count + "");
            for (int i = 0; i < count; ++i) {
                if (cSms.moveToNext()) {
                    cr.delete(Uri.parse("content://sms/" + cSms.getInt(0)),
                            null, null);
                }
            }

            cSms.close();
            break;
        }
        cConv.close();
    }
}
