package org.example; // indica el paquete donde está la clase

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de manejar la conexión a la base de datos MySQL
 */
public class ConexionBase {

    /**
     * Método que devuelve la conexión a la base de datos.
     * @return Connection → conexión activa o null si falla
     */
    public static Connection conectar() {
        // Inicializamos la variable que contendrá la conexión
        Connection conexion = null;

        try {
            // URL de conexión a la base de datos
            // jdbc:mysql://localhost:3306/persona
            // useSSL=false → evita advertencias de SSL en MySQL
            // serverTimezone=UTC → evita problemas de zona horaria
            String url = "jdbc:mysql://localhost:3306/persona?useSSL=false&serverTimezone=UTC";

            // Usuario de la base de datos
            String usuario = "root";

            // Contraseña de MySQL (pon la tuya)
            String password = "1234";

            // Intentamos crear la conexión usando DriverManager
            conexion = DriverManager.getConnection(url, usuario, password);

        } catch (SQLException e) {
            // Si ocurre algún error, lo capturamos e imprimimos
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }

        // Retornamos la conexión (puede ser null si hubo error)
        return conexion;
    }
}