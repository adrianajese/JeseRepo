package net.jese.blacklistpro.modelo;

/**
 * Created by adrii on 06/03/2018.
 */

public class contactoBloqueado {
    private int id;
    private String nombre;
    private String numero;
    private int tipoBloqueo;

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

    public int getTipoBloqueo() {
        return tipoBloqueo;
    }

    public void setTipoBloqueo(int tipoBloqueo) {
        this.tipoBloqueo = tipoBloqueo;
    }

}
