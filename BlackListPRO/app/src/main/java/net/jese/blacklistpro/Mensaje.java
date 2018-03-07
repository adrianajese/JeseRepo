package net.jese.blacklistpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.jese.blacklistpro.modelo.modeloMensaje;

/**
 * Created by adrii on 06/03/2018.
 */

public class Mensaje extends Activity {
    TextView numero, fecha, cuerpo;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensaje);

        numero = (TextView) findViewById(R.id.lblNumeroMensaje);
        fecha = (TextView) findViewById(R.id.lblfechaMensaje);
        cuerpo = (TextView) findViewById(R.id.lblCuerpoMensaje);
        i = getIntent();

        Log.v("Si esta", i.hasExtra("mess") + "");

        modeloMensaje imp = (modeloMensaje) i.getSerializableExtra("mess");

        numero.setText(imp.getTelefono());
        fecha.setText(imp.getFecha() + " " + imp.getHora());
        cuerpo.setText(imp.getCuerpo());
    }

    public void action_salirMensaje(View v) {
        finish();
    }
}

