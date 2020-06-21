package consultas;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection cn;
    
    public static final String BBDD = "bd_saito_maintenance";
    public static final String USER = "root";
    public static final String PASS = "rebeldemenor1";
    public static final String URL = "jdbc:mysql://localhost:3306/" + BBDD 
            + "?useTimezone=true&serverTimezone=UTC";

    public static Connection getInstance() {
        if (cn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
                System.err.println("Error al conectar a la base de datos: " + e);
            }
        }
        return cn;
    }
}
