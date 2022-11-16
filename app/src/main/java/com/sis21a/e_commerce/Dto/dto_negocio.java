package com.sis21a.e_commerce.Dto;

public class dto_negocio {
    public int getId_negocio() {
        return id_negocio;
    }

    public void setId_negocio(int id_negocio) {
        this.id_negocio = id_negocio;
    }

    public String getNombre_negocio() {
        return nombre_negocio;
    }

    public void setNombre_negocio(String nombre_negocio) {
        this.nombre_negocio = nombre_negocio;
    }

    public int getTipo_negocio() {
        return tipo_negocio;
    }

    public void setTipo_negocio(int tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public dto_negocio() {
    }

    public dto_negocio(int id_negocio, String nombre_negocio, int tipo_negocio, int id_usuario) {
        this.id_negocio = id_negocio;
        this.nombre_negocio = nombre_negocio;
        this.tipo_negocio = tipo_negocio;
        this.id_usuario = id_usuario;
    }

    int id_negocio;
    String nombre_negocio;
    int tipo_negocio;
    int id_usuario;

}
