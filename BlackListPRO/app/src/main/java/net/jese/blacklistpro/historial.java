package net.jese.blacklistpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import net.jese.blacklistpro.datos.MensajeDao;
import net.jese.blacklistpro.datos.TelefonoDao;
import net.jese.blacklistpro.modelo.Llamada;
import net.jese.blacklistpro.modelo.modeloMensaje;

import java.util.List;

/**
 * Created by adrii on 06/03/2018.
 */

public class historial extends Activity {
    ListView lstLlamadas, lstMensajes;
    TelefonoDao dao;
    MensajeDao mendao;
    private TabHost tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia);

        final Intent i = new Intent(this, Mensaje.class);

        final MensajeDao daoMen = new MensajeDao(this);
        // Configuramos esl t�tulo de las tabs---------------------
        // ---------------------------------------------------------
        TabHost.TabSpec spec = null;
        tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();

        spec = tabs.newTabSpec("tag1");

        spec.setIndicator("Llamadas");
        spec.setContent(R.id.tab1);
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");

        spec.setIndicator("Mensajes");
        spec.setContent(R.id.tab2);
        tabs.addTab(spec);
        // ----------------------------------------------------------
        // ----------------------------------------------------------

        lstLlamadas = (ListView) findViewById(R.id.lstLlamadas);
        lstMensajes = (ListView) findViewById(R.id.lstMensajes);

        dao = new TelefonoDao(this);
        mendao = new MensajeDao(this);
        // -----------Llamadas-------------------------------
        List<Llamada> listaLlamada;
        dao.open();
        listaLlamada = dao.getAllLlamadas();
        dao.close();

        ArrayAdapter<Llamada> adp = new ArrayAdapter<Llamada>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                listaLlamada);
        lstLlamadas.setAdapter(adp);
        // -----------Mensajes-------------------------------
        List<modeloMensaje> listaMensajes;
        mendao.open();
        listaMensajes = mendao.getAllMensajes();
        mendao.close();

        try {
            ArrayAdapter<modeloMensaje> adp2 = new ArrayAdapter<modeloMensaje>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1, listaMensajes);
            lstMensajes.setAdapter(adp2);
        } catch (Exception e) {
            // TODO: handle exception
            Log.v("Error", e.toString());
        }

        lstMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                modeloMensaje m = (modeloMensaje) parent.getAdapter().getItem(
                        position);
                i.putExtra("mess", m);
                startActivityForResult(i, 9);
            }
        });
    }
}

