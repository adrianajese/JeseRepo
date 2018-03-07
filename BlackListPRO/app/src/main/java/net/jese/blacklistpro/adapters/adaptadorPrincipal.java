package net.jese.blacklistpro.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.jese.blacklistpro.modelo.contactoBloqueado;

/**
 * Created by adrii on 06/03/2018.
 */

public class adaptadorPrincipal extends ArrayAdapter<contactoBloqueado> {
    Activity context;
    contactoBloqueado[] datos;

    public adaptadorPrincipal(Activity context, contactoBloqueado[] datos) {
        super(context, R.layout.adaptador_principal, datos);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflate = context.getLayoutInflater();
        View item = inflate.inflate(R.layout.adaptador_principal, null);

        TextView nombre = (TextView) item.findViewById(R.id.lblNombre);
        nombre.setText(datos[position].getNombre());

        TextView tipo = (TextView) item.findViewById(R.id.lblTipoBloqueo);
        tipo.setText("Tipo de bloqueo: " + datos[position].getTipoBloqueo());

        ImageView img = (ImageView) item.findViewById(R.id.imgImagen);
        img.setImageResource(R.drawable.ic_launcher);

        return item;

    }
}

