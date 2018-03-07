package net.jese.blacklistpro.modelo;

import java.io.Serializable;

/**
 * Created by adrii on 06/03/2018.
 */

public class modeloMensaje implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String telefono;
    private String cuerpo;
    private String fecha;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Tel�fono: " + getTelefono() + "\n Fecha: " + getFecha() + " "
                + getHora();
    }

}
