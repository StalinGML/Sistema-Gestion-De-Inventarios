package com.mycompany.sistemagestiondeinventarios;

import java.io.Serializable;

/**
 * Clase que modela un producto en el inventario.
 * Implementa Serializable para que los objetos de esta clase puedan ser
 * escritos y leídos de un archivo de forma fácil.
 */
public class Producto implements Serializable {
    // Atributos de la clase
    private String nombre;
    private int cantidad;
    private double precio;

    /**
     * Constructor para crear un nuevo objeto Producto.
     *
     * @param nombre   El nombre del producto.
     * @param cantidad La cantidad disponible en inventario.
     * @param precio   El precio unitario del producto.
     */
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y Setters para acceder y modificar los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Sobrescribe el método toString() para formatear el objeto
     * como una cadena de texto, ideal para guardar en el archivo.
     * El formato es "nombre,cantidad,precio".
     *
     * @return Una cadena formateada del producto.
     */
    @Override
    public String toString() {
        return nombre + "," + cantidad + "," + precio;
    }
}