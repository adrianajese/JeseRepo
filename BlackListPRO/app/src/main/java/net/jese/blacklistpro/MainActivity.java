package net.jese.blacklistpro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import net.jese.blacklistpro.adapters.adaptadorPrincipal;
import net.jese.blacklistpro.datos.ContactoBloqueadoDao;
import net.jese.blacklistpro.modelo.contactoBloqueado;

import java.util.List;

public class MainActivity extends Activity {
    Intent i;
    public static final int RESULTADO_LOGIN = 1;
    public static final int RESULTADO_AGREGAR = 2;

    ListView listaBloqueos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaBloqueos = (ListView) findViewById(R.id.listaBloqueos);

        i = new Intent(this, VistaLogin.class);
        startActivityForResult(i, 1);
        refrescarBloqueos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("El request", requestCode + " Resultado: " + resultCode);
        if (requestCode == RESULTADO_LOGIN && resultCode != RESULT_OK) {
            this.finish();
        }
        if (requestCode == RESULTADO_AGREGAR && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "�Contacto bloqueado!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        refrescarBloqueos();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menuAgregar:
                i = new Intent(this, VistaContacto.class);
                startActivityForResult(i, RESULTADO_AGREGAR);
                break;

            case R.id.menuRegistro:
                i = new Intent(this, historial.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refrescarBloqueos() {
        ContactoBloqueadoDao dao = new ContactoBloqueadoDao(this);
        dao.open();
        List<contactoBloqueado> listaContactos = dao.getAllBloqueos();
        dao.close();
        contactoBloqueado[] arreglo = new contactoBloqueado[listaContactos
                .size()];
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = listaContactos.get(i);
        }
        adaptadorPrincipal adapter = new adaptadorPrincipal(this, arreglo);
        listaBloqueos.setAdapter(adapter);
    }
}
