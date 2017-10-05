package net.jese.myagenda;

import java.io.Serializable;

/**
 * Created by adrii on 05/09/2017.
 */

public class Contacto implements Serializable {

    private String user;
    private String mail;
    private String twitt;
    private String phone;
    private String nac;

    public Contacto(String user, String mail, String twitt, String phone, String nac) {
        this.user = user;
        this.mail = mail;
        this.twitt = twitt;
        this.phone = phone;
        this.nac = nac;
    }

    @Override
    public String toString() {
        return user+"\n"+mail;
    }
}
