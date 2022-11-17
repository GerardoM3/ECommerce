package com.sis21a.e_commerce.Dto;

public class Dto_tipo_cuenta {

    String tipo;
    int estado;
    int id_negocio;
    int id_usuario;

    public Dto_tipo_cuenta() {
    }

    public Dto_tipo_cuenta(String tipo, int estado, int id_negocio, int id_usuario) {
        this.tipo = tipo;
        this.estado = estado;
        this.id_negocio = id_negocio;
        this.id_usuario = id_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_negocio() {
        return id_negocio;
    }

    public void setId_negocio(int id_negocio) {
        this.id_negocio = id_negocio;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
