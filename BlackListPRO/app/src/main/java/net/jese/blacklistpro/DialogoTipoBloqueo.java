package net.jese.blacklistpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import net.jese.blacklistpro.datos.ContactoBloqueadoDao;
import net.jese.blacklistpro.modelo.Contacto;

/**
 * Created by adrii on 06/03/2018.
 */

public class DialogoTipoBloqueo extends Activity {
    CheckBox llamadas, mensajes;
    Intent intent;
    ContactoBloqueadoDao dao;
    Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipo_de_bloqueo);
        llamadas = (CheckBox) findViewById(R.id.chkLlamadas);
        mensajes = (CheckBox) findViewById(R.id.chkMensajes);
        intent = getIntent();
        dao = new ContactoBloqueadoDao(this);
        c = (Contacto) intent.getSerializableExtra("contacto");

    }

    public void action_cancelar(View v) {
        intent.removeExtra("contacto");
        finish();
    }

    public void action_bloquear(View v) {

        if (llamadas.isChecked() && !mensajes.isChecked()) {
            dao.open();
            if (dao.crearContactoBloqueado(c.getId(), c.getNombre(),
                    c.getNumero(), 0)) {
                dao.close();
                setResult(RESULT_OK);
                finish();
            } else {
                dao.close();
                setResult(RESULT_CANCELED);
                finish();
            }
        }

        if (!llamadas.isChecked() && mensajes.isChecked()) {
            dao.open();
            if (dao.crearContactoBloqueado(c.getId(), c.getNombre(),
                    c.getNumero(), 1)) {
                dao.close();
                setResult(RESULT_OK);
                finish();
            } else {
                dao.close();
                setResult(RESULT_CANCELED);
                finish();
            }

        }
        if (llamadas.isChecked() && mensajes.isChecked()) {
            dao.open();
            if (dao.crearContactoBloqueado(c.getId(), c.getNombre(),
                    c.getNumero(), 2)) {
                dao.close();
                setResult(RESULT_OK);
                finish();
            } else {
                dao.close();
                setResult(RESULT_CANCELED);
                finish();
            }
        }
        intent.removeExtra("contacto");

    }
}
