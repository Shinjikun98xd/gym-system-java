import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() {
        // Creamos una variable de tipo Connection (al inicio está vacía)
        Connection conexion = null;
        try {
            // URL de conexión a MySQL
            // jdbc:mysql://localhost:3306/persona
            // localhost → tu PC
            // 3306 → puerto por defecto de MySQL
            // persona → nombre de tu base de datos
            String url = "jdbc:mysql://localhost:3306/persona?useSSL=false&serverTimezone=UTC";
            // Usuario de la base de datos (normalmente root)
            String usuario = "root";
            // Contraseña de MySQL (pon la tuya)
            String password = "1234";
            // Aquí intentamos hacer la conexión real
            conexion = DriverManager.getConnection(url, usuario, password);
            // Si llega aquí, la conexión fue exitosa
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            // Si ocurre un error, lo mostramos
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
        // Retornamos la conexión (puede ser válida o null si falló)
        return conexion;
    }
}