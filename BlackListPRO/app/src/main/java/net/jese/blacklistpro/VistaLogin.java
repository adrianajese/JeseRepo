package net.jese.blacklistpro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by adrii on 06/03/2018.
 */

public class VistaLogin extends Activity {
    EditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        password = (EditText) findViewById(R.id.txtPassword);
    }

    public void action_iniciar(View v) {

        String user = usuario.getText().toString();
        String pass = password.getText().toString();

        if (user.equals("Jese") && pass.equals("jese")) {
            setResult(RESULT_OK);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
