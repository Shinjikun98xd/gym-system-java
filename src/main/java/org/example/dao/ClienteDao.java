package org.example.dao;

import org.example.model.Cliente;
import org.example.ConexionBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    // Método para insertar un cliente en la base de datos
    public void agregarCliente(Cliente c) {

        // Consulta SQL con parámetros (los ? se reemplazan luego)
        String sql = "INSERT INTO clientes (dni, nombre, apellido, correo, peso, altura, edad, telefono, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        // try-with-resources: abre conexión y la cierra automáticamente
        try (Connection conn = ConexionBase.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Aquí reemplazamos cada ? con los datos del cliente

            ps.setInt(1, c.getDni());          // ?1 → dni
            ps.setString(2, c.getNombre());    // ?2 → nombre
            ps.setString(3, c.getApellido());  // ?3 → apellido
            ps.setString(4, c.getCorreo());    // ?4 → correo
            ps.setDouble(5, c.getPeso());      // ?5 → peso
            ps.setDouble(6, c.getAltura());    // ?6 → altura
            ps.setInt(7, c.getEdad());         // ?7 → edad
            ps.setString(8, c.getTelefono());  // ?8 → telefono

            // Ejecuta el INSERT
            ps.executeUpdate();

            System.out.println("Cliente agregado correctamente");

        } catch (SQLException e) {
            // Si algo falla, muestra el error
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }
    // Método para consultar un cliente en la base de datos
    public Cliente obtenerCliente(int id) {

        // Consulta SQL para buscar un cliente por su DNI
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (
                // Abrimos conexión a la base de datos
                Connection conn = ConexionBase.conectar();

                // Preparamos la consulta SQL
                PreparedStatement ps = conn.prepareStatement(sql)

        ) {

            // Reemplazamos el ? con el valor del Id
            ps.setInt(1, id);

            // Ejecutamos la consulta (SELECT)
            ResultSet rs = ps.executeQuery();

            // Verificamos si hay resultado
            if (rs.next()) {

                // Creamos un objeto Cliente vacío
                Cliente c = new Cliente();

                // Llenamos el objeto con los datos de la BD
                c.setId(rs.getInt("id"));
                c.setDni(rs.getInt("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCorreo(rs.getString("correo"));
                c.setPeso(rs.getDouble("peso"));
                c.setAltura(rs.getDouble("altura"));
                c.setEdad(rs.getInt("edad"));
                c.setTelefono(rs.getString("telefono"));

                // Devolvemos el cliente encontrado
                return c;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cliente: " + e.getMessage());
        }

        // Si no encontró nada, devuelve null
        return null;
    }
    // Método para consultar todos cliente en la base de datos
    public List<Cliente> listarClientes() {

        // Creamos una lista para guardar todos los clientes
        List<Cliente> lista = new ArrayList<>();

        // Consulta SQL para traer todos los clientes
        String sql = "SELECT * FROM clientes";

        try (
                // Abrimos conexión
                Connection conn = ConexionBase.conectar();

                // Preparamos la consulta
                PreparedStatement ps = conn.prepareStatement(sql);

                // Ejecutamos el SELECT
                ResultSet rs = ps.executeQuery()
        ) {

            // Recorremos TODOS los resultados
            while (rs.next()) {

                // Creamos un cliente por cada fila
                Cliente c = new Cliente();

                // Llenamos el objeto con datos de la BD
                c.setId(rs.getInt("id"));
                c.setDni(rs.getInt("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCorreo(rs.getString("correo"));
                c.setPeso(rs.getDouble("peso"));
                c.setAltura(rs.getDouble("altura"));
                c.setEdad(rs.getInt("edad"));
                c.setTelefono(rs.getString("telefono"));

                // Agregamos el cliente a la lista
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        // Devolvemos la lista completa
        return lista;
    }
    // Método para actualizar un cliente en la base de datos
    public void actualizarCliente(Cliente c) {

        // Consulta SQL para actualizar un cliente por ID
        String sql = "UPDATE clientes SET dni=?, nombre=?, apellido=?, correo=?, peso=?, altura=?, edad=?, telefono=? WHERE id=?";

        try (
                // Abrimos conexión
                Connection conn = ConexionBase.conectar();

                // Preparamos la consulta
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Reemplazamos los ? con los nuevos datos
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getCorreo());
            ps.setDouble(5, c.getPeso());
            ps.setDouble(6, c.getAltura());
            ps.setInt(7, c.getEdad());
            ps.setString(8, c.getTelefono());

            // ESTE ES CLAVE → el ID que queremos actualizar
            ps.setInt(9, c.getId());

            // Ejecutamos el UPDATE
            ps.executeUpdate();

            System.out.println("Cliente actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }
    // Método para eliminar un cliente en la base de datos
    public void eliminarCliente(int id) {

        // Consulta SQL para eliminar un cliente por ID
        String sql = "DELETE FROM clientes WHERE id=?";

        try (
                // Abrimos conexión
                Connection conn = ConexionBase.conectar();

                // Preparamos la consulta
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            // Reemplazamos el ? con el ID del cliente
            ps.setInt(1, id);

            // Ejecutamos el DELETE

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.out.println("No existe cliente con ese ID");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}

