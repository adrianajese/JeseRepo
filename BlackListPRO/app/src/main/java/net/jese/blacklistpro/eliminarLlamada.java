package net.jese.blacklistpro;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by adrii on 06/03/2018.
 */

public class eliminarLlamada extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        String[] campos = { android.provider.CallLog.Calls.NUMBER,
                android.provider.CallLog.Calls.TYPE,
                android.provider.CallLog.Calls.CACHED_NAME,
                android.provider.CallLog.Calls.CACHED_NUMBER_TYPE };


    }
}
