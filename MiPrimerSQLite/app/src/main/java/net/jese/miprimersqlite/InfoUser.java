package net.jese.miprimersqlite;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import modelo.Contacto;
import modelo.DAOContactos;

public class InfoUser extends AppCompatActivity {

    TextView infoName, infoPhone, infoMail, infoTw, infoNac;
    Button btnMod, btnEliminar;
    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        infoName = (TextView) findViewById(R.id.infoName);
        infoPhone = (TextView) findViewById(R.id.infoPhone);
        infoMail = (TextView) findViewById(R.id.infoMail);
        infoTw = (TextView) findViewById(R.id.infoTw);
        infoNac = (TextView) findViewById(R.id.infoNac);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOContactos obj = new DAOContactos(InfoUser.this);
                obj.delete(phone, name);
                finish();
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(getApplicationContext(), Users.class);
                in3.putExtra("mod", infoName.getText().toString());
                startActivity(in3);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DAOContactos obj = new DAOContactos(InfoUser.this);
        Contacto c = obj.contacto(getIntent().getExtras().getString("identificador"));
        infoName.setText(c.getNombre());
        infoPhone.setText(c.getTelefono());
        infoMail.setText(c.getCorreo_electronico());
        infoTw.setText(c.getTwitter());
        infoNac.setText(c.getFechaNac());
        name = c.getNombre();
        phone = c.getTelefono();


    }
}
