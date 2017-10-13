package modelo;

import java.util.Date;

/**
 * Created by adrii on 05/10/2017.
 */

public class Contacto {
    private int id;
    private String nombre;
    private String correo_electronico;
    private String twitter;
    private String telefono;
    private String fechaNac;

    public Contacto() {

    }

    public Contacto(int id, String nombre, String correo_electronico, String twitter, String telefono, String fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.twitter = twitter;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }

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

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return nombre+"\n"+telefono;

    }
}
