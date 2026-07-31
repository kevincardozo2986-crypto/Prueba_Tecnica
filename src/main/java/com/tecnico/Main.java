package com.tecnico;

import com.tecnico.models.Producto;
import com.tecnico.services.ProductoService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoServicio = new ProductoService();
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        listarProductos(productoServicio);
                        break;
                    case 2:
                        crearProducto(productoServicio, scanner);
                        break;
                    case 3:
                        actualizarProducto(productoServicio, scanner);
                        break;
                    case 4:
                        eliminarProducto(productoServicio, scanner);
                        break;
                    case 0:
                        System.out.println("Programa terminado");
                        break;
                    default:
                        System.out.println("Ey, papi va hasta 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ey!!, el menu es de números no seas asi!!!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n_________________ PRODUCTOS _________________");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    public static void listarProductos(ProductoService servicio) {
        if (servicio.listar().isEmpty()) {
            System.out.println("No hay productos");
        } else {
            for (Producto producto : servicio.listar()) {
                System.out.println(producto);
            }
        }
    }

    public static void crearProducto(ProductoService servicio, Scanner teclado) {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(teclado.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(teclado.nextLine());
        System.out.print("Categoría: ");
        String categoria = teclado.nextLine();

        servicio.crear(nombre, precio, stock, categoria);
        System.out.println("Producto creado");
    }

    public static void actualizarProducto(ProductoService servicio, Scanner teclado) {
        System.out.print("ID: ");
        int id = Integer.parseInt(teclado.nextLine());
        System.out.print("Nuevo nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(teclado.nextLine());
        System.out.print("Nuevo stock: ");
        int stock = Integer.parseInt(teclado.nextLine());
        System.out.print("Nueva categoría: ");
        String categoria = teclado.nextLine();

        servicio.actualizar(id, nombre, precio, stock, categoria);
        System.out.println("Producto actualizado");
    }

    public static void eliminarProducto(ProductoService servicio, Scanner teclado) {
        System.out.print("ID: ");
        int id = Integer.parseInt(teclado.nextLine());
        servicio.eliminar(id);
        System.out.println("Producto eliminado");
    }
}
