package net.jese.blacklistpro.Receivers;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by adrii on 06/03/2018.
 */

public class PhoneState extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO Auto-generated method stub
        ContactoBloqueadoDao dao = new ContactoBloqueadoDao(context);
        TelefonoDao daoTelefono = new TelefonoDao(context);

        String numero = intent
                .getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.v("Numero que llama", numero);
        dao.open();
        String prueba = dao.existe(numero);
        dao.close();
        Log.v("Respuesta", prueba);

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            if (prueba.equals("Si@0") || prueba.equals("Si@2")) {
                disconnectCall();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df3 = new SimpleDateFormat(
                        "dd-MM-yyyy HH:mm:ss a");
                String fechahora = df3.format(c.getTime());
                String fecha = fechahora.substring(0, fechahora.indexOf(" "));
                String hora = fechahora.substring(fechahora.indexOf(" "),
                        fechahora.length());
                daoTelefono.open();
                daoTelefono.agregarLlamada(numero, fecha, hora);
                daoTelefono.close();
                try {
                    Thread.sleep(5000);
                    eliminarLlamada(context);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } else {
        }
    }

    public void eliminarLlamada(Context context) {
        Log.v("Estoy en eliminar", "hola");
        Cursor cu;
        String id = "";
        String columns[] = new String[]{CallLog.Calls._ID,
                CallLog.Calls.NUMBER, CallLog.Calls.DATE,
                CallLog.Calls.DURATION, CallLog.Calls.TYPE};


        cu = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, "type = " + CallLog.Calls.MISSED_TYPE, null, "deconocido");

        cu.moveToFirst();
        while (!cu.isAfterLast()) {
            id = cu.getString(0);
            context.getContentResolver().delete(CallLog.Calls.CONTENT_URI,
                    "_id = " + id, null);
            cu.moveToNext();
        }
    }

    public void disconnectCall() {// Refl
        try {
            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";

            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;

            Method telephonyEndCall;

            Object telephonyObject;
            Object serviceManagerObject;

            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod(
                    "asInterface", IBinder.class);

            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(
                    serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface",
                    IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);

            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
