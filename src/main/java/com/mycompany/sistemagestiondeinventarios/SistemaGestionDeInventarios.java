package com.mycompany.sistemagestiondeinventarios;

import java.util.Scanner;

/**
 * Clase principal que contiene el método main para ejecutar el sistema.
 * Proporciona una interfaz de usuario en la consola para interactuar con el inventario.
 */
public class SistemaGestionDeInventarios {
    public static void main(String[] args) {
        // Instancia la clase Inventario, lo que automáticamente carga los datos.
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        // Bucle do-while para mostrar el menú y procesar la entrada del usuario.
        do {
            System.out.println("\n=== Sistema de Gestión de Inventarios ===");
            System.out.println("            Muebleria Gabriel");
            System.out.println("  ---Desarrollado por Stalin Mendieta---");
            System.out.println("\n1. Agregar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            // Usa un switch para manejar las diferentes opciones del menú.
            switch (opcion) {
                case "1":
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreAdd = scanner.nextLine();
                    try {
                        System.out.print("Ingrese la cantidad: ");
                        int cantidadAdd = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el precio: ");
                        double precioAdd = Double.parseDouble(scanner.nextLine());
                        inventario.agregarProducto(nombreAdd, cantidadAdd, precioAdd);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Cantidad y precio deben ser números válidos.");
                    }
                    break;
                case "2":
                    System.out.print("Ingrese el nombre del producto a actualizar: ");
                    String nombreUpdate = scanner.nextLine();
                    try {
                        System.out.print("Ingrese la nueva cantidad: ");
                        int nuevaCantidad = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el nuevo precio: ");
                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                        inventario.actualizarProducto(nombreUpdate, nuevaCantidad, nuevoPrecio);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Cantidad y precio deben ser números válidos.");
                    }
                    break;
                case "3":
                    System.out.print("Ingrese el nombre del producto a eliminar: ");
                    String nombreDelete = scanner.nextLine();
                    inventario.eliminarProducto(nombreDelete);
                    break;
                case "4":
                    inventario.mostrarInventario();
                    break;
                case "5":
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (!opcion.equals("5"));

        // Cierra el objeto Scanner para liberar recursos.
        scanner.close();
    }
}