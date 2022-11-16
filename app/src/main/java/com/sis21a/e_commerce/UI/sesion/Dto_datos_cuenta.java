package com.sis21a.e_commerce.UI.sesion;

public class Dto_datos_cuenta {

    String usuario;
    String correo;
    String clave;
    int id_usuario;

    public Dto_datos_cuenta(String usuario, String correo, String clave, int id_usuario) {
        this.usuario = usuario;
        this.correo = correo;
        this.clave = clave;
        this.id_usuario = id_usuario;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


}
