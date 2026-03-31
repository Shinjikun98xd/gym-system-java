package org.example.services;

import org.example.model.Cliente;

public class Calculadora {

    public static double calcularProteina(Cliente c) {
        return c.getPeso() * 2.0; // gramos por día
    }

    public static double calcularAgua(Cliente c) {
        return c.getPeso() * 35; // ml por día
    }

    public static double calcularCreatina(Cliente c) {
        return 5; // gramos estándar
    }

    public static double calcularMagnesio(Cliente c) {
        return 400; // mg promedio adulto
    }
}
