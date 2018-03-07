package net.jese.blacklistpro.modelo;

import java.io.Serializable;

/**
 * Created by adrii on 06/03/2018.
 */

public class Contacto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Nombre: " + getNombre() + "\n Tel�fono: " + getNumero();
    }
}
