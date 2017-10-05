package net.jese.myagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    Button btnA;
    ArrayList<Contacto> arreglito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA = (Button)findViewById(R.id.btnA);
        list = (ListView)findViewById(R.id.list);
        arreglito = new ArrayList<>();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(getApplication(), Actividad2.class);
                startActivityForResult(obj, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9 && resultCode == RESULT_OK){

            Contacto cont = (Contacto)data.getSerializableExtra("jese");
            arreglito.add(cont);

            String [] arr = new String[arreglito.size()];

            for (int i = 0; i < arreglito.size(); i++){
                arr[i] = arreglito.get(i).toString();
            }

            ArrayAdapter <String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arr);
            list.setAdapter(adp);
        }
    }
}
