package net.jese.audiolibros;

import android.content.Context;

import java.util.Vector;

/**
 * Created by jlmgm on 12/09/2017.
 */

public class InfoGlobal {

    private Vector<Libro> vectorLibros;
    private AdaptadorLibros adaptador;


    private static InfoGlobal INSTANCIA = new InfoGlobal();

    private InfoGlobal() {}

    public static InfoGlobal getInstance(){

        return INSTANCIA;

    }


    public void inicializa(Context contexto){

        vectorLibros = Libro.ejemploLibros();
        adaptador = new AdaptadorLibros(contexto, vectorLibros);

    }


    public AdaptadorLibros getAdaptador() {
        return adaptador;
    }


    public Vector<Libro> getVectorLibros() {
        return vectorLibros;
    }

















}
