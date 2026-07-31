package com.tecnico.services;

import com.tecnico.models.Producto;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();
    private File archivo = new File("data/productos.json");
    private int siguienteId = 1;

    public ProductoService() {
        cargar();
    }

    public ArrayList<Producto> listar() {
        return productos;
    }

    private void metodoparavalidar(String nombre, double precio, int stock, String categoria) {
        if (nombre.isEmpty() || categoria.isEmpty()) {
            throw new IllegalArgumentException("Completa todos los campos");
        }
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("Precio y stock no pueden ser negativos");
        }
    }


    public void crear(String nombre, double precio, int stock, String categoria) {
        metodoparavalidar(nombre, precio, stock, categoria);

        productos.add(new Producto(siguienteId, nombre, precio, stock, categoria));
        siguienteId++;
        guardar();
    }

    public void actualizar(int id, String nombre, double precio, int stock, String categoria) {
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
            archivo.getParentFile().mkdirs();
            FileWriter escritor = new FileWriter(archivo);
            escritor.write("[\n");

            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                escritor.write("{\"id\":" + p.getId()
                    + ",\"nombre\":\"" + p.getNombre()
                    + "\",\"precio\":" + p.getPrecio()
                    + ",\"stock\":" + p.getStock()
                    + ",\"categoria\":\"" + p.getCategoria() + "\"}");

                if (i < productos.size() - 1) escritor.write(",");
                escritor.write("\n");
            }

            escritor.write("]");
            escritor.close();
        } catch (Exception e) {
            System.out.println("Error al guardar los productos.");
        }
    }

    private void cargar() {
        try {
            if (!archivo.exists()) return;

            String texto = Files.readString(archivo.toPath());
            texto = texto.replace("[", "").replace("]", "").trim();
            if (texto.isEmpty()) return;

            String[] filas = texto.split("},\\s*\\{");
            for (String fila : filas) {
                fila = fila.replace("{", "").replace("}", "").replace("\"", "");
                String[] datos = fila.split(",");

                int id = Integer.parseInt(datos[0].split(":")[1]);
                String nombre = datos[1].split(":")[1];
                double precio = Double.parseDouble(datos[2].split(":")[1]);
                int stock = Integer.parseInt(datos[3].split(":")[1]);
                String categoria = datos[4].split(":")[1];

                productos.add(new Producto(id, nombre, precio, stock, categoria));
                siguienteId = id + 1;
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los productos.");
        }
    }
}
