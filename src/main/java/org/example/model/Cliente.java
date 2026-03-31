package org.example.model;

public class Cliente {
    private int id;           // Clave primaria en la base de datos
    private int dni;       // Documento del cliente
    private String nombre;
    private String apellido;
    private String correo;
    private double peso;      // Peso en kg
    private double altura;    // Altura en metros
    private int edad;
    private String telefono;

    public Cliente() {
        // Constructor vacío
    }
    public Cliente (int dni, String nombre, String apellido, String correo,
                    double peso, double altura, int edad, String telefono) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    // Setter para dni: solo números y no vacío
    public void setDni(int dni) {
        if (dni > 0) {
            this.dni = dni;
        } else {
            System.out.println("⚠️ DNI inválido");
        }
    }

    public String getNombre() {
        return nombre;
    }

    // Setters para nombre y apellido: no permiten vacío
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("⚠️ Nombre inválido");
        }
    }

    public String getApellido() {
        return apellido;
    }

    // Setters para nombre y apellido: no permiten vacío
    public void setApellido(String apellido) {
        if (apellido != null && !apellido.isEmpty()) {
            this.apellido = apellido;
        } else {
            System.out.println("⚠️ Apellido inválido");
        }
    }

    public String getCorreo() {
        return correo;
    }

    // Setter para correo: verifica que contenga '@' y mínimo 5 caracteres
    public void setCorreo(String correo) {
        if (correo != null && correo.contains("@") && correo.length() >= 5) {
            this.correo = correo;
        } else {
            System.out.println("⚠️ Correo inválido");
        }
    }

    public double getPeso() {
        return peso;
    }

    // Setter para peso: no permite 0 o negativo
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        } else {
            System.out.println("⚠️ Peso inválido, debe ser mayor a 0");
        }
    }

    public double getAltura() {
        return altura;
    }

    // Setter para altura: no permite 0 o negativo
    public void setAltura(double altura) {
        if (altura > 0) {
            this.altura = altura;
        } else {
            System.out.println("⚠️ Altura inválida, debe ser mayor a 0");
        }
    }

    public int getEdad() {
        return edad;
    }

    // Setter para edad: no permite negativa ni demasiado alta (ej: >120)
    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 120) {
            this.edad = edad;
        } else {
            System.out.println("⚠️ Edad inválida");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    // Setter para teléfono: solo permite números y '+' al inicio
    public void setTelefono(String telefono) {
        if (telefono != null && telefono.matches("\\+?[0-9]{7,15}")) {
            this.telefono = telefono;
        } else {
            System.out.println("⚠️ Teléfono inválido");
        }
    }
}


