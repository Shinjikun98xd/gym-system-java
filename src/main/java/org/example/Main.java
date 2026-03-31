package org.example;// paquete donde está la clase Main

import org.example.ConexionBase; // importamos nuestra clase de conexión
import org.example.dao.ClienteDao;
import org.example.model.Cliente;
import org.example.services.Calculadora;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del proyecto
 * Solo sirve para probar la conexión a la base de datos
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteDao dao = new ClienteDao();

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Listar clientes");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Calculadora");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {

                case 1:
                    System.out.println("AGREGAR CLIENTE");

                    System.out.print("DNI: ");
                    int dni = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    System.out.print("Peso: ");
                    double peso = Double.parseDouble(sc.nextLine());

                    System.out.print("Altura: ");
                    double altura = Double.parseDouble(sc.nextLine());

                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    Cliente nuevo = new Cliente(dni, nombre, apellido, correo, peso, altura, edad, telefono);

                    dao.agregarCliente(nuevo);

                    break;

                case 2:
                    System.out.println("BUSCAR CLIENTE POR ID");

                    System.out.print("Ingrese ID: ");
                    int idBuscar = Integer.parseInt(sc.nextLine());

                    Cliente encontrado = dao.obtenerCliente(idBuscar);

                    if (encontrado != null) {
                        System.out.println("\n--- CLIENTE ---");
                        System.out.println("ID: " + encontrado.getId());
                        System.out.println("DNI: " + encontrado.getDni());
                        System.out.println("Nombre: " + encontrado.getNombre());
                        System.out.println("Apellido: " + encontrado.getApellido());
                        System.out.println("Correo: " + encontrado.getCorreo());
                        System.out.println("Peso: " + encontrado.getPeso());
                        System.out.println("Altura: " + encontrado.getAltura());
                        System.out.println("Edad: " + encontrado.getEdad());
                        System.out.println("Teléfono: " + encontrado.getTelefono());
                    } else {
                        System.out.println("Cliente no encontrado");
                    }

                    break;

                case 3:
                    System.out.println("LISTAR CLIENTES");

                    List<Cliente> clientes = dao.listarClientes();

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println("----------------------");
                            System.out.println("ID: " + c.getId());
                            System.out.println("Nombre: " + c.getNombre());
                            System.out.println("Apellido: " + c.getApellido());
                            System.out.println("Peso: " + c.getPeso());
                        }
                    }

                    break;

                case 4:
                    System.out.println("ACTUALIZAR CLIENTE");

                    System.out.print("Ingrese ID del cliente a actualizar: ");
                    int idActualizar = Integer.parseInt(sc.nextLine());

                    Cliente clienteActualizar = dao.obtenerCliente(idActualizar);

                    if (clienteActualizar != null) {

                        System.out.print("Nuevo nombre: ");
                        clienteActualizar.setNombre(sc.nextLine());

                        System.out.print("Nuevo apellido: ");
                        clienteActualizar.setApellido(sc.nextLine());

                        System.out.print("Nuevo correo: ");
                        clienteActualizar.setCorreo(sc.nextLine());

                        System.out.print("Nuevo peso: ");
                        clienteActualizar.setPeso(Double.parseDouble(sc.nextLine()));

                        System.out.print("Nueva altura: ");
                        clienteActualizar.setAltura(Double.parseDouble(sc.nextLine()));

                        System.out.print("Nueva edad: ");
                        clienteActualizar.setEdad(Integer.parseInt(sc.nextLine()));

                        System.out.print("Nuevo teléfono: ");
                        clienteActualizar.setTelefono(sc.nextLine());

                        dao.actualizarCliente(clienteActualizar);

                    } else {
                        System.out.println("Cliente no encontrado");
                    }

                    break;

                case 5:
                    System.out.println("ELIMINAR CLIENTE");

                    System.out.print("Ingrese ID a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());

                    Cliente clienteEliminar = dao.obtenerCliente(idEliminar);

                    if (clienteEliminar != null) {

                        System.out.println("Cliente: " + clienteEliminar.getNombre() + " " + clienteEliminar.getApellido());

                        System.out.print("¿Seguro que deseas eliminarlo? (s/n): ");
                        String confirmacion = sc.nextLine();

                        if (confirmacion.equalsIgnoreCase("s")) {
                            dao.eliminarCliente(idEliminar);
                        } else {
                            System.out.println("Eliminación cancelada");
                        }

                    } else {
                        System.out.println("Cliente no encontrado");
                    }

                    break;

                case 6:
                    System.out.println("CÁLCULO NUTRICIONAL");

                    System.out.print("Ingrese ID del cliente: ");
                    int idNutricion = Integer.parseInt(sc.nextLine());

                    Cliente clienteNutricion = dao.obtenerCliente(idNutricion);

                    if (clienteNutricion != null) {

                        double proteina = Calculadora.calcularProteina(clienteNutricion);
                        double agua = Calculadora.calcularAgua(clienteNutricion);
                        double creatina = Calculadora.calcularCreatina(clienteNutricion);
                        double magnesio = Calculadora.calcularMagnesio(clienteNutricion);

                        System.out.println("\n--- RECOMENDACIÓN DIARIA ---");
                        System.out.println("Proteína: " + proteina + " g");
                        System.out.println("Agua: " + agua + " ml");
                        System.out.println("Creatina: " + creatina + " g");
                        System.out.println("Magnesio: " + magnesio + " mg");

                    } else {
                        System.out.println("Cliente no encontrado");
                    }

                    break;

                case 7:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 7);

        sc.close();
    }
}




