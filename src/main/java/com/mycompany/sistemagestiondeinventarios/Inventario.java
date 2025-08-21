package com.mycompany.sistemagestiondeinventarios;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona la colección de productos y la persistencia de datos
 * en un archivo de texto.
 */
public class Inventario {
    // Un HashMap para almacenar productos, usando el nombre como clave para acceso rápido.
    private Map<String, Producto> productos;
    // Constante para el nombre del archivo de inventario.
    private static final String NOMBRE_ARCHIVO = "inventario.txt";

    /**
     * Constructor de la clase Inventario.
     * Al instanciar un objeto Inventario, automáticamente intenta cargar
     * los productos desde el archivo.
     */
    public Inventario() {
        this.productos = new HashMap<>();
        cargarInventario();
    }

    /**
     * Carga el inventario desde el archivo de texto.
     * Utiliza un bloque try-with-resources para asegurar que el BufferedReader
     * se cierre automáticamente.
     * Maneja excepciones como FileNotFoundError (si el archivo no existe)
     * y IOException para errores de lectura.
     */
    public void cargarInventario() {
        System.out.println("Cargando inventario desde el archivo...");
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea por la coma para obtener los datos.
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    // Parsea los datos y crea un objeto Producto.
                    String nombre = datos[0];
                    int cantidad = Integer.parseInt(datos[1]);
                    double precio = Double.parseDouble(datos[2]);
                    productos.put(nombre, new Producto(nombre, cantidad, precio));
                }
            }
            System.out.println("Inventario cargado exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo 'inventario.txt' no se encontró. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error de I/O al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato de los datos en el archivo. Verifique el contenido.");
        }
    }

    /**
     * Guarda el inventario actual en el archivo de texto.
     * Reescribe el archivo con el estado actual del HashMap 'productos'.
     * Utiliza un bloque try-with-resources para el BufferedWriter.
     */
    public void guardarInventario() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Producto p : productos.values()) {
                bw.write(p.toString());
                bw.newLine(); // Agrega un salto de línea para cada producto.
            }
            System.out.println("Inventario guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error de I/O al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Agrega un nuevo producto al inventario y lo guarda en el archivo.
     *
     * @param nombre   Nombre del nuevo producto.
     * @param cantidad Cantidad inicial.
     * @param precio   Precio unitario.
     */
    public void agregarProducto(String nombre, int cantidad, double precio) {
        if (productos.containsKey(nombre)) {
            System.out.println("El producto '" + nombre + "' ya existe. No se añadió.");
        } else {
            productos.put(nombre, new Producto(nombre, cantidad, precio));
            guardarInventario(); // Persiste el cambio en el archivo.
            System.out.println("Producto '" + nombre + "' agregado exitosamente.");
        }
    }

    /**
     * Actualiza la cantidad y/o el precio de un producto existente.
     *
     * @param nombre        Nombre del producto a actualizar.
     * @param nuevaCantidad Nueva cantidad del producto.
     * @param nuevoPrecio   Nuevo precio del producto.
     */
    public void actualizarProducto(String nombre, int nuevaCantidad, double nuevoPrecio) {
        if (productos.containsKey(nombre)) {
            Producto p = productos.get(nombre);
            p.setCantidad(nuevaCantidad);
            p.setPrecio(nuevoPrecio);
            guardarInventario(); // Persiste el cambio.
            System.out.println("Producto '" + nombre + "' actualizado exitosamente.");
        } else {
            System.out.println("El producto '" + nombre + "' no se encontró.");
        }
    }

    /**
     * Elimina un producto del inventario.
     *
     * @param nombre Nombre del producto a eliminar.
     */
    public void eliminarProducto(String nombre) {
        if (productos.containsKey(nombre)) {
            productos.remove(nombre);
            guardarInventario(); // Persiste el cambio.
            System.out.println("Producto '" + nombre + "' eliminado exitosamente.");
        } else {
            System.out.println("El producto '" + nombre + "' no se encontró.");
        }
    }

    /**
     * Muestra la lista completa de productos en el inventario.
     * Si el inventario está vacío, lo notifica al usuario.
     */
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("\n--- Inventario Actual ---");
        // Itera sobre los valores del HashMap para mostrar cada producto.
        productos.values().forEach(p ->
            System.out.println("Nombre: " + p.getNombre() + ", Cantidad: " + p.getCantidad() + ", Precio: $" + String.format("%.2f", p.getPrecio()))
        );
        System.out.println("-------------------------");
    }
}