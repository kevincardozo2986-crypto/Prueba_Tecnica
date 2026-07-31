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
            
        } catch (Exception e) {
            System.out.println("Error al guardar los productos.");
        }
    }

    private void cargar() {
        try {
            if (!archivo.exists()) return;

            }
        } catch (Exception e) {
            System.out.println("Error al cargar los productos.");
        }
    }
}
