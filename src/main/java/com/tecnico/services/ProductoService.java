package com.tecnico.services;

import com.tecnico.models.Producto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProductoService {

    private ArrayList<Producto> productos = new ArrayList<>();

    private File archivo = new File("data/productos.txt");

    private int siguienteId = 1;

    public ProductoService() {
        cargar();
    }

    public ArrayList<Producto> listar() {
        return productos;
    }

    private void metodoparavalidar(
            String nombre,
            double precio,
            int stock,
            String categoria
    ) {

        if (nombre == null || categoria == null) {
            throw new IllegalArgumentException(
                    "Completa todos los campos"
            );
        }

        if (nombre.trim().isEmpty() || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Completa todos los campos"
            );
        }

        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException(
                    "Precio y stock no pueden ser negativos"
            );
        }
    }

    public void crear(
            String nombre,
            double precio,
            int stock,
            String categoria
    ) {

        metodoparavalidar(nombre, precio, stock, categoria);

        Producto nuevoProducto = new Producto(
                siguienteId,
                nombre,
                precio,
                stock,
                categoria
        );

        productos.add(nuevoProducto);

        siguienteId++;

        guardar();
    }

    public void actualizar(
            int id,
            String nombre,
            double precio,
            int stock,
            String categoria
    ) {

        metodoparavalidar(nombre, precio, stock, categoria);

        for (Producto producto : productos) {

            if (producto.getId() == id) {

                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setStock(stock);
                producto.setCategoria(categoria);

                guardar();

                return;
            }
        }

        System.out.println("Producto no encontrado");
    }

    public void eliminar(int id) {

        for (int i = 0; i < productos.size(); i++) {

            if (productos.get(i).getId() == id) {

                productos.remove(i);

                guardar();

                return;
            }
        }

        System.out.println("Producto no encontrado");
    }

    private void guardar() {

        try {

            // Crear la carpeta data si todavía no existe
            archivo.getParentFile().mkdirs();

            FileWriter escritor = new FileWriter(archivo);

            // Recorrer todos los productos
            for (Producto producto : productos) {

                // Guardar cada producto en una línea
                escritor.write(
                        producto.getId() + "|" +
                        producto.getNombre() + "|" +
                        producto.getPrecio() + "|" +
                        producto.getStock() + "|" +
                        producto.getCategoria() + "\n"
                );
            }

            escritor.close();

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar los productos: " +
                    e.getMessage()
            );
        }
    }

    private void cargar() {

        try {

            // Si el archivo no existe, no hay productos para cargar
            if (!archivo.exists()) {
                return;
            }

            FileReader archivoLectura = new FileReader(archivo);

            BufferedReader lector = new BufferedReader(archivoLectura);

            String linea;

            // Leer el archivo línea por línea
            while ((linea = lector.readLine()) != null) {

                // Separar los datos usando el símbolo |
                String[] datos = linea.split("\\|");

                int id = Integer.parseInt(datos[0]);

                String nombre = datos[1];

                double precio = Double.parseDouble(datos[2]);

                int stock = Integer.parseInt(datos[3]);

                String categoria = datos[4];

                Producto producto = new Producto(
                        id,
                        nombre,
                        precio,
                        stock,
                        categoria
                );

                productos.add(producto);

                // Evitar que se repitan los ID
                if (id >= siguienteId) {
                    siguienteId = id + 1;
                }
            }

            lector.close();

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar los productos: " +
                    e.getMessage()
            );
        }
    }
}