package net.jese.blacklistpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.jese.blacklistpro.datos.ContactoDao;
import net.jese.blacklistpro.modelo.Contacto;

import java.util.List;

/**
 * Created by adrii on 06/03/2018.
 */

public class VistaContacto extends Activity {
    ListView lstContactos;
    List<Contacto> listaContactos;

    ContactoDao dao;
    Intent intent;
    public static final int RESULTADO_DIALOGO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos);

        lstContactos = (ListView) findViewById(R.id.lstContactos);

        dao = new ContactoDao();
        try {
            listaContactos = dao.obtenerContactos(this);

            ArrayAdapter<Contacto> adp = new ArrayAdapter<Contacto>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1, listaContactos);
            lstContactos.setAdapter(adp);
        } catch (Exception e) {
            // TODO: handle exception
            Log.v("error de vista", e.toString());
        }

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Contacto c = (Contacto) parent.getAdapter().getItem(position);
                Log.v("Contacto",
                        "id: " + c.getId() + " nombre: " + c.getNombre()
                                + " numero: " + c.getNumero());
                intent = new Intent(VistaContacto.this,
                        DialogoTipoBloqueo.class);
                intent.putExtra("contacto", c);
                startActivityForResult(intent, RESULTADO_DIALOGO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULTADO_DIALOGO && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }else{
            if (resultCode==RESULT_CANCELED) {
                finish();
            }
        }
    }
}
