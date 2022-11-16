package com.sis21a.e_commerce.Dto;

public class dto_datos_generales {
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public dto_datos_generales() {
    }

    public dto_datos_generales(int id_usuario, String nombres, String apellidos) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    int id_usuario;
    String nombres;
    String apellidos;
}
