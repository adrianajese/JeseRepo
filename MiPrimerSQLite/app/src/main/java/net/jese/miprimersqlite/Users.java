package net.jese.miprimersqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import modelo.*;

public class Users extends AppCompatActivity {

    EditText nombre, telefono, correo, twitter, fechaNac;
    Button agregar;
    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        nombre = (EditText) findViewById(R.id.name);
        telefono = (EditText) findViewById(R.id.phone);
        correo = (EditText) findViewById(R.id.mail);
        twitter = (EditText) findViewById(R.id.tw);
        fechaNac = (EditText) findViewById(R.id.nac);
        agregar = (Button) findViewById(R.id.btnAgregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString().length() != 0){
                    DAOContactos obj = new DAOContactos(Users.this);
                    Contacto ctc = new Contacto(0,nombre.getText().toString(), correo.getText().toString(), twitter.getText().toString(), telefono.getText().toString(), fechaNac.getText().toString());
                    if (getIntent().getExtras().getString("mod").equals("agregar")){
                        obj.insert(ctc);
                    } else {
                        obj.update(ctc, name, phone);
                    }
                    finish();
                } else {
                    Toast.makeText(Users.this, "Debe ingresar el nombre del contacto", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!getIntent().getExtras().getString("mod").equals("agregar")){
            agregar.setText("Modificar");
            DAOContactos obj = new DAOContactos(Users.this);
            Contacto c = obj.contacto(getIntent().getExtras().getString("mod"));
            nombre.setText(c.getNombre());
            telefono.setText(c.getTelefono());
            correo.setText(c.getCorreo_electronico());
            twitter.setText(c.getTwitter());
            fechaNac.setText(c.getFechaNac());
            name = c.getNombre();
            phone = c.getTelefono();
        }
    }
}
