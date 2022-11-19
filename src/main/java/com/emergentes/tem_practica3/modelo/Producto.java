package com.emergentes.tem_practica3.modelo;

public class Producto {
  private int id;
  private float precio;
  private int cantidad;
  private String producto;

  public Producto() {
    this.id = 0;
    this.precio = 0f;
    this.cantidad = 0;
    this.producto = "";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getPrecio() {
    return precio;
  }

  public void setPrecio(float precio) {
    this.precio = precio;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public String getProducto() {
    return producto;
  }

  public void setProducto(String producto) {
    this.producto = producto;
  }  
}
