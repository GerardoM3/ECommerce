package com.sis21a.e_commerce.Dto;

import androidx.annotation.NonNull;

public class dto_categoria {

    int id_categoria;
    String nombre_categoria;
    String descripcion_categoria;
    int estado_categoria;
    int id_negocio;

    public dto_categoria() {
    }

    public dto_categoria(int id_categoria, String nombre_categoria, String descripcion_categoria, int estado_categoria, int id_negocio) {
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
        this.descripcion_categoria = descripcion_categoria;
        this.estado_categoria = estado_categoria;
        this.id_negocio = id_negocio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }

    public int getEstado_categoria() {
        return estado_categoria;
    }

    public void setEstado_categoria(int estado_categoria) {
        this.estado_categoria = estado_categoria;
    }

    public int getId_negocio() {
        return id_negocio;
    }

    public void setId_negocio(int id_negocio) {
        this.id_negocio = id_negocio;
    }
}
