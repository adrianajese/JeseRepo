package net.jese.myagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Actividad2 extends AppCompatActivity {

    EditText user, mail, twi, phone, nac;
    Button btnG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        user = (EditText)findViewById(R.id.user);
        mail = (EditText)findViewById(R.id.mail);
        twi = (EditText)findViewById(R.id.twitt);
        phone = (EditText)findViewById(R.id.phone);
        nac = (EditText)findViewById(R.id.nac);
        btnG = (Button)findViewById(R.id.btnG);

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent();
                Contacto contact = new Contacto(user.getText().toString(), mail.getText().toString(), twi.getText().toString(),
                        phone.getText().toString(), nac.getText().toString());
                obj.putExtra("jese", contact);
                setResult(RESULT_OK, obj);
                finish();

            }
        });
    }
}
