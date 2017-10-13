package net.jese.miprimersqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import modelo.Contacto;
import modelo.DAOContactos;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    EditText buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.lista);
        buscar = (EditText) findViewById(R.id.search);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Users.class);
                in.putExtra("mod", "agregar");
                startActivity(in);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in2 = new Intent(getApplicationContext(), InfoUser.class);
                in2.putExtra("identificador",adapterView.getItemAtPosition(i).toString().split("\n")[0]);
                startActivity(in2);
            }
        });

        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                DAOContactos dao = new DAOContactos(MainActivity.this);
                ArrayAdapter ad = null;
                List ls = dao.busquedaPorNombre(buscar.getText().toString());
                if (ls != null) {
                    ad = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, ls);
                    lista.setAdapter(ad);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DAOContactos obj = new DAOContactos(MainActivity.this);
        List ls = obj.getAll();
//
        if (ls != null){
            ArrayAdapter <Contacto> adp = new ArrayAdapter<Contacto>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, ls);
            lista.setAdapter(adp);
        }
    }
}
