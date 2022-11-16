package com.sis21a.e_commerce.Producto;

public class dto_producto {
    public dto_producto(int id_product, String nombre_producto, String descripcion_producto, int existencias, int estado_producto, int id_categoria) {
        this.id_product = id_product;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.existencias = existencias;
        this.estado_producto = estado_producto;
        this.id_categoria = id_categoria;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(int estado_producto) {
        this.estado_producto = estado_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    int  id_product;
    String nombre_producto;
    String descripcion_producto;
    int existencias;
    int estado_producto;
    int id_categoria;
}
